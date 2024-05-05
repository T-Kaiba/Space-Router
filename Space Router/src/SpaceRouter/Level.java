package SpaceRouter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;

public class Level extends Application{

    private Stage stage; //Necessary Variable for shifting scenes

    public Level() {
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //setting up background
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

        //declaring root for the scene
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(30);

        //creating scene labels
        Label title = new Label("Levels");
        title.setStyle("-fx-font-size: 72px");
        Label mode = new Label("Select Difficulty:");
        mode.setStyle("-fx-font-size: 30px");

        //Declaring Level buttons and setting up their action properties
        GameButtons easyL = new GameButtons("Easy");
        easyL.setOnAction(actionEvent -> {
            stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                Easy easy = new Easy();
                easy.start(stage);
               //Main.easyPage.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        GameButtons mediumL = new GameButtons("Medium");
        mediumL.setOnAction(actionEvent -> {
            stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                Medium medium = new Medium();
                medium.start(stage);
                //Main.mediumPage.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        GameButtons hardL = new GameButtons("Hard");
        hardL.setOnAction(actionEvent -> {
            stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                Hard hard = new Hard();
                hard.start(stage);
                //Main.hardPage.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //setting up the main root for the scene
        root.getChildren().addAll(title, mode, easyL,mediumL, hardL);
        root.setBackground(background);

        //setting up window properties
        primaryStage.setTitle("Space Router");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Menu.class.getResource("/css/level.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
    }

}
