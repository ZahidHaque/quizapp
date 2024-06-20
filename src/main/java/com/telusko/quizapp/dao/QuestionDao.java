package com.telusko.quizapp.dao;

import com.telusko.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByDifficultylevel(String Difficultylevel);

    @Query(value = "SELECT * FROM QUESTION Q WHERE Q.DIFFICULTYLEVEL = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionByDifficultylevel(String category, int numQ);
}
