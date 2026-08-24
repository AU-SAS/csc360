package jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Step_0_JavaFX_Hello_World extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a Button
        Button btn = new Button("Click Me");

        // Set an action for the button
        btn.setOnAction(e -> System.out.println("Hello from JavaFX!"));

        // Create a layout and add the button to it
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Create a scene with the layout
        Scene scene = new Scene(root, 300, 200);

        // Set up the stage (window)
        primaryStage.setTitle("Hello JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}