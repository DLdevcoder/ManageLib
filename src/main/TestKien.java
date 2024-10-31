package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class TestKien extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon_app.png")));
        FXMLLoader loader = new FXMLLoader(Paths.get("src/views/members/MemberList.fxml").toUri().toURL());
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Paths.get("src/resources/Frame.css").toUri().toString());
        primaryStage.setTitle("ManageLib");
        primaryStage.setWidth(1350);
        primaryStage.setHeight(750);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}