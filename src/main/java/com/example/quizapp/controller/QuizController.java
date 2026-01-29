package com.example.quizapp.controller;

import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuizSubmission;
import com.example.quizapp.model.StudentResult;
import com.example.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/question")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(quizService.addQuestion(question));
    }

    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getQuestions() {
        return ResponseEntity.ok(quizService.getAllQuestions());
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitQuiz(@RequestBody QuizSubmission submission) {
        int score = quizService.evaluateSubmission(submission);
        return ResponseEntity.ok("Score: " + score);
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<StudentResult>> getLeaderboard() {
        return ResponseEntity.ok(quizService.getLeaderboard());
    }

    // for deleating questions
    @DeleteMapping("/question/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        boolean deleted = quizService.deleteQuestionById(id);
        if (deleted) {
            return ResponseEntity.ok("Question deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
