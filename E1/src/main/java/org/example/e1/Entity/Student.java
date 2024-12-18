package org.example.e1.Entity;

public class Student {
    private String maSV;
    private String hoTen;
    private double diemToan;
    private double diemLy;
    private double diemHoa;
    private double diemTrungBinh;

    public Student(String maSV, String hoTen, double diemToan, double diemLy, double diemHoa) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.diemToan = diemToan;
        this.diemLy = diemLy;
        this.diemHoa = diemHoa;
        this.diemTrungBinh = tinhDiemTrungBinh();
    }

    private double tinhDiemTrungBinh() {
        return (diemToan + diemLy + diemHoa) / 3;
    }

    public String getMaSV() { return maSV; }
    public String getHoTen() { return hoTen; }
    public double getDiemTrungBinh() { return diemTrungBinh; }

    @Override
    public String toString() {
        return String.format("Mã SV: %s - Họ Tên: %s - Điểm TB: %.2f",
                maSV, hoTen, diemTrungBinh);
    }
}

