package com.telusko.quizapp.service;

import com.telusko.quizapp.dao.QuestionDao;
import com.telusko.quizapp.dao.QuizDao;
import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.model.QuestionWrapper;
import com.telusko.quizapp.model.Quiz;
import com.telusko.quizapp.model.RightAnswerWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionByDifficultylevel(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for (Question question : questions) {
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(),question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<List<Integer>> calculateScore(List<RightAnswerWrapper> rightAnswerWrapper) {
        List<Integer> scores = new ArrayList<>();
        for (RightAnswerWrapper rightAnswer : rightAnswerWrapper) {
            Optional<Question> question = questionDao.findById(rightAnswer.getId());
            if (question.isPresent()) {
                if(question.get().getRightAnswer().equals(rightAnswer.getAns())){
                    scores.add(1);
                }
                else if (question.get().getRightAnswer().equals(rightAnswer.getAns())){
                    scores.add(2);
                }
                else scores.add(0);
            }
        }
        return new ResponseEntity<>(scores, HttpStatus.OK);
    }
}
