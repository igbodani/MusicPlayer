module MusicPlayer.main {
    requires javafx.graphics;
    requires javafx.media;
    requires javafx.fxml;
    requires javafx.controls;
    opens edu.isu.project;
    exports edu.isu.project to com.google.gson;
}