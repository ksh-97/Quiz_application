package com.Quiz.quizApp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class QueResponse {
    private Integer id;
    private String response;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public QueResponse(Integer id, String response) {
        this.id = id;
        this.response = response;
    }
}
