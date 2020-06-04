package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class TimerWindow {

    private double mainTotalTime;

    TimerWindow(Group root){
        Line totalTimeLine = new Line(100,75,500,75);

        Button setTotalTimeButton = new Button();
        setTotalTimeButton.setLayoutX(150);
        setTotalTimeButton.setLayoutY(150);
        setTotalTimeButton.setPrefSize(300, 50);
        setTotalTimeButton.setText("SET TOTAL TIME");

        Button addSeparatorButton = new Button();
        addSeparatorButton.setLayoutX(150);
        addSeparatorButton.setLayoutY(250);
        addSeparatorButton.setPrefSize(300, 50);
        addSeparatorButton.setText("ADD SEPARATOR");

        Button launchButton = new Button();
        launchButton.setLayoutX(150);
        launchButton.setLayoutY(350);
        launchButton.setPrefSize(300, 50);
        launchButton.setText("LAUNCH TIMER");

        root.getChildren().addAll(totalTimeLine, setTotalTimeButton, addSeparatorButton, launchButton);

//==============================================================================

        SeparatorFactory separatorFactory = new SeparatorFactory();
        List<Integer> separatorList = new ArrayList<>();

        setTotalTimeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField setTotalTimeField = new TextField();
                setTotalTimeField.setPromptText("00.00.00");
                setTotalTimeField.setAlignment(Pos.CENTER);
                setTotalTimeField.setLayoutX(100);
                setTotalTimeField.setLayoutY(25);
                setTotalTimeField.setPrefSize(175, 50);

                Button startTotalTimeButton = new Button("save total time");
                startTotalTimeButton.setLayoutX(325);
                startTotalTimeButton.setLayoutY(25);
                startTotalTimeButton.setPrefSize(175, 50);

                Group totalTimeRoot = new Group();
                totalTimeRoot.getChildren().addAll(setTotalTimeField, startTotalTimeButton);
                Scene secondScene = new Scene(totalTimeRoot, 600, 100);

                Stage totalTimeWindow = new Stage();
                totalTimeWindow.setResizable(false);
                totalTimeWindow.setTitle("Total Time Setting");
                totalTimeWindow.setScene(secondScene);
                totalTimeWindow.show();

                startTotalTimeButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        char[] totalTimeTextArray = setTotalTimeField.getText().toCharArray();
                        int totalTime = Integer.parseInt(""+totalTimeTextArray[0])*36000;
                        totalTime += Integer.parseInt(""+totalTimeTextArray[1])*3600;
                        totalTime += Integer.parseInt(""+totalTimeTextArray[3])*600;
                        totalTime += Integer.parseInt(""+totalTimeTextArray[4])*60;
                        totalTime += Integer.parseInt(""+totalTimeTextArray[6])*10;
                        totalTime += Integer.parseInt(""+totalTimeTextArray[7]);
                        totalTimeWindow.close();
                        setTotalTime(totalTime);
                    }
                });
            }
        });

        addSeparatorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Button separatorX = separatorFactory.addSeparator(100,63, separatorList, mainTotalTime);
                root.getChildren().addAll(separatorX);
            }
        });

        launchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LaunchTimer launchTimer = new LaunchTimer(separatorList, mainTotalTime);
            }
        });
    }

    public void setTotalTime(int totalTime){
        mainTotalTime = totalTime;
    }
}


