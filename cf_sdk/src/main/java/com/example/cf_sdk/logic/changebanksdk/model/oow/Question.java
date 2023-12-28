package com.example.cf_sdk.logic.changebanksdk.model.oow;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *
 * Representation of an OOW question.
 */

public class Question {

    @SerializedName("type")
    private String mType;

    @SerializedName("question")
    private String mQuestion;

    @SerializedName("possibleAnswers")
    private List<String> mPossibleAnswers;

    public static Question create(String type, String question, List<String> possibleAnswers) {
        return new Question(type, question, possibleAnswers);
    }

    private Question(String type, String question, List<String> possibleAnswers) {
        mType = type;
        mQuestion = question;
        mPossibleAnswers = possibleAnswers;
    }

    public String getType() {
        return mType;
    }

    public Question withType(String type) {
        return new Question(type, mQuestion, mPossibleAnswers);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public Question withQuestion(String question) {
        return new Question(mType, question, mPossibleAnswers);
    }

    public List<String> getPossibleAnswers() {
        return mPossibleAnswers;
    }

    public Question withPossibleAnswers(List<String> answers) {
        return new Question(mType, mQuestion, answers);
    }
}
