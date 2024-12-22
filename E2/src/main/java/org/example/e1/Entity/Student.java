package org.example.e1.Entity;

public class Student {
    private String id;
    private String fullName;
    private double mathScore;
    private double physicsScore;
    private double chemistryScore;
    private double averageScore;

    public Student(String id, String fullName, double mathScore, double physicsScore, double chemistryScore) {
        this.id = id;
        this.fullName = fullName;
        this.mathScore = mathScore;
        this.physicsScore = physicsScore;
        this.chemistryScore = chemistryScore;
        this.averageScore = calculateAverageScore();
    }

    private double calculateAverageScore() {
        return (mathScore + physicsScore + chemistryScore) / 3;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public double getAverageScore() {
        return averageScore;
    }

    @Override
    public String toString() {
        return String.format("ID: %s - Name: %s - Average Score: %.2f",
                id, fullName, averageScore);
    }
}
