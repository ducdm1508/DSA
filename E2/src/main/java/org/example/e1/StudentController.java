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
        for (int i = 1; i < studentList.size(); i++) {
            Student key = studentList.get(i);
            int j = i - 1;

            while (j >= 0 && studentList.get(j).getAverageScore() < key.getAverageScore()) {
                studentList.set(j + 1, studentList.get(j));
                j--;
            }
            studentList.set(j + 1, key);
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
