package com.example.visualmemorytraining.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.visualmemorytraining.FireBaseRepository;
import com.example.visualmemorytraining.QuestionFragmentViewModel;
import com.example.visualmemorytraining.R;
import com.example.visualmemorytraining.data.Task;
import com.example.visualmemorytraining.data.Tasks;
import com.example.visualmemorytraining.databinding.QuestionFragmentBinding;

import java.util.Objects;

public class QuestionFragment extends Fragment {
    QuestionFragmentBinding binding;
    QuestionFragmentViewModel viewModel;
    Task task;
    //final int[] numberOfQuestion = {1};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = QuestionFragmentBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(QuestionFragmentViewModel.class);
        String taskNumber = getArguments().getSerializable("task").toString();
        task = Tasks.getTasks().get(Integer.parseInt(taskNumber) - 1);
        setViews();//отрисовка вопроса, кнопок
        viewModel.getNumberOfQuestion().observe(getViewLifecycleOwner(), numberOfQuestion -> {
            if (viewModel.getNumberOfQuestionValue() == 8) {//если 8, то переходит на фрагмент результа
                //int scores = MainActivity.getPref().getInt("scores",0) + viewModel.countScores();

//                String USER_KEY = "User";
//                DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
//                User user = new User(mDataBase.getKey(), MainFragment.email , MainFragment.scores+viewModel.countScores());
//                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                mDataBase.child(uid).setValue(user);
                FireBaseRepository.addDataBaseValue(viewModel.countScores());
                //MainActivity.getPref().edit().putInt("scores",scores).apply();
                Bundle bundle = new Bundle();
                bundle.putSerializable("scores", viewModel.getScoresValue());
                Navigation.findNavController(binding.getRoot())
                        .navigate(R.id.action_questionFragment_to_resultFragment,bundle);
            } else {
                setViews();//отрисовка вопроса, кнопок
            }
        });

        binding.questionButton1.setOnClickListener(v -> {
            if(Objects.equals(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[0], task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getRightAnswer())){
                viewModel.formResult(1);
            }
            else viewModel.formResult(0);
            buttonAction();
        });
        binding.questionButton2.setOnClickListener(v -> {
            if(Objects.equals(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[1], task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getRightAnswer())){
                viewModel.formResult(1);
            }
            else viewModel.formResult(0);
            buttonAction();
        });
        binding.questionButton3.setOnClickListener(v -> {
            if(Objects.equals(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[2], task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getRightAnswer())){
                viewModel.formResult(1);
            }
            else viewModel.formResult(0);
            buttonAction();
        });
        binding.questionButton4.setOnClickListener(v -> {
            if(Objects.equals(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[3], task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getRightAnswer())){
                viewModel.formResult(1);
            }
            else viewModel.formResult(0);
            buttonAction();
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

    private void buttonAction() {
        viewModel.increaseNumberOfQuestion();//увеличивается номер вопроса
    }
    private void setViews(){
        binding.question.setText(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getQuestion());
        binding.questionButton1.setText(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[0]);
        binding.questionButton2.setText(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[1]);
        binding.questionButton3.setText(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[2]);
        binding.questionButton4.setText(task.getList().get(viewModel.getNumberOfQuestionValue() - 1).getAnswers()[3]);
    }
}
