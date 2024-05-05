package SpaceRouter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileInputStream;

public class HowToPlay extends Application {

    private Stage pStage; //Necessary Variable for shifting scenes

    public HowToPlay() {
    }

    @Override
    public void start(Stage stage) throws Exception {

        //setting up background
        FileInputStream input = new FileInputStream("src/css/space-1.gif");
        Image image = new Image(input);

        // creating a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));

        // creating Background
        Background background = new Background(backgroundimage);

        //loading example image
        FileInputStream input1 = new FileInputStream("src/css/Minimum_spanning_tree.png");
        Image mst = new Image(input1);
        ImageView showMST = new ImageView(mst);
        showMST.setFitHeight(200);
        showMST.setFitWidth(200);

        //setting up roots for the scene
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(5);

        VBox root1 = new VBox();
        root1.setAlignment(Pos.CENTER);
        root1.setSpacing(20);

        VBox root2 = new VBox();
        root2.setAlignment(Pos.CENTER);
        root2.setSpacing(20);

        //applying background to the roots
        root.setBackground(background);
        root1.setBackground(background);
        root2.setBackground(background);

        //titles for all scenes
        Label title = new Label("How To Play!");
        title.setStyle("-fx-font-size: 72px");
        Label title1 = new Label("How   To   Play!");
        title1.setStyle("-fx-font-size: 72px");
        Label title2 = new Label("How   To   Play!");
        title2.setStyle("-fx-font-size: 72px");

        //Labels for 1st scene
        Label sTitle1 = new Label("What   is   an   MST!");
        sTitle1.setStyle("-fx-background-color: linear-gradient(to right, #2b5876 0%, #4e4376  51%, #2b5876  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");
        Label sLine1 = new Label("An   MST   is   Short   for   'minimum   spanning   tree'   or   'minimum-weight   spanning   tree' ");
        Label sLine2 = new Label("is   a   subset   of   the   edges   of   a   connected,   edge-weighted   undirected   graph");
        Label sLine3 = new Label("connects   all   the   vertices   together");
        Label sLine4 = new Label("Does   Not   Include   Any   Cycles   or   Closed   Paths");
        Label sLine5 = new Label("Has   minimum   possible   total   edge   weight");
        Label sLine6 = new Label("The   Number   of   edges   is   always   1   less   than   the   number   of   Vertices");
        Label sLine7 = new Label("Example   of   an   MST");

        GameButtons next = new GameButtons("NEXT");

        root.getChildren().addAll(title1,sTitle1,sLine1,sLine2,sLine3,sLine4,sLine5,sLine6,showMST,sLine7,next);

        Scene mstIntro = new Scene(root);

        //Labels for 2nd scene
        Label sTitle3 = new Label("Scenario");
        sTitle3.setStyle("-fx-background-color: linear-gradient(to right, #2b5876 0%, #4e4376  51%, #2b5876  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");
        Label nLine1 = new Label("You   are   on   a   Spaceship");
        Label nLine2 = new Label("You   are   to   explore   a   series   of    planets   one   by   one");
        Label nLine3 = new Label("Find   the    way   to   explore   all   the   planets");
        Label nLine4 = new Label("You   cannot   Explore   a   planet   twice");
        Label nLine5 = new Label("You   are    low   on   supplies / resources");
        Label nLine6 = new Label("So   Make   the   path   as   short   as   possible");

        GameButtons next1 = new GameButtons("NEXT");

        root2.getChildren().addAll(title2,sTitle3,nLine1,nLine2,nLine3,nLine4,nLine5,nLine6,next1);

        Scene scenario = new Scene(root2);

        //Labels for 3rd scene
        Label sTitle2 = new Label("To   Clear   The   Game");
        sTitle2.setStyle("-fx-background-color: linear-gradient(to right, #2b5876 0%, #4e4376  51%, #2b5876  100%);-fx-margin: 10px;\n" +
                "            -fx-padding: 15px 45px;\n" +
                "            -fx-text-align: center;\n" +
                "            -fx-text-transform: uppercase;\n" +
                "            -fx-transition: 0.5s;\n" +
                "            -fx-background-size: 200% auto;\n" +
                "            -fx-color: white;\n" +
                "            -fx-border-radius: 10px;\n" +
                "            -fx-display: block;\n" +
                "-fx-background-radius: 60; -fx-background-insets: 0,1,2,3,0;");
        Label line1 = new Label("First,   Count   the   total   Number   of   vertices   in   the   Graph");
        Label line2 = new Label("Start   with   the   lowest   weighted   edge   and   proceed   in   ascending   order   of   weights");
        Label line3 = new Label("Click   on   an   edge   to   select   it");
        Label line4 = new Label("Skip   the   Edges   that   make   a   Closed   path   with   the   prior   edges   if   selected");
        Label line5 = new Label("Do   this   until   Required   number   of   edges   are   selected");
        Label line6 = new Label("Click   the   'Found   It'   button   when   you   have   found   the   MST!");
        Label line7 = new Label("Good   Luck !!!");

        //Button to switch to Menu page
        GameButtons close = new GameButtons("Close");
        close.setOnAction (actionEvent -> {
            pStage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
            try {
               Menu m = new Menu();
               m.start(pStage);
                //Main.menuPage.start(pStage);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        root1.getChildren().addAll(title,sTitle2,line1,line2,line3,line4,line5,line6,line7,close);

        Scene gameIntro = new Scene(root1);

        //setting button properties to switch scenes
        next1.setOnAction(actionEvent -> {
            stage.setScene(gameIntro);
            stage.setFullScreenExitHint("");
            stage.setFullScreen(true);
        });

        next.setOnAction(actionEvent -> {
            stage.setScene(scenario);
            stage.setFullScreenExitHint("");
            stage.setFullScreen(true);
        });

        //setting window properties
        stage.setTitle("Space Router");
        mstIntro.getStylesheets().add(Menu.class.getResource("/css/howTo.css").toExternalForm());
        gameIntro.getStylesheets().add(Menu.class.getResource("/css/howTo.css").toExternalForm());
        scenario.getStylesheets().add(Menu.class.getResource("/css/howTo.css").toExternalForm());
        stage.setScene(mstIntro);
        stage.show();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);
    }
}
