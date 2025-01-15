package com.filmhub.filmhubjavafx;

import com.filmhub.filmhubjavafx.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        new MainController().start();
    }

    public static void main(String[] args)
    {
        launch();
    }
}