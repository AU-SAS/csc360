import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Step_2_JavaFX_Controls extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create Labels
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        // Create TextField and PasswordField
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();

        // Create Button
        Button loginButton = new Button("Login");

        // Create output label
        Label messageLabel = new Label();

        // Set action on button
        loginButton.setOnAction(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            if (user.equals("admin") && pass.equals("1234")) {
                messageLabel.setText("Login Successful!");
            } else {
                messageLabel.setText("Invalid credentials.");
            }
        });

        // Layout using GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(messageLabel, 1, 3);

        // Scene and Stage setup
        Scene scene = new Scene(grid, 350, 200);
        primaryStage.setTitle("JavaFX Basic Controls");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}