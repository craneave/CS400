import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {

	private int score = 0;

	@Override
	public void start(final Stage stage) {
		// Step 3 & 4
		BorderPane borderPane = new BorderPane();
		Scene scene = new Scene(borderPane, 640, 480);
		stage.setTitle("Dessert in the Desert JavaFX Game");

		// Step 5
		Label scoreLabel = new Label("Score: " + score);
		borderPane.setTop(scoreLabel);
		BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

		Button exitButton = new Button("Exit");
		exitButton.requestFocus();
		exitButton.setOnAction(event -> {
			Platform.exit();
		});
		borderPane.setBottom(exitButton);
		BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

		// Step 6
		Pane pane = new Pane();
		borderPane.setCenter(pane);
		BorderPane.setAlignment(pane, Pos.CENTER);

		// TODO: Step 7-10
		Button b1 = new Button("Dessert");
		Button b2 = new Button("Desert");
		Button b3 = new Button("Desert");
		Button b4 = new Button("Desert");
		Button b5 = new Button("Desert");
		Button b6 = new Button("Desert");
		Button b7 = new Button("Desert");
		Button b8 = new Button("Desert");
		Random r = new Random();
		Button[] b = { b1, b2, b3, b4, b5, b6, b7, b7, b8 };
		randomizeButtonPositions(r, b);
		pane.getChildren().add(b1);
		pane.getChildren().add(b2);
		pane.getChildren().add(b3);
		pane.getChildren().add(b4);
		pane.getChildren().add(b5);
		pane.getChildren().add(b6);
		pane.getChildren().add(b7);
		pane.getChildren().add(b8);
		b1.setOnAction(event -> {
			increase(scoreLabel, r, b, exitButton);
		});
		for (int x = 1; x < b.length; x++) {
			b[x].setOnAction(event -> {
				decrease(scoreLabel, r, b, exitButton);
			});
		}
		stage.setScene(scene);
		stage.show();

	}

	private void decrease(Label scoreLabel, Random r, Button[] b, Button exitButton) {
		score--;
		scoreLabel.setText("Score: " + score);
		exitButton.requestFocus();
		randomizeButtonPositions(r, b);
	}

	private void increase(Label scoreLabel, Random r, Button[] b, Button exitButton) {
		score++;
		scoreLabel.setText("Score: " + score);
		exitButton.requestFocus();
		randomizeButtonPositions(r, b);
	}

	private void randomizeButtonPositions(Random r, Button[] b) {

		for (int x = 0; x < b.length; x++) {
			b[x].setLayoutX(r.nextInt(600));
			b[x].setLayoutY(r.nextInt(400));
		}
	}

	public static void main(String[] args) {
		Application.launch();
	}
}