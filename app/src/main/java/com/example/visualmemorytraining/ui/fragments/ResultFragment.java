package com.example.visualmemorytraining.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.visualmemorytraining.R;
import com.example.visualmemorytraining.databinding.QuestionFragmentBinding;
import com.example.visualmemorytraining.databinding.ResultFragmentBinding;

import java.util.ArrayList;

public class ResultFragment extends Fragment {
    private ResultFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ResultFragmentBinding.inflate(inflater,container,false);
        ArrayList<Integer> scores = (ArrayList<Integer>) getArguments().getSerializable("scores");
        int sum = 0;
        StringBuilder res = new StringBuilder("Вы ошиблись в следующих вопросах: ");
        for (int i = 0;i< scores.size();i++) {
            sum += scores.get(i);
            if(scores.get(i) == 0){
                if(res.toString().equals("Вы ошиблись в следующих вопросах: ")){
                    res.append((i+1));
                }
                else{
                    res.append(", ").append((i+1));
                }
            }
        }
        if(sum == 7){
            binding.textView.setText("Все правильно!");
        }
        else{
            binding.textView.setText(String.format("Правильных ответов: %d%%\n\n%s", (sum*100) / 7, res));
        }
        binding.buttonRes.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot())
                    .navigate(R.id.action_resultFragment_to_mainFragment);
        });


        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            //при нажатии кнопки назад не должно ничего происходить
            public void handleOnBackPressed() {
                // Back is pressed...

            }
        });
        return binding.getRoot();

    }
}
