package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Button button1 = new Button("Drink me");
			button1.setOnAction(e -> button1.setText("Silly Alice"));
			root.setTop(button1);
			
			HBox hBox = new HBox();
			Label nameLabel = new Label("Enter your name");
			TextField nameField = new TextField();
			nameField.setPromptText("Enter your name here");
			hBox.getChildren().addAll(nameLabel, nameField);
			root.setCenter(hBox);
			
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
