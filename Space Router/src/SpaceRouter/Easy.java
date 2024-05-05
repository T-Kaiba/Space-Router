package SpaceRouter;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.IOException;

public class Easy extends Gameplay{

    public Easy() throws IOException {
       setVnumber(5);
       setEnumber(7);
       setVertexRadius(30);
       setPositionX(460);
       setAdjacencyMatrix(new int[5][5]);
       setParent(new int[5]);
       setLevel(0);
    }

    //start method overriding main method
    public void start(Stage stage) throws Exception {

        backgroundMusic.setOnEndOfMedia(new Runnable()
        {
            public void run() {
                backgroundMusic.seek(Duration.ZERO);
            }
        });

        Menu.stopIntro();
        backgroundMusic.setVolume(0.5);
        backgroundMusic.play();

        FileInputStream input = new FileInputStream("src/css/space-1.gif");

        Image image = new Image(input);

        // create a background image
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false));

        // create Background
        Background background = new Background(backgroundimage);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);

        Label title = new Label("Start!");
        Label line = new Label("Find   the   Shortest   Route   as   Fast   as   Possible!");

        title.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 72px");
        line.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 30px");

        Pane pane = new Pane();

        //array of vertices
        Vertices[] vertex = new Vertices[Vnumber];

        //array of edges
        Edges[] edge = new Edges[Enumber];

        //array of edge weights
        weight = new Text[Enumber];

        //Creating Objects of Vertices class
        for (int i = 0; i < Vnumber; i++) {
            vertex[i] = new Vertices(positionX, vertexRadius, i); //calling the constructor
            positionX += 100; //changing the abcissa of every vertex by 100 pixels
        }

        //Creating Objects of Edges class
        edge[0] = new Edges(vertex[0], vertex[1]);
        edge[1] = new Edges(vertex[0], vertex[2]);
        edge[2] = new Edges(vertex[1], vertex[3]);
        edge[3] = new Edges(vertex[1], vertex[2]);
        edge[4] = new Edges(vertex[2], vertex[3]);
        edge[5] = new Edges(vertex[2], vertex[4]);
        edge[6] = new Edges(vertex[3], vertex[4]);

        //Creating Objects of Text class
        for (int i = 0; i < Enumber; i++) {
            //displaying the weight of each edge at the mid point of that edge displaced 5 pixels vertically and horizontally
            weight[i] = new Text(((edge[i].getStartX() + edge[i].getEndX()) / 2) + 5, ((edge[i].getStartY() + edge[i].getEndY()) / 2) - 5, Integer.toString(edge[i].getEdgeWeight()));
            weight[i].setStyle("-fx-font-family: ArcadeClassic; -fx-font-size: 20px");
            weight[i].setFill(Color.GOLD);
        }

        for (int i = 0; i < Enumber; i++) {

            int finalI = i;
            edge[finalI].setOnMouseEntered(mouseEvent -> {
                if(edge[finalI].isDisable()!=true) {
                    edge[finalI].setStroke(Color.GOLD);
                    weight[finalI].setFill(Color.AQUAMARINE);
                }
            });
            edge[finalI].setOnMouseExited(mouseEvent -> {
                if(edge[finalI].isDisable()!=true) {
                    edge[finalI].setStroke(Color.WHITE);
                    weight[finalI].setFill(Color.GOLD);
                }
            });

        }

        //creating array for user selectes edges
        int[] userSelectedMSTWeigth = new int[Vnumber-1];

        //generating MST at backend
        createMST(adjacencyMatrix,edge);

        //adding all nodes in the pane
        pane.getChildren().addAll(vertex[0], vertex[1], vertex[2], vertex[3], vertex[4],
                edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], edge[6],
                weight[0], weight[1], weight[2], weight[3], weight[4], weight[5], weight[6]);

        root.setBackground(background);

        root.getChildren().addAll(title,line,pane,foundIt);

        //initiating gameplay
        extracted(edge, userSelectedMSTWeigth);

        //getting the stage for GUI
        GameScene = new Scene(root);
        GameScene.getStylesheets().add(Menu.class.getResource("/css/gameplay.css").toExternalForm());
        stage.setScene(GameScene);
        stage.show();
        stage.setFullScreenExitHint("");
        stage.setFullScreen(true);

    }

    //method to generate MST
    public void createMST(int[][] adjacencyMatrix, Edges[] edges) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                adjacencyMatrix[i][j] = 999;
            }
        }
        adjacencyMatrix[0][1] = adjacencyMatrix[1][0] = edges[0].getEdgeWeight();
        adjacencyMatrix[0][2] = adjacencyMatrix[2][0] = edges[1].getEdgeWeight();
        adjacencyMatrix[1][2] = adjacencyMatrix[2][1] = edges[3].getEdgeWeight();
        adjacencyMatrix[1][3] = adjacencyMatrix[3][1] = edges[2].getEdgeWeight();
        adjacencyMatrix[2][3] = adjacencyMatrix[3][2] = edges[4].getEdgeWeight();
        adjacencyMatrix[2][4] = adjacencyMatrix[4][2] = edges[5].getEdgeWeight();
        adjacencyMatrix[3][4] = adjacencyMatrix[4][3] = edges[6].getEdgeWeight();
    }

}
