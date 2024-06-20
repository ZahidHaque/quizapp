package com.telusko.quizapp.controller;

import com.telusko.quizapp.model.Question;
import com.telusko.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{difficultylevel}")
    public List<Question> getQuestionsByDifficultylevel(@PathVariable String difficultylevel){
        return questionService.getQuestionsByDifficultylevel(difficultylevel);
    }

    /*
    愛里さんがよろしくと伝えていました。
    */

    @PostMapping("add")
    public String addQuestion(@RequestBody Question question){
        questionService.addQuestion(question);
        return "Question added";
    }

    @DeleteMapping("delete/{id}")
    public String deleteQuestion(@PathVariable int id){
        questionService.deleteById(id);
        return id+"th Question deleted";
    }


}
