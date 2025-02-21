package com.Quiz.quizApp.service;

import com.Quiz.quizApp.model.Question;
import com.Quiz.quizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        System.out.println("Getting all questions ----");
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String catname) {
        System.out.println("Fetching by category ----");
        try{
            return new ResponseEntity<> (questionDao.getQuestionsByCategory(catname), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


    }

    public List<String> getAllCategory() {
        System.out.println("Fetching all category ----");
        List<String> catList = questionDao.getDistinctCategories();
//        System.out.println(catList); // Check if data is populated correctly
        return catList;
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);
        try {
            return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Could not add", HttpStatus.BAD_REQUEST);
    }

}
