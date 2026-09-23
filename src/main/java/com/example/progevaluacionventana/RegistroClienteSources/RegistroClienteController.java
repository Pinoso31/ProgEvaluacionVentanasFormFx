package com.example.progevaluacionventana.RegistroClienteSources;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class RegistroClienteController {

    @FXML
    private TextField txtNombresCliente;

    @FXML
    private TextField txtApellidosCliente;

    @FXML
    private ComboBox<String> cmbTipoCliente;

    @FXML
    private ComboBox<String> cmbCuidadCliente;

    @FXML
    private DatePicker dtpFechaNacimientoCliente;

}
