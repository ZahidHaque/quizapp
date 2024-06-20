package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.model.Question;
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
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
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
