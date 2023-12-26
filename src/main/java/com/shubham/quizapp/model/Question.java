package com.shubham.quizapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

//Model

@Data // lombok will automatically make getters and setters in ? (https://stackoverflow.com/questions/36526286/view-lombok-generated-code-in-intellij-idea)
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    in sql there is snake casing (with underscore) and in java theres camelcase, so JPA will handle it (converting cases to look for correct variable)
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
//    in db, if difficultylevel was written as it is, not in snake case, we will not put capital L here
    private String difficultyLevel;
    private String category;


}
