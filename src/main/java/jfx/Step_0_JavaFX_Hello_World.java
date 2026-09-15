package jfx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Step 0 -- the smallest complete JavaFX application: a 300x200 window holding one button that
 * prints to the console when clicked.
 *
 * <p>It deliberately does almost nothing, because the subject of this step is the four pieces every
 * JavaFX application is assembled from, not what it draws.
 *
 * <ul>
 *   <li>{@link Application} -- the base class. JavaFX starts the toolkit and its rendering thread,
 *       then calls {@link #start(Stage)} on the JavaFX Application Thread. There is no main loop to
 *       write, and {@link Application#launch} does not return until the last window closes.</li>
 *   <li>{@link Stage} -- the window, handed to {@code start}. Nothing appears on screen until
 *       {@link Stage#show()} is called, which is the last thing {@code start} does here.</li>
 *   <li>{@link Scene} -- the contents of the window, and where the window's size is set. A stage
 *       shows one scene at a time; swapping scenes is how you change screens.</li>
 *   <li>The scene graph -- a tree of nodes under a single root, here a {@link StackPane} holding one
 *       {@link Button}. Layout panes position their children, controls are the leaves.</li>
 * </ul>
 *
 * <p>Worth contrasting with the Swing demos in {@code practice} and {@code atvt}. There, drawing
 * means overriding {@code paintComponent} and issuing {@code Graphics2D} calls on every repaint --
 * an immediate-mode model, where nothing remembers what was drawn. Here nothing is painted by hand:
 * the scene graph is retained, and JavaFX renders it, repaints damaged regions, and handles hit
 * testing for the button. That difference, not the syntax, is the reason to learn both.
 *
 * <p><strong>Run this through {@link Launcher}, not directly.</strong> Launching an
 * {@code Application} subclass while the JavaFX jars are on the classpath -- which is what an IDE
 * Run button on this class does -- fails with "JavaFX runtime components are missing, and are
 * required to run this application". {@code Launcher} explains the cause and needs no VM options.
 *
 * @see Launcher
 * @see Step_1_JavaFX_Layout
 */
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