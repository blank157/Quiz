package com.example.quizapp.model;

import java.util.Map;

public class QuizSubmission {
    private String studentName;
    private Map<Long, Integer> answers;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Map<Long, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Long, Integer> answers) {
        this.answers = answers;
    }
}
