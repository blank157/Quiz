package com.example.quizapp.service;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSubmission;
import com.example.quizapp.model.StudentResult;
import com.example.quizapp.repository.QuestionRepository;
import com.example.quizapp.repository.StudentResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepo;

    @Autowired
    private StudentResultRepository resultRepo;

    public Question addQuestion(Question q) {
        return questionRepo.save(q);
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public int evaluateSubmission(QuizSubmission submission) {
        int score = 0;
        for (Map.Entry<Long, Integer> entry : submission.getAnswers().entrySet()) {
            Optional<Question> qOpt = questionRepo.findById(entry.getKey());
            if (qOpt.isPresent()) {
                Question question = qOpt.get();
                int selectedOptionIndex = entry.getValue();
                String selectedAnswer = switch (selectedOptionIndex) {
                    case 0 -> question.getOptionA();
                    case 1 -> question.getOptionB();
                    case 2 -> question.getOptionC();
                    case 3 -> question.getOptionD();
                    default -> null;
                };

                if (selectedAnswer != null && selectedAnswer.equalsIgnoreCase(question.getCorrectAnswer())) {
                    score++;
                }
            }
        }

        StudentResult result = new StudentResult();
        result.setStudentName(submission.getStudentName());
        result.setScore(score);
        resultRepo.save(result);

        return score;
    }

    public List<StudentResult> getLeaderboard() {
        return resultRepo.findTop10ByOrderByScoreDesc();
    }

    //  method is to support delets in controller
    public boolean deleteQuestionById(Long id) {
        if (questionRepo.existsById(id)) {
            questionRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
