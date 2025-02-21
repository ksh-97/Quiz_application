package com.Quiz.quizApp.dao;

import com.Quiz.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsByCategory(String category);

    @Query("SELECT DISTINCT q.category FROM Question q")
    List<String> getDistinctCategories();

    @Query(value = "SELECT * FROM Question q WHERE q.category=:cat ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String cat, int numQ);
}
