package com.example.accessingdatamysql;

import org.apache.coyote.Response;

import java.util.ArrayList;
import java.util.List;

public class ResponseObjectForAverageUserScore {
    int total_score;
    List<CallT> scores = new ArrayList<>();

    public ResponseObjectForAverageUserScore(int total_score, List<CallT> scores) {
        this.total_score = total_score;
        this.scores = scores;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public void setScores(List<CallT> scores) {
        this.scores = scores;
    }

    public int getTotal_score() {
        return total_score;
    }

    public List<CallT> getScores() {
        return scores;
    }
}
