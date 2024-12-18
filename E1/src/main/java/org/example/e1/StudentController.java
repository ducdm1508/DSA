package org.example.e1;

import javafx.event.ActionEvent;
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

    @FXML private TextField txtMaSV;
    @FXML private TextField txtHoTen;
    @FXML private TextField txtDiemToan;
    @FXML private TextField txtDiemLy;
    @FXML private TextField txtDiemHoa;
    @FXML private TextArea txtDanhSach;

    private List<Student> danhSachSinhVien = new ArrayList<>();

    @FXML
    protected void themSinhVien() {
        try {
            Student sv = new Student(
                    txtMaSV.getText(),
                    txtHoTen.getText(),
                    Double.parseDouble(txtDiemToan.getText()),
                    Double.parseDouble(txtDiemLy.getText()),
                    Double.parseDouble(txtDiemHoa.getText())
            );
            danhSachSinhVien.add(sv);
            capNhatDanhSach();
            lamSachForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void sapXepDiem() {
        sapXepTheoDiem();
        capNhatDanhSach();
    }

    private void sapXepTheoDiem() {
        int n = danhSachSinhVien.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (danhSachSinhVien.get(j).getDiemTrungBinh() <
                        danhSachSinhVien.get(j + 1).getDiemTrungBinh()) {
                    Student temp = danhSachSinhVien.get(j);
                    danhSachSinhVien.set(j, danhSachSinhVien.get(j + 1));
                    danhSachSinhVien.set(j + 1, temp);
                }
            }
        }
    }

    private void capNhatDanhSach() {
        txtDanhSach.clear();
        for (Student sv : danhSachSinhVien) {
            txtDanhSach.appendText(sv.toString() + "\n");
        }
    }

    private void lamSachForm() {
        txtMaSV.clear();
        txtHoTen.clear();
        txtDiemToan.clear();
        txtDiemLy.clear();
        txtDiemHoa.clear();
    }
}