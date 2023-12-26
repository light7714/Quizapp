package com.shubham.quizapp.controller;

import com.shubham.quizapp.model.Question;
import com.shubham.quizapp.model.QuestionWrapper;
import com.shubham.quizapp.model.Response;
import com.shubham.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
//url eg: /quiz/create?category=Java&numQ=5&title=JQuiz
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

//    we dont want to send rightAnswer field to client, so making a wrapper which will include only the fields we wanna send
     @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {
        return quizService.getQuizQuestions(id);
    }

//    url eg: /quiz/submit/{quizId} and body will have an array of objects, which will contain question id and a response field containing the option the user selected in string
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
        // id is quiz id
        return quizService.calculateResult(id, responses);
    }
}
