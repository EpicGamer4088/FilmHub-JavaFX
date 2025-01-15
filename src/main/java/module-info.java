module com.filmhub.filmhubjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.filmhub.filmhubjavafx to javafx.fxml;
    exports com.filmhub.filmhubjavafx;
    exports com.filmhub.filmhubjavafx.utils;
    opens com.filmhub.filmhubjavafx.utils to javafx.fxml;
}