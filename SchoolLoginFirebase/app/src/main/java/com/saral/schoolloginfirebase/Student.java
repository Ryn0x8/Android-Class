package com.saral.schoolloginfirebase;

public class Student {
    String name, email, number, school;

    public Student() {
    }

    public Student(String name, String email, String number, String school) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.school = school;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }
}

