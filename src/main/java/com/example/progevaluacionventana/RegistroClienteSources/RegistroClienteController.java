package com.example.progevaluacionventana.RegistroClienteSources;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

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

    @FXML

    private RadioButton rbAfilacion;

    @FXML
    private RadioButton rbConsulta;

    @FXML
    private RadioButton rbReclamacion;

    @FXML
    private CheckBox chkSoporteTecnico;

    @FXML
    private CheckBox chkMantenimientoEquipos;

    @FXML
    private CheckBox chkDesarrolloWeb;

    @FXML
    private TextField txtFotografia;

    @FXML
    private Button btnSeleccionarFotoDirectorio;

    @FXML
    private Button btnGuardarRegistroCliente;











    @FXML
    public void initialize() {
        // 1. Unicidad para RadioButtons (Mediante ToggleGroup oficial de JavaFX)
        ToggleGroup grupoSolicitud = new ToggleGroup();
        rbAfilacion.setToggleGroup(grupoSolicitud);
        rbConsulta.setToggleGroup(grupoSolicitud);
        rbReclamacion.setToggleGroup(grupoSolicitud);

        // 2. Unicidad para CheckBoxes (Listeners para desmarcar el resto al activar uno)
        chkSoporteTecnico.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                chkMantenimientoEquipos.setSelected(false);
                chkDesarrolloWeb.setSelected(false);
            }
        });

        chkMantenimientoEquipos.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                chkSoporteTecnico.setSelected(false);
                chkDesarrolloWeb.setSelected(false);
            }
        });

        chkDesarrolloWeb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                chkSoporteTecnico.setSelected(false);
                chkMantenimientoEquipos.setSelected(false);
            }
        });
    }


    // MÉTODO VALIDAR FORMULARIO (Evita errores de compilación)

    private boolean validarFormulario() {
        // 1. Validar campos de texto (Nombres, Apellidos y Fotografía)
        if (txtNombresCliente.getText() == null || txtNombresCliente.getText().trim().isEmpty() ||
                txtApellidosCliente.getText() == null || txtApellidosCliente.getText().trim().isEmpty() ||
                txtFotografia.getText() == null || txtFotografia.getText().trim().isEmpty()) {

            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, completa todos los campos de texto.");
            return false;
        }

        // 2. Validar ComboBox (Tipo de cliente y Ciudad)
        if (cmbTipoCliente.getValue() == null || cmbCuidadCliente.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, selecciona el tipo de cliente y la ciudad.");
            return false;
        }

        // 3. Validar DatePicker (Fecha de nacimiento)
        if (dtpFechaNacimientoCliente.getValue() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, selecciona una fecha de nacimiento válida.");
            return false;
        }

        // 4. Validar RadioButton (Tipo de solicitud)
        if (!rbAfilacion.isSelected() && !rbConsulta.isSelected() && !rbReclamacion.isSelected()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, selecciona un tipo de solicitud.");
            return false;
        }

        // 5. Validar CheckBox (Servicios de interés)
        if (!chkSoporteTecnico.isSelected() && !chkMantenimientoEquipos.isSelected() && !chkDesarrolloWeb.isSelected()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, selecciona un servicio de interés.");
            return false;
        }

        return true;
    }


    private void mostrarAlerta(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }












    @FXML
    private void selecionarFotografiaCliente(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar Documento de Identificación");
        fc.setInitialDirectory(new File("C:\\"));

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("Imagen PNG", "*.png"),
                new FileChooser.ExtensionFilter("Imagen JPG", "*.jpg")
        );


        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File file = fc.showOpenDialog(stage);

        if (file != null) {
            txtFotografia.setText(file.getAbsolutePath());
        }
    }
}
