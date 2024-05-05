package SpaceRouter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;

public class GameEnds extends Application {

    private boolean result; //variable to control the scene shift
    private int score; //Score of the user
    private Stage stageP; //Necessary Variable for shifting scenes

    public GameEnds(boolean result, int score) {
        this.result = result;
        this.score = score;
    }

    public boolean isResult() {
        return result;
    }

    @Override
    public void start(Stage stage) throws Exception {

        //local variables fot root and scene
        VBox root;
        Scene scene;

        //buttons in the last scene
        GameButtons playAgain = new GameButtons("Play Again");
        playAgain.setOnAction (actionEvent -> {
            stageP = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            Menu.startIntro();
            try {
                Level l = new Level();
                l.start(stageP);
                // Main.levelPage.start(stageP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        GameButtons menu = new GameButtons("Menu");
        menu.setOnAction (actionEvent -> {
            stageP = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                Menu m = new Menu();
                m.start(stageP);
                //Main.menuPage.start(stageP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        GameButtons viewHighScores = new GameButtons("View High Scores");
        viewHighScores.setOnAction (actionEvent -> {
            stageP = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            Menu.startIntro();
            try {
                HighScore hs = new HighScore();
                hs.start(stageP);
                //Main.highScorePage.start(stageP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        if(isResult()==true && score>0){

            root = caseWin(playAgain,menu);

            AudioClip win = new AudioClip(new File("src/css/mixkit-game-level-completed-2059.wav").toURI().toString());

            //setting up the background
            FileInputStream input1 = new FileInputStream("src/css/debut-space-dribbble.png");
            Image image1 = new Image(input1);

            // create a background image
            BackgroundImage backgroundimage1 = new BackgroundImage(image1,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));

            // create Background
            Background background1 = new Background(backgroundimage1);

            root.setBackground(background1);
            scene = new Scene(root);
            scene.getStylesheets().add(Menu.class.getResource("/css/win.css").toExternalForm());
            win.setVolume(0.5);
            win.play();

        }//executes when the user wins the game

        else if (isResult()==true && score==0){

            root = caseLoss(playAgain,menu);

            AudioClip lose = new AudioClip(new File("src/css/mixkit-game-over-trombone-1940.wav").toURI().toString());

            //setting up the background
            FileInputStream input2 = new FileInputStream("src/css/debut-space-dribbble.png");
            Image image2 = new Image(input2);

            // creating a background image
            BackgroundImage backgroundimage2 = new BackgroundImage(image2,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));

            // creating Background
            Background background2 = new Background(backgroundimage2);

            root.setBackground(background2);
            scene = new Scene(root);
            scene.getStylesheets().add(Menu.class.getResource("/css/lose.css").toExternalForm());
            lose.setVolume(0.5);
            lose.play();

        }//executes when the user loses the game

        else{

            root = caseHighScore(playAgain,menu,viewHighScores);

            AudioClip newRecord = new AudioClip(new File("src/css/mixkit-game-bonus-reached-2065.wav").toURI().toString());

            //setting up the background
            FileInputStream input3 = new FileInputStream("src/css/debut-space-dribbble.png");
            Image image3 = new Image(input3);

            // creating a background image
            BackgroundImage backgroundimage3 = new BackgroundImage(image3,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(1.0, 1.0, true, true, false, false));

            // creating Background
            Background background3 = new Background(backgroundimage3);

            root.setBackground(background3);
            scene = new Scene(root);
            scene.getStylesheets().add(Menu.class.getResource("/css/newRecord.css").toExternalForm());
            newRecord.setVolume(0.5);
            newRecord.play();

        }//executes when the user wins the game and breaks the highscore

        //setting the window properties
        stage.setTitle("Space Router");
        stage.setScene(scene);
        stage.show();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
    }

    public VBox caseWin(GameButtons playAgain, GameButtons menu){

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(100);

        //labels for the wins scene
        Label line1 = new Label("CONGRATS!!!");
        Label line2 = new Label("You   Found   The   MST   Route!");
        line1.setStyle("-fx-text-fill: #9bc5c3; -fx-font-size: 72px");
        line2.setStyle("-fx-text-fill: #9bc5c3; -fx-font-size: 30px");

        HBox scored = new HBox();
        scored.setAlignment(Pos.CENTER);
        scored.setSpacing(50);

        Label yourScore = new Label("Your   Score");
        Label intScore = new Label(Integer.toString(score));
        Label colon = new Label(":");
        yourScore.setStyle("-fx-background-color: linear-gradient(to right, #616161 0%, #9bc5c3  51%, #616161  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");
        intScore.setStyle("-fx-text-fill: #9bc5c3");
        colon.setStyle("-fx-text-fill: #9bc5c3");

        scored.getChildren().addAll(yourScore,colon,intScore);

        HBox GameButtonss = new HBox();
        GameButtonss.setAlignment(Pos.CENTER);
        GameButtonss.setSpacing(50);
        GameButtonss.getChildren().addAll(playAgain,menu);

        root.getChildren().addAll(line1,line2,scored,GameButtonss);

        return root;
    }

    public VBox caseLoss(GameButtons playAgain, GameButtons menu){

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(100);

        //labels for the lose scene
        Label line1 = new Label("Game   Over!!!");
        Label line2 = new Label("NOT   The   RIGHT   ROUTE!");
        line1.setStyle("-fx-text-fill: #780206; -fx-font-size: 72px");
        line2.setStyle("-fx-text-fill: #780206; -fx-font-size: 30px");

        HBox scored = new HBox();
        scored.setAlignment(Pos.CENTER);
        scored.setSpacing(50);

        Label yourScore = new Label("Your   Score");
        Label intScore = new Label(Integer.toString(score));
        Label colon = new Label(":");
        yourScore.setStyle("-fx-background-color: linear-gradient(to right, #780206 0%, #061161  51%, #780206  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");
        intScore.setStyle("-fx-text-fill: #780206");
        colon.setStyle("-fx-text-fill: #780206");

        scored.getChildren().addAll(yourScore,colon,intScore);

        HBox GameButtonss = new HBox();
        GameButtonss.setAlignment(Pos.CENTER);
        GameButtonss.setSpacing(50);
        GameButtonss.getChildren().addAll(playAgain,menu);

        root.getChildren().addAll(line1,line2,scored,GameButtonss);

        return root;
    }

    public VBox caseHighScore(GameButtons playAgain, GameButtons menu, GameButtons viewHighScores){

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(100);

        //Labels for the highscore scene
        Label line1 = new Label("NEW RECORD!");
        Label line2 = new Label("You Beat The HighScore!");
        line1.setStyle("-fx-text-fill: #6441A5; -fx-font-size: 72px");
        line2.setStyle("-fx-text-fill: #6441A5; -fx-font-size: 30px");

        HBox scored = new HBox();
        scored.setAlignment(Pos.CENTER);
        scored.setSpacing(50);

        Label yourScore = new Label("Your   Score");
        Label intScore = new Label(Integer.toString(score));
        Label colon = new Label(":");
        yourScore.setStyle("-fx-background-color: linear-gradient(to right, #6441A5 0%, #2a0845  51%, #6441A5  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");
        intScore.setStyle("-fx-text-fill: #6441A5");
        colon.setStyle("-fx-text-fill: #6441A5");

        scored.getChildren().addAll(yourScore,colon,intScore);

        HBox GameButtonss = new HBox();
        GameButtonss.setAlignment(Pos.CENTER);
        GameButtonss.setSpacing(50);
        GameButtonss.getChildren().addAll(playAgain,menu);

        root.getChildren().addAll(line1,line2,scored,viewHighScores,GameButtonss);

        return root;
    }
}
