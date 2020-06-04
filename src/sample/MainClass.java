package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClass extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("MyTimer");
        Group root = new Group();
        primaryStage.setScene(new Scene(root, 600, 475));
        primaryStage.show();
        TimerWindow timer = new TimerWindow(root);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
