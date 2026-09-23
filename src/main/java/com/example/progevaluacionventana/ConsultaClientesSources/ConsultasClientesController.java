package com.example.progevaluacionventana.ConsultaClientesSources;

import com.example.progevaluacionventana.RegistroClienteSources.RegistroClienteController;
import com.example.progevaluacionventana.models.Cliente;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ConsultasClientesController {

    @FXML private TextField txtBuscar;
    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colTipoCliente;
    @FXML private TableColumn<Cliente, String> colCiudad;
    @FXML private TableColumn<Cliente, String> colFechaNacimiento;
    @FXML private TableColumn<Cliente, String> colTipoSolicitud;

    private final FilteredList<Cliente> filtrados =
            new FilteredList<>(RegistroClienteController.getClientes());

    @FXML
    private void initialize() {
        colNombre.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getNombre() + " "
                                + c.getValue().getApellido()
                )
        );

        colTipoCliente.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getTipoCliente())
        );

        colCiudad.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getCuidad())
        );

        colFechaNacimiento.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getFechaNacimiento()
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                )
        );

        colTipoSolicitud.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getTipoSolicitud())
        );

        tablaClientes.setItems(filtrados);
    }

    @FXML
    private void buscar() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        filtrados.setPredicate(c ->
                (c.getNombre() + " " + c.getApellido())
                        .toLowerCase()
                        .contains(texto)
        );
    }

    @FXML
    private void actualizar() {
        buscar();
        tablaClientes.refresh();
    }

    @FXML
    private void verDetalle() {
        Cliente c = tablaClientes.getSelectionModel().getSelectedItem();

        if (c == null) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Seleccioná un cliente de la tabla."
            ).showAndWait();
            return;
        }

        new Alert(
                Alert.AlertType.INFORMATION,
                "Cliente: " + c.getNombre() + " " + c.getApellido()
                        + "\nTipo: " + c.getTipoCliente()
                        + "\nCiudad: " + c.getCuidad()
                        + "\nSolicitud: " + c.getTipoSolicitud()
                        + "\nServicios: " + c.getServiciosInteres()
                        + "\nFotografía: " + c.getRutaFoto()
        ).showAndWait();
    }

    @FXML
    private void dobleClic(MouseEvent evento) {
        if (evento.getClickCount() == 2
                && tablaClientes.getSelectionModel().getSelectedItem() != null) {
            verDetalle();
        }
    }

    @FXML
    private void cerrar() {
        ((Stage) tablaClientes.getScene().getWindow()).close();
    }
}