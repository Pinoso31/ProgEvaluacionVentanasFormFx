module com.example.progevaluacionventana {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progevaluacionventana to javafx.fxml;
    exports com.example.progevaluacionventana;
}