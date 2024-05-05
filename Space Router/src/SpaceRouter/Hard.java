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

public class Hard extends Gameplay{

    public Hard() throws IOException {
        setVnumber(14);
        setEnumber(23);
        setVertexRadius(15);
        setPositionX(280);
        setAdjacencyMatrix(new int[14][14]);
        setParent(new int[14]);
        setLevel(2);
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
            positionX += 60; //changing the abcissa of every vertex by 100 pixels
        }

        //Creating Objects of Edges class
        edge[0] = new Edges(vertex[0], vertex[1]);
        edge[1] = new Edges(vertex[0], vertex[2]);
        edge[2] = new Edges(vertex[1], vertex[2]);
        edge[3] = new Edges(vertex[1], vertex[3]);
        edge[4] = new Edges(vertex[2], vertex[3]);
        edge[5] = new Edges(vertex[2], vertex[4]);
        edge[6] = new Edges(vertex[3], vertex[5]);
        edge[7] = new Edges(vertex[4], vertex[5]);
        edge[8] = new Edges(vertex[4], vertex[6]);
        edge[9] = new Edges(vertex[5], vertex[6]);
        edge[10] = new Edges(vertex[4], vertex[7]);
        edge[11] = new Edges(vertex[6], vertex[7]);
        edge[12] = new Edges(vertex[7], vertex[8]);
        edge[13] = new Edges(vertex[7], vertex[9]);
        edge[14] = new Edges(vertex[7], vertex[11]);
        edge[15] = new Edges(vertex[9], vertex[10]);
        edge[16] = new Edges(vertex[10], vertex[11]);
        edge[17] = new Edges(vertex[10], vertex[13]);
        edge[18] = new Edges(vertex[11], vertex[13]);
        edge[19] = new Edges(vertex[12], vertex[13]);
        edge[20] = new Edges(vertex[11], vertex[12]);
        edge[21] = new Edges(vertex[8], vertex[9]);
        edge[22] = new Edges(vertex[9], vertex[11]);

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
        pane.getChildren().addAll(vertex[0], vertex[1], vertex[2], vertex[3], vertex[4], vertex[5], vertex[6], vertex[7], vertex[8], vertex[9], vertex[10], vertex[11], vertex[12], vertex[13],
                edge[0], edge[1], edge[2], edge[3], edge[4], edge[5], edge[6], edge[7], edge[8], edge[9], edge[10], edge[11], edge[12], edge[13], edge[14], edge[15], edge[16], edge[17], edge[18], edge[19], edge[20], edge[21], edge[22],
                weight[0], weight[1], weight[2], weight[3], weight[4], weight[5], weight[6], weight[7], weight[8], weight[9], weight[10], weight[11], weight[12], weight[13], weight[14], weight[15], weight[16], weight[17], weight[18], weight[19], weight[20], weight[21], weight[22]);

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
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                adjacencyMatrix[i][j] = 999;
            }
        }
        adjacencyMatrix[0][1] = adjacencyMatrix[1][0] = edges[0].getEdgeWeight();
        adjacencyMatrix[0][2] = adjacencyMatrix[2][0] = edges[1].getEdgeWeight();
        adjacencyMatrix[1][2] = adjacencyMatrix[2][1] = edges[2].getEdgeWeight();
        adjacencyMatrix[1][3] = adjacencyMatrix[3][1] = edges[3].getEdgeWeight();
        adjacencyMatrix[2][3] = adjacencyMatrix[3][2] = edges[4].getEdgeWeight();
        adjacencyMatrix[2][4] = adjacencyMatrix[4][2] = edges[5].getEdgeWeight();
        adjacencyMatrix[3][5] = adjacencyMatrix[5][3] = edges[6].getEdgeWeight();
        adjacencyMatrix[4][5] = adjacencyMatrix[5][4] = edges[7].getEdgeWeight();
        adjacencyMatrix[4][6] = adjacencyMatrix[6][4] = edges[8].getEdgeWeight();
        adjacencyMatrix[5][6] = adjacencyMatrix[6][5] = edges[9].getEdgeWeight();
        adjacencyMatrix[4][7] = adjacencyMatrix[7][4] = edges[10].getEdgeWeight();
        adjacencyMatrix[6][7] = adjacencyMatrix[7][6] = edges[11].getEdgeWeight();
        adjacencyMatrix[7][8] = adjacencyMatrix[8][7] = edges[12].getEdgeWeight();
        adjacencyMatrix[7][9] = adjacencyMatrix[9][7] = edges[13].getEdgeWeight();
        adjacencyMatrix[7][11] = adjacencyMatrix[11][7] = edges[14].getEdgeWeight();
        adjacencyMatrix[9][10] = adjacencyMatrix[10][9] = edges[15].getEdgeWeight();
        adjacencyMatrix[10][11] = adjacencyMatrix[11][10] = edges[16].getEdgeWeight();
        adjacencyMatrix[10][13] = adjacencyMatrix[13][10] = edges[17].getEdgeWeight();
        adjacencyMatrix[11][13] = adjacencyMatrix[13][11] = edges[18].getEdgeWeight();
        adjacencyMatrix[12][13] = adjacencyMatrix[13][12] = edges[19].getEdgeWeight();
        adjacencyMatrix[11][12] = adjacencyMatrix[12][11] = edges[20].getEdgeWeight();
        adjacencyMatrix[8][9] = adjacencyMatrix[9][8] = edges[21].getEdgeWeight();
        adjacencyMatrix[9][11] = adjacencyMatrix[11][9] = edges[22].getEdgeWeight();
    }
}
