import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Step_1_JavaFX_Layout extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create tabs
        TabPane tabPane = new TabPane();

        // VBox tab
        Tab vboxTab = new Tab("VBox", createVBoxLayout());
        vboxTab.setClosable(false);

        // HBox tab
        Tab hboxTab = new Tab("HBox", createHBoxLayout());
        hboxTab.setClosable(false);

        // GridPane tab
        Tab gridTab = new Tab("GridPane", createGridLayout());
        gridTab.setClosable(false);

        // StackPane tab
        Tab stackTab = new Tab("StackPane", createStackLayout());
        stackTab.setClosable(false);

        // Add tabs
        tabPane.getTabs().addAll(vboxTab, hboxTab, gridTab, stackTab);

        // Set up the scene and stage
        Scene scene = new Scene(tabPane, 400, 300);
        primaryStage.setTitle("JavaFX Layouts Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createVBoxLayout() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));

        Label label = new Label("Enter your name:");
        TextField textField = new TextField();
        Button submitBtn = new Button("Submit");

        submitBtn.setOnAction(e -> System.out.println("Name entered: " + textField.getText()));

        vbox.getChildren().addAll(label, textField, submitBtn);
        return vbox;
    }

    private HBox createHBoxLayout() {
        HBox hbox = new HBox(15);
        hbox.setPadding(new Insets(20));

        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");

        yesBtn.setOnAction(e -> System.out.println("You clicked Yes"));
        noBtn.setOnAction(e -> System.out.println("You clicked No"));

        hbox.getChildren().addAll(yesBtn, noBtn);
        return hbox;
    }

    private GridPane createGridLayout() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();
        Button submitBtn = new Button("Submit");

        submitBtn.setOnAction(e -> System.out.println(
                "Submitted Name: " + nameField.getText() + ", Email: " + emailField.getText()));

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(submitBtn, 1, 2);

        return grid;
    }

    private StackPane createStackLayout() {
        StackPane stackPane = new StackPane();

        Label label = new Label("Background Label");
        Button button = new Button("Click Me");

        button.setOnAction(e -> System.out.println("StackPane Button Clicked"));

        stackPane.getChildren().addAll(label, button);
        return stackPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
