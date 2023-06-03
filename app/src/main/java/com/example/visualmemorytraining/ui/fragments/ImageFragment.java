package com.example.visualmemorytraining.ui.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.visualmemorytraining.R;
import com.example.visualmemorytraining.data.Tasks;
import com.example.visualmemorytraining.databinding.ImageFragmentBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
//Фрагмент, отвечающий за показ изображения
public class ImageFragment extends Fragment {
    ImageFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ImageFragmentBinding.inflate(inflater, container, false);
        int num = Integer.parseInt(getArguments().getSerializable("task").toString());
        binding.progressBar.setVisibility(View.VISIBLE);
        ImageView image = binding.imageView;
        final boolean[] flag = {true};
        int time = 120000;
        if (num > 5 && num < 11)
            time = 180000;
        else if (num > 10)
            time = 240000;
        //Установка таймера
        CountDownTimer timer = new CountDownTimer(time, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                binding.textViewImage.setText(String.format(getString(R.string.timer), millisUntilFinished / 1000 / 60, (millisUntilFinished / 1000 - millisUntilFinished / 1000 / 60 * 60) / 10, (millisUntilFinished / 1000 - millisUntilFinished / 1000 / 60 * 60) % 10));
            }

            @Override
            public void onFinish() {
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_imageFragment_to_questionFragment, getArguments());
            }
        };
        //Размеры изображений
        int dimen_width = R.dimen.image_width;
        int dimen_height = R.dimen.image_height;

        if (num == 1 || num == 2 || num == 10 || num == 11 || num == 19 || num == 20) {
            dimen_width = R.dimen.image_width1;
            dimen_height = R.dimen.image_height1;
        } else if (num == 13 || num == 16 || num == 18) {
            dimen_width = R.dimen.image_width2;
            dimen_height = R.dimen.image_height2;
        }
        //Загрузка изображения с помощью picasso
        Picasso.get().load(Tasks.getUrls().get(Integer.valueOf(getArguments().getSerializable("task").toString()) - 1))
                .resizeDimen(dimen_width, dimen_height)
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Изображение успешно загружено
                        timer.start();
                        binding.textViewMethod.setText("Внимательно посмотрите, затем закройте глаза и постарайтесь представить \nв мельчайших деталях");
                        binding.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getContext(), "Ошибка при загрузке изображения", Toast.LENGTH_LONG).show();
                        if (flag[0])
                            Navigation.findNavController(binding.getRoot())
                                    .navigate(R.id.action_imageFragment_to_mainFragment, getArguments());
                        // Возникла ошибка при загрузке изображения
                    }
                });
        ;

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            //при нажатии кнопки назад происходит возврат
            public void handleOnBackPressed() {
                // Back is pressed...
                timer.cancel();//остановка таймера
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_imageFragment_to_mainFragment, getArguments());

            }
        });
        //Кнопка перехода к вопросам
        binding.buttonImage.setOnClickListener(v -> {
            timer.cancel();//остановка таймера
            flag[0] = false;
            Navigation.findNavController(binding.getRoot())
                    .navigate(R.id.action_imageFragment_to_questionFragment, getArguments());

        });
        return binding.getRoot();
    }

}
