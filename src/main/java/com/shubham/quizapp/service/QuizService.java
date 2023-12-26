package com.shubham.quizapp.service;

import com.shubham.quizapp.dao.QuestionDao;
import com.shubham.quizapp.dao.QuizDao;
import com.shubham.quizapp.model.Question;
import com.shubham.quizapp.model.QuestionWrapper;
import com.shubham.quizapp.model.Quiz;
import com.shubham.quizapp.model.Response;
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
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
//        will automatically get data from mapping table and return
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q: questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        //findbyid gives optional, so we put get() (better way: ifPresent() first, OR, like above, Optional<Quiz> quiz, and then check)
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for(Response response: responses) {
            if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
                right++;
            }
            i++;
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
