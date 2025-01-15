module com.filmhub.filmhubjavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.filmhub.filmhubjavafx to javafx.fxml;
    exports com.filmhub.filmhubjavafx;
}