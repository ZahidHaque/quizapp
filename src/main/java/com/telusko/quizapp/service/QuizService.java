package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuizDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    QuizDao quizDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        return null;
    }
}
