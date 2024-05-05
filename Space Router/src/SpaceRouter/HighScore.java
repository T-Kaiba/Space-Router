package SpaceRouter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;

public class HighScore extends Application {

    private Stage stage2; //Necessary Variable for shifting scenes

    public HighScore() {
    }

    @Override
    public void start(Stage stage) throws Exception {

        //Reading the Highscore of each level from the file
        BufferedReader br = new BufferedReader(new FileReader("HighScores.txt"));
        String ln;
        String copy[] = new String[3];
        for( int i = 0; (ln = br.readLine()) != null; i++){
            copy[i] = ln;
        }
        br.close();

        //setting up the background
        FileInputStream input = new FileInputStream("src/css/spacedribbble.gif");
        Image image = new Image(input);

        // creating a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));

        // creating Background
        Background background = new Background(backgroundimage);

        //setting up the root for the scene
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(70);

        //setting up the Labels for the scene
        Label title = new Label("High Scores");
        title.setStyle("-fx-text-fill: #f83600; -fx-font-size: 72px");

        Label colon1 = new Label(":");
        Label colon2 = new Label(":");
        Label colon3 = new Label(":");

        HBox h1 = new HBox();
        h1.setAlignment(Pos.CENTER);
        h1.setSpacing(50);
        Label easy = new Label("    Easy    ");
        easy.setStyle("-fx-background-color: linear-gradient(to right, #fe8c00 0%, #f83600  51%, #fe8c00  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");

        Label easyScore = new Label(copy[0]);
        easyScore.setStyle("-fx-text-fill: #f83600");
        colon1.setStyle("-fx-text-fill: #f83600");
        h1.getChildren().addAll(easy, colon1,easyScore);

        HBox h2 = new HBox();
        h2.setAlignment(Pos.CENTER);
        h2.setSpacing(50);
        Label medium = new Label("  Medium  ");
        medium.setStyle("-fx-background-color: linear-gradient(to right, #fe8c00 0%, #f83600  51%, #fe8c00  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");

        Label mediumScore = new Label(copy[1]);
        mediumScore.setStyle("-fx-text-fill: #f83600");
        colon2.setStyle("-fx-text-fill: #f83600");
        h2.getChildren().addAll(medium, colon2,mediumScore);

        HBox h3 = new HBox();
        h3.setAlignment(Pos.CENTER);
        h3.setSpacing(50);
        Label hard = new Label("    Hard    ");
        hard.setStyle("-fx-background-color: linear-gradient(to right, #fe8c00 0%, #f83600  51%, #fe8c00  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");

        Label hardScore = new Label(copy[2]);
        hardScore.setStyle("-fx-text-fill: #f83600");
        colon3.setStyle("-fx-text-fill: #f83600");
        h3.getChildren().addAll(hard, colon3,hardScore);

        //Button to go back to the menu scene
        GameButtons close = new GameButtons("Close");
        close.setOnAction (actionEvent -> {
            stage2 = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                Menu m = new Menu();
                m.start(stage2);
                //Main.menuPage.start(stage2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        root.getChildren().addAll(title,h1,h2,h3,close);

        root.setBackground(background);

        //setting up the window properties
        stage.setTitle("Space Router");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Menu.class.getResource("/css/highscore.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
    }

}
