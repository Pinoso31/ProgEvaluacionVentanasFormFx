package com.example.progevaluacionventana.RegistroClienteSources;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegistroClienteApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroClienteApplication.class.getResource("RegistroClienteVista.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Registro Cliente");
        stage.setScene(scene);
        stage.show();
    }


}
