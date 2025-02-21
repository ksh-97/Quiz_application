package com.Quiz.quizApp.service;

import com.Quiz.quizApp.dao.QuestionDao;
import com.Quiz.quizApp.dao.QuizDao;
import com.Quiz.quizApp.model.QueResponse;
import com.Quiz.quizApp.model.Question;
import com.Quiz.quizApp.model.QuestionWrapper;
import com.Quiz.quizApp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQuestions, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
    }



    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);

        if (quiz.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
        }
        System.out.println("fetching questions --------------");

        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for(Question q : questions) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionWrappers.add(qw);
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<String> calculateResult(int id, List<QueResponse> submissions) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        for(int i=0; i<submissions.size(); i++) {
            System.out.println(submissions.get(i));
            if(submissions.get(i).getResponse().equals(questions.get(i).getRightAnswer())) right++;
        }

        return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(right));
    }
}
