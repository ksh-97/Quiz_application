package com.Quiz.quizApp.controller;

import com.Quiz.quizApp.model.QueResponse;
import com.Quiz.quizApp.model.QuestionWrapper;
import com.Quiz.quizApp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
     QuizService quizService;

    //    creating quiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQuestions, @RequestParam String title ) {
        return quizService.createQuiz(category, numQuestions, title);
    }

    //fetching a quiz by id
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    //Submitting a quiz
    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable int id, @RequestBody List<QueResponse> submissions) {
        return quizService.calculateResult(id, submissions);
    }

}
