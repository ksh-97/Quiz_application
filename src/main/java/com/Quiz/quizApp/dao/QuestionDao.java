package com.Quiz.quizApp.dao;

import com.Quiz.quizApp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> getQuestionsByCategory(String category);

    @Query("SELECT DISTINCT q.category FROM Question q")
    List<String> getDistinctCategories();
}
