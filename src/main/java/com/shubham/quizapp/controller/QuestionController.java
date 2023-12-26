package com.shubham.quizapp.controller;

import com.shubham.quizapp.model.Question;
import com.shubham.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

//    so the final url will be /question/allQuestions
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

//    @GetMapping("allQuestions")
//    public ResponseEntity<List<Question>> getAllQuestions() {
////        1st arg is actual data, 2nd is status code
//        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK)
//    }

//    so the final url will be /question/category/{category}
    @GetMapping("category/{type}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable("type") String category) {
        return questionService.getQuestionsByCategory(category);
    }

//    spring automatically converts the sent JSON object from client side to java object here. similarly, we send string as return type and it converts it to JSON
//    so url for post req will be /question/add
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

//    spring automatically converts the string id written in url to integer
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }
}
