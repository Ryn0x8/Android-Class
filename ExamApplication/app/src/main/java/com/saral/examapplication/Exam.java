package com.saral.examapplication;

public class Exam {
    String subject;
    String date;

    public Exam(String subject, String date) {
        this.subject = subject;
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }
}
