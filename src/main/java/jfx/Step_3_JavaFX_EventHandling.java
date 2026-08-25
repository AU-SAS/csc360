package jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Run this through jfx.Launcher, not directly: launching an Application subclass with the
// JavaFX jars on the classpath fails with "JavaFX runtime components are missing".
// jfx.Launcher explains why and works with no VM options.
public class Step_3_JavaFX_EventHandling extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create UI components
        Label messageLabel = new Label("Click the button!");
        Button clickButton = new Button("Click Me");

        // Event handling using lambda expression
        clickButton.setOnAction(e -> {
            messageLabel.setText("Button clicked at: " + System.currentTimeMillis());
        });

        // Layout
        VBox vbox = new VBox(15);
        vbox.setStyle("-fx-padding: 20;");
        vbox.getChildren().addAll(messageLabel, clickButton);

        // Scene and stage setup
        Scene scene = new Scene(vbox, 300, 150);
        primaryStage.setTitle("Event Handling Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
