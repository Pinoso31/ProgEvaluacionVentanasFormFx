module com.example.progevaluacionventana {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.progevaluacionventana to javafx.fxml;
    opens com.example.progevaluacionventana.InicioSesionSources to javafx.fxml;
    opens com.example.progevaluacionventana.VentanaPrincipalSources to javafx.fxml;
    opens com.example.progevaluacionventana.RegistroClienteSources to javafx.fxml;
    opens com.example.progevaluacionventana.ConsultaClientesSources to javafx.fxml;

    exports com.example.progevaluacionventana;
}