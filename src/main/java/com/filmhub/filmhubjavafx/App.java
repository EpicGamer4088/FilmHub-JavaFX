package com.filmhub.filmhubjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class App extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        BorderPane root = new BorderPane();

        HBox searchBar = new HBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Search for a Movie or a Show...");
        Button searchButton = new Button("Search");
        searchBar.getChildren().addAll(searchField, searchButton);

        ListView<String> resultsList = new ListView<>();

        searchButton.setOnAction(e -> {
            String query = searchField.getText();
            if (!query.isEmpty())
            {
                resultsList.getItems().clear();
                resultsList.getItems().addAll("Film 1", "Film 2", "Serie 1");
            }
        });

        root.setTop(searchBar);
        root.setCenter(resultsList);

        Scene scene = new Scene(root, 800, 600);

        scene.getStylesheets().add(
                Objects.requireNonNull(getClass().getResource("/css/style.css")).toExternalForm()
        );

        primaryStage.setTitle("Film Browser");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
