package com.shubham.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor // gives parametrized and default constructor
public class Response {
    private Integer id;
    private String response;
}
