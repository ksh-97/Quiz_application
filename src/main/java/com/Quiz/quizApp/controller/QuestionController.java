package com.Quiz.quizApp.controller;

import com.Quiz.quizApp.model.Question;
import com.Quiz.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category")
    public List<String> getAllCategory() {
        return questionService.getAllCategory();
    }

    @GetMapping("category/{catname}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String catname) {
        return questionService.getQuestionsByCategory(catname);
    }

    //Adding a new question
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        System.out.println(question);
        return questionService.addQuestion(question);
    }


}
