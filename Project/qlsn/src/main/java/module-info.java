module org.example.qlsn {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.qlsn to javafx.fxml;
    exports org.example.qlsn;
}