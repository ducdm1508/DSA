module org.example.e1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.e1 to javafx.fxml;
    exports org.example.e1;
}