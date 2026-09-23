package com.example.progevaluacionventana.VentanaPrincipalSources;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class VentanaPrincipalController {
    @FXML private ListView<String> listaActividad;

    @FXML
    private void abrirRegistro(ActionEvent event) {
        abrirVentana("RegistroClienteVista.fxml", "Registrar cliente");
    }

    @FXML
    private void abrirConsulta(ActionEvent event) {
        abrirVentana("consulta-clientes.fxml", "Consultar clientes");
    }

    private void abrirVentana(String archivo, String titulo) {
        URL ruta = getClass().getResource(
                "/com/example/progevaluacionventana/" + archivo);

        if (ruta == null) {
            new Alert(Alert.AlertType.WARNING,
                    " Falta Integracion  " + titulo).showAndWait();
            return;
        }

        try {
            Parent vista = FXMLLoader.load(ruta);
            Stage ventana = new Stage();
            ventana.initOwner(listaActividad.getScene().getWindow());
            ventana.initModality(Modality.WINDOW_MODAL);
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(vista));
            ventana.show();

            listaActividad.getItems().add(0, "Se abrio " + titulo);
        } catch (IOException error) {
            new Alert(Alert.AlertType.ERROR,
                    "No se puede abrir el archibo " + titulo.toLowerCase()).showAndWait();
            error.printStackTrace();
        }
    }

    @FXML
    private void copiarActividad(ActionEvent event) {
        String actividad = listaActividad.getSelectionModel().getSelectedItem();

        if (actividad == null) {
            new Alert(Alert.AlertType.WARNING,
                    "Elija una actividad").showAndWait();
            return;
        }

        ClipboardContent contenido = new ClipboardContent();
        contenido.putString(actividad);
        Clipboard.getSystemClipboard().setContent(contenido);
    }

    @FXML
    private void mostrarActividad(MouseEvent event) {
        if (event.getClickCount() == 2) {
            String actividad = listaActividad.getSelectionModel().getSelectedItem();
            if (actividad != null) {
                new Alert(Alert.AlertType.INFORMATION, actividad).showAndWait();
            }
        }
    }

    @FXML
    private void salir(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setHeaderText("¿Queres cerrar la aplicación?");
        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            Platform.exit();
        }
    }
}