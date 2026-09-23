package com.example.progevaluacionventana.RegistroClienteSources;

import com.example.progevaluacionventana.models.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegistroClienteController {
    private static final ObservableList<Cliente> CLIENTES =
            FXCollections.observableArrayList();

    private static final Path ARCHIVO = Path.of(
            System.getProperty("user.home"), "clientes-registrados.dat"
    );

    static {
        if (Files.exists(ARCHIVO)) {
            try (ObjectInputStream entrada =
                         new ObjectInputStream(Files.newInputStream(ARCHIVO))) {
                Object datos = entrada.readObject();

                if (datos instanceof java.util.List<?> lista) {
                    for (Object objeto : lista) {
                        if (objeto instanceof Cliente cliente) {
                            CLIENTES.add(cliente);
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException error) {
                System.err.println(
                        "No se pudo cargar el archivo de clientes: "
                                + error.getMessage()
                );
            }
        }
    }

    @FXML private TextField txtNombresCliente;
    @FXML private TextField txtApellidosCliente;
    @FXML private ComboBox<String> cmbTipoCliente;
    @FXML private ComboBox<String> cmbCuidadCliente;
    @FXML private DatePicker dtpFechaNacimientoCliente;
    @FXML private RadioButton rbConsulta;
    @FXML private RadioButton rbReclamo;
    @FXML private RadioButton rbSolicitud;
    @FXML private CheckBox chkSoporte;
    @FXML private CheckBox chkMantenimiento;
    @FXML private CheckBox chkInstalacion;
    @FXML private TextField txtRutaFoto;
    @FXML private ImageView vistaFoto;

    private final ToggleGroup tipoSolicitud = new ToggleGroup();

    public static ObservableList<Cliente> getClientes() {
        return CLIENTES;
    }

    @FXML
    private void initialize() {
        cmbTipoCliente.getItems().setAll("Persona natural", "Empresa");

        cmbCuidadCliente.getItems().setAll(
                "Managua", "Masaya", "Granada", "León", "Chinandega",
                "Estelí", "Matagalpa", "Jinotega", "Rivas", "Carazo",
                "Boaco", "Chontales", "Nueva Segovia", "Madriz",
                "Río San Juan", "Costa Caribe Norte", "Costa Caribe Sur"
        );

        rbConsulta.setToggleGroup(tipoSolicitud);
        rbReclamo.setToggleGroup(tipoSolicitud);
        rbSolicitud.setToggleGroup(tipoSolicitud);
        txtRutaFoto.setEditable(false);
    }

    @FXML
    private void seleccionarFoto() {
        FileChooser selector = new FileChooser();
        selector.setTitle("Seleccionar fotografía");
        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Fotografías", "*.png", "*.jpg", "*.jpeg", "*.gif"
                )
        );

        File archivo = selector.showOpenDialog(
                (Stage) txtRutaFoto.getScene().getWindow()
        );

        if (archivo != null) {
            txtRutaFoto.setText(archivo.getAbsolutePath());
            vistaFoto.setImage(new Image(archivo.toURI().toString()));
        }
    }

    @FXML
    private void guardar() {
        String nombres = txtNombresCliente.getText().trim();
        String apellidos = txtApellidosCliente.getText().trim();
        LocalDate nacimiento = dtpFechaNacimientoCliente.getValue();
        RadioButton solicitud =
                (RadioButton) tipoSolicitud.getSelectedToggle();

        String servicios = Stream.of(
                        chkSoporte, chkMantenimiento, chkInstalacion
                )
                .filter(CheckBox::isSelected)
                .map(CheckBox::getText)
                .collect(Collectors.joining(", "));

        if (nombres.isEmpty()
                || apellidos.isEmpty()
                || cmbTipoCliente.getValue() == null
                || cmbCuidadCliente.getValue() == null
                || nacimiento == null
                || solicitud == null
                || servicios.isEmpty()
                || txtRutaFoto.getText().isEmpty()) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Completá todos los campos y seleccioná la fotografía."
            ).showAndWait();
            return;
        }

        if (!nacimiento.isBefore(LocalDate.now())) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Ingresá una fecha de nacimiento anterior a hoy."
            ).showAndWait();
            return;
        }

        Date fecha = Date.from(
                nacimiento.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );

        Cliente cliente = new Cliente(
                nombres,
                apellidos,
                cmbTipoCliente.getValue(),
                cmbCuidadCliente.getValue(),
                fecha,
                solicitud.getText(),
                servicios
        );
        cliente.setRutaFoto(txtRutaFoto.getText());

        try (ObjectOutputStream salida =
                     new ObjectOutputStream(Files.newOutputStream(ARCHIVO))) {
            java.util.ArrayList<Cliente> todos =
                    new java.util.ArrayList<>(CLIENTES);
            todos.add(cliente);
            salida.writeObject(todos);
        } catch (IOException error) {
            new Alert(
                    Alert.AlertType.ERROR,
                    "No se pudo guardar el cliente: " + error.getMessage()
            ).showAndWait();
            return;
        }

        CLIENTES.add(cliente);
        new Alert(
                Alert.AlertType.INFORMATION,
                "Cliente guardado correctamente."
        ).showAndWait();
        limpiar();
    }

    @FXML
    private void limpiar() {
        txtNombresCliente.clear();
        txtApellidosCliente.clear();
        cmbTipoCliente.getSelectionModel().clearSelection();
        cmbCuidadCliente.getSelectionModel().clearSelection();
        dtpFechaNacimientoCliente.setValue(null);
        tipoSolicitud.selectToggle(null);
        chkSoporte.setSelected(false);
        chkMantenimiento.setSelected(false);
        chkInstalacion.setSelected(false);
        txtRutaFoto.clear();
        vistaFoto.setImage(null);
    }

    @FXML
    private void cancelar() {
        ((Stage) txtNombresCliente.getScene().getWindow()).close();
    }
}