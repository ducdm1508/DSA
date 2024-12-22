package org.example.e1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.e1.Entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    @FXML
    private Label welcomeText;

    @FXML private TextField txtStudentID;
    @FXML private TextField txtFullName;
    @FXML private TextField txtMathScore;
    @FXML private TextField txtPhysicsScore;
    @FXML private TextField txtChemistryScore;
    @FXML private TextArea txtStudentList;

    private List<Student> studentList = new ArrayList<>();

    @FXML
    protected void addStudent() {
        try {
            Student student = new Student(
                    txtStudentID.getText(),
                    txtFullName.getText(),
                    Double.parseDouble(txtMathScore.getText()),
                    Double.parseDouble(txtPhysicsScore.getText()),
                    Double.parseDouble(txtChemistryScore.getText())
            );
            studentList.add(student);
            updateStudentList();
            clearForm();
        } catch (Exception e) {
            txtStudentList.setText("Error: Please check the input fields.");
            e.printStackTrace();
        }
    }

    @FXML
    protected void sortByScore() {
        if (studentList.isEmpty()) {
            txtStudentList.setText("No data available to sort.");
            return;
        }
        sortStudentsByScore();
        updateStudentList();
    }

    private void sortStudentsByScore() {
        int n = studentList.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (studentList.get(j).getAverageScore() > studentList.get(maxIndex).getAverageScore()) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                Student temp = studentList.get(i);
                studentList.set(i, studentList.get(maxIndex));
                studentList.set(maxIndex, temp);
            }
        }
    }

    private void updateStudentList() {
        txtStudentList.clear();
        for (Student student : studentList) {
            txtStudentList.appendText(student.toString() + "\n");
        }
    }

    private void clearForm() {
        txtStudentID.clear();
        txtFullName.clear();
        txtMathScore.clear();
        txtPhysicsScore.clear();
        txtChemistryScore.clear();
    }
}
