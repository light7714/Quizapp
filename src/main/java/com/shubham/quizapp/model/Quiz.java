package com.shubham.quizapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity //entity is not a component or bean managed by spring...
@Data
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ManyToMany
//    need this many to many mapping bcoz we have a quiz with multiple questions, and also a question can be present in multiple quizzes
//    a mapping table will automatically be created
//    so this field is not in Quiz table, but in the mapping table quiz_questions
    private List<Question> questions;
}
