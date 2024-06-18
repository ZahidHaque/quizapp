package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByDifficultylevel(String difficultylevel) {
        return questionDao.findByDifficultylevel(difficultylevel);
    }

    public void addQuestion(Question question) {
        questionDao.save(question);
    }

    public void deleteById(int id) {
        questionDao.deleteById(id);
    }
}
