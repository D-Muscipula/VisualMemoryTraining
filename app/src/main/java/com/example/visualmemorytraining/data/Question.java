package com.example.visualmemorytraining.data;

public class Question {
    private final String question;
    private final String[] answers = new String[4];
    private final String rightAnswer;

    public Question(String question, String answer1, String answer2, String answer3, String answer4, String rightAnswer) {
        this.question = question;
        answers[0] = answer1;
        answers[1] = answer2;
        answers[2] = answer3;
        answers[3] = answer4;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }
    public String[] getAnswers() {
        return answers;
    }
    public String getRightAnswer() {
        return rightAnswer;
    }
}
