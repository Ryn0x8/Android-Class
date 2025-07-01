package com.saral.quizapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Quiz {

    HashMap<String, String> questions = new HashMap<>();

    public void setQuestion(String question, String answer){
        questions.put(question, answer);
    }

    public String getAnswer(String question){
         String ans = questions.get(question);
         questions.remove(question);
         return ans;
    }

    public String getQuestion(){
        if (questions.isEmpty()) return "No questions available";
        Random random = new Random();
        ArrayList<String> key = new ArrayList<>(questions.keySet());
        return key.get(random.nextInt(key.size()));
    }

}
