package SpaceRouter;
 /**
 * >>>Main Class<< *
 **/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;

public class Menu extends Application {

    private Stage stage; //Necessary Variable for shifting scenes
    //Main game music theme
    static Media intro_source = new Media(new File("src/css/centys-week-4749.mp3").toURI().toString());
    static MediaPlayer intro = new MediaPlayer(intro_source);

    public Menu() throws FileNotFoundException {

        //Font for all text in the game
        Font myfont = Font.loadFont(new FileInputStream(new File("src/css/ARCADECLASSIC.TTF")), 60);

        //Creating highscores file if not present
        File f = new File("HighScores.txt");

        //make file if it does not exist
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(f));
            if (br.readLine() == null && f.length() == 0)
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write("0\n");
                bw.write("0\n");
                bw.write("0\n");
                bw.close();
            }

        }
        catch (FileNotFoundException ex)
        {
            try
            {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write("0\n");
                bw.write("0\n");
                bw.write("0\n");
                bw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start(Stage window) throws Exception{

        //setting the mediaplayer to loop
        intro.setOnEndOfMedia(new Runnable() {
            public void run() {
                intro.seek(Duration.ZERO);
            }
        });

        //setting window properties
        window.setMinWidth((Screen.getPrimary().getVisualBounds().getWidth()));
        window.setMinHeight((Screen.getPrimary().getVisualBounds().getHeight()));

        //declaring roots for the menu scene
        BorderPane root = new BorderPane();

        VBox root1 = new VBox();
        root1.setAlignment(Pos.CENTER);

        root.setTop(root1);

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

        //setting up Game Title
        FileInputStream input1 = new FileInputStream("src/css/SPACE-ROUTER.png");
        Image name = new Image(input1);
        ImageView gameName = new ImageView(name);
        //setting up title size properties
        gameName.setFitWidth((Screen.getPrimary().getVisualBounds().getWidth())/2 );
        gameName.setFitHeight((Screen.getPrimary().getVisualBounds().getHeight())/2 );
        gameName.setPreserveRatio(true);

        root1.getChildren().addAll(gameName);
        root1.setPadding(new Insets(100));

        VBox root2 = new VBox();
        root2.setAlignment(Pos.CENTER);

        root.setCenter(root2);

        //Declaring Scene Buttons
        GameButtons play = new GameButtons("Start Game");
        GameButtons howTo = new GameButtons("How to Play");
        GameButtons highScores = new GameButtons("High Scores");
        GameButtons exit = new GameButtons("Exit Game");

        //setting button properties
        play.setOnAction (actionEvent -> {
            stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                Level l = new Level();
                l.start(stage);
                //Main.levelPage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        howTo.setOnAction (actionEvent -> {
            stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                HowToPlay htp = new HowToPlay();
                htp.start(stage);
                //Main.howToPlayPage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        highScores.setOnAction (actionEvent -> {

            stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
                HighScore hs = new HighScore();
                hs.start(stage);
                // Main.highScorePage.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        exit.setOnAction(actionEvent -> {
            stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            stage.close();
        });

        root2.getChildren().addAll( play, howTo, highScores, exit);
        root2.setPadding(new Insets(10));
        root2.setSpacing(30);

        root.setBackground(background);

        //setting up window properties
        window.setTitle("Space Router");
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Menu.class.getResource("/css/menu.css").toExternalForm());
        window.setScene(scene);
        window.show();
        intro.play();
        window.setFullScreenExitHint("");
        window.setFullScreen(true);
    }

    //Background Music on/off methods
    public static void stopIntro(){intro.stop();}
    public static void startIntro(){intro.play();}

    //Main Method
    public static void main(String[] args) { launch(args); }
}
