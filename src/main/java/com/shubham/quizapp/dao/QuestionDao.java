package com.shubham.quizapp.dao;

import com.shubham.quizapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository provides all methods like the crud methods without us having to write the data-jpa query language (JPQL (jpa ql) or HQL if using hibernate). We dont have to define the methods ourselves which are already provided
// 1st arg in class name which maps to the type of table in db, 2nd is type of pk
@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
//    we dont need to implement this method, just by defining it the jpa will do our job
    List<Question> findByCategory(String category);

//    now we will need to implement logic of this method
//     writing native query of postgres - :category means the variable accepted in arg
//    if nativeQuery = false, we'll have to write JPQL
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
