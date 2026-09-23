package com.example.progevaluacionventana.InicioSesionSources;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class InicioSesionController {
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;

    @FXML
    private void iniciarSesion(ActionEvent event) {
        abrirMenuPrincipal();
    }

    @FXML
    private void iniciarConEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            abrirMenuPrincipal();
        }
    }

    private void abrirMenuPrincipal() {
        if (txtUsuario.getText().trim().isEmpty()
                || txtContrasena.getText().isEmpty()) {
            Alert aviso = new Alert(Alert.AlertType.WARNING);
            aviso.setTitle("Datos incompletos");
            aviso.setHeaderText(null);
            aviso.setContentText("Ingresá el usuario y la contraseña.");
            aviso.showAndWait();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/progevaluacionventana/ventana-principal.fxml"));
            Scene escena = new Scene(loader.load());
            Stage ventana = (Stage) txtUsuario.getScene().getWindow();
            ventana.setScene(escena);
            ventana.setTitle("Menú principal");
            ventana.centerOnScreen();
        } catch (IOException error) {
            new Alert(Alert.AlertType.ERROR,
                    "No se pudo abrir la ventana principal.").showAndWait();
            error.printStackTrace();
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("¿Querés cerrar la aplicación?");
        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            Platform.exit();
        }
    }
}