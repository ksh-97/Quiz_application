package com.Quiz.quizApp.service;

import com.Quiz.quizApp.Question;
import com.Quiz.quizApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        System.out.println("Getting all questions ----");
        List<Question> questions = questionDao.findAll();
//        System.out.println(questions); // Check if data is populated correctly
        return questions;
    }

    public List<Question> getQuestionsByCategory(String catname) {
        System.out.println("Fetching by category ----");
        List<Question> questions = questionDao.getQuestionsByCategory(catname);
//        System.out.println(questions); // Check if data is populated correctly
        return questions;
    }

    public List<String> getAllCategory() {
        System.out.println("Fetching all category ----");
        List<String> catList = questionDao.getDistinctCategories();
//        System.out.println(catList); // Check if data is populated correctly
        return catList;
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Question added successfully";
    }

}
