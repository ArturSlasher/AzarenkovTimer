package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class SeparatorFactory {
    private int separatorTimeX;

    public int getSeparatorTimeX() {
        return separatorTimeX;
    }

    public void setSeparatorTime(int separatorTime){ separatorTimeX = separatorTime; }

    public Button addSeparator(int layoutX, int layoutY, List separatorList, double tT){
        Button separatorX = new Button();
        separatorX.setLayoutX(layoutX);
        separatorX.setLayoutY(layoutY);

        separatorX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextField setSeparatorField = new TextField();
                setSeparatorField.setPromptText("00.00.00");
                setSeparatorField.setAlignment(Pos.CENTER);
                setSeparatorField.setLayoutX(100);
                setSeparatorField.setLayoutY(25);
                setSeparatorField.setPrefSize(175, 50);

                Button startSeparatorButton = new Button("save separator");
                startSeparatorButton.setLayoutX(325);
                startSeparatorButton.setLayoutY(25);
                startSeparatorButton.setPrefSize(175, 50);

                Group secondaryRoot = new Group();
                secondaryRoot.getChildren().addAll(startSeparatorButton, setSeparatorField);
                Scene secondScene = new Scene(secondaryRoot, 600, 100);

                Stage separatorWindow = new Stage();
                separatorWindow.setTitle("Separator Setting");
                separatorWindow.setScene(secondScene);
                separatorWindow.show();

                startSeparatorButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        char[] separatorTimeTextArray = setSeparatorField.getText().toCharArray();
                        separatorTimeX = Integer.parseInt(""+separatorTimeTextArray[0])*36000;
                        separatorTimeX+= Integer.parseInt(""+separatorTimeTextArray[1])*3600;
                        separatorTimeX += Integer.parseInt(""+separatorTimeTextArray[3])*600;
                        separatorTimeX += Integer.parseInt(""+separatorTimeTextArray[4])*60;
                        separatorTimeX += Integer.parseInt(""+separatorTimeTextArray[6])*10;
                        separatorTimeX += Integer.parseInt(""+separatorTimeTextArray[7]);
                        setSeparatorTime(separatorTimeX);
                        separatorX.setLayoutX(95 + separatorTimeX/tT*400);
                        separatorWindow.close();

                        separatorList.add(getSeparatorTimeX());
                    }
                });
            }
        });
        return separatorX;
    }
}
