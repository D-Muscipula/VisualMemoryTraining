package com.example.visualmemorytraining;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class QuestionFragmentViewModel extends ViewModel {
    private int numberOfQuestionValue = 1;
    private final ArrayList<Integer> scoresValue = new ArrayList<>();
    //private int scoresValue = 0;
    private final MutableLiveData<Integer> numberOfQuestion = new MutableLiveData<>();
    //private final MutableLiveData<Integer> scores = new MutableLiveData<>();
    private final MutableLiveData<ArrayList<Integer>> scores = new MutableLiveData<>();

    public MutableLiveData<Integer> getNumberOfQuestion() {
        return numberOfQuestion;
    }


    public int getNumberOfQuestionValue() {
        return numberOfQuestionValue;
    }

    public void increaseNumberOfQuestion() {
        numberOfQuestionValue++;
        numberOfQuestion.setValue(numberOfQuestionValue);
    }

    public ArrayList<Integer> getScoresValue() {
        return scoresValue;
    }

    public MutableLiveData<ArrayList<Integer>> getScores() {
        return scores;
    }
    public void formResult(int r){
        scoresValue.add(r);
        scores.setValue(scoresValue);
    }
    public int countScores(){
        int sum = 0;
        for (int x:scoresValue) {
            sum+=x;
        }
        return sum;
    }

}
