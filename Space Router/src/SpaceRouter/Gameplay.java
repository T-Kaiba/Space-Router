package SpaceRouter;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;

/**
 * This class contains the whole code for the gameplay.
 **/

public abstract class Gameplay extends Application {

    protected GameButtons foundIt = new GameButtons("Found It!");

    static Media background_source = new Media(new File("src/css/trippin-1547.mp3").toURI().toString());
    MediaPlayer backgroundMusic =new MediaPlayer(background_source);

    Scene GameScene;
    Stage stage;
    protected int Vnumber; //Number of vertices in the level
    protected int Enumber; //Number of edges in the level
    protected double vertexRadius; //Predefined radius of vertices in the level
    protected double positionX; //Starting abcissa of vertices
    protected int[][] adjacencyMatrix; //adjacency matrix of the graph
    protected Text weight[];
    protected int level;
    private int[] parent;

    protected Gameplay() {
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setParent(int[] parent) {
        this.parent = parent;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) { this.adjacencyMatrix = adjacencyMatrix; }

    public void setVnumber(int vnumber) {
        Vnumber = vnumber;
    }

    public void setEnumber(int enumber) {
        Enumber = enumber;
    }

    public void setVertexRadius(double vertexRadius) {
        this.vertexRadius = vertexRadius;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    @Override
    //start method overriding main method
    public abstract void start(Stage stage) throws Exception;

    //method to generate MST
    public abstract void createMST(int[][] adjacencyMatrix, Edges[] edges);


    //the following method contains code for real time gameplay
    public void extracted(Edges[] edge, int[] a) {

        long startTime = System.nanoTime();
      //  System.out.println(startTime);

        var lambdaContext = new Object() {
            int count = 0;
            int SumOfUserMST = 0; //number of user selected edges(updated in real time)
        };
        for (int i = 0; i < edge.length; i++) {

            int finalI = i;
            edge[i].setOnMouseClicked(mouseEvent -> {

                //when an edge is selected
                int counter = lambdaContext.count;
                //edge color is changed
                edge[finalI].setDisable(true);
                edge[finalI].setStroke(Color.DARKCYAN);
                edge[finalI].setOpacity(1);
                weight[finalI].setFill(Color.RED);

                //store the user selected edgeWeight
                try {
                    a[counter] = edge[finalI].getEdgeWeight();
                }catch (ArrayIndexOutOfBoundsException e){}

                //to the next edge
                counter++;
                lambdaContext.count = counter;

                //the following block executes only when the game ends

                foundIt.setOnAction(actionEvent -> {

                    long endTime = System.nanoTime();
//                    System.out.println(endTime);

                    int totalTime = (int) ((endTime - startTime)/1000000000);
                   // System.out.println(totalTime);

                    int score = 10000/totalTime;
                    //System.out.println(score);

                    lambdaContext.SumOfUserMST = User(a);

                    //If the user wins, the following block executes
                    if (Check(kruskalMST(adjacencyMatrix), lambdaContext.SumOfUserMST)) {

                        String ln;
                        boolean bool = true;
                        int highScore;
                        try {
                            BufferedReader br = new BufferedReader(new FileReader("HighScores.txt"));
                            String copy[] = new String[3];
                            for( int j = 0; (ln = br.readLine()) != null; j++){
                                copy[j] = ln;
                            }
                            br.close();
                            highScore = Integer.parseInt(copy[level]);
                            if(score > highScore){
                                bool = false;
                                copy[level] = Integer.toString(score);
                                BufferedWriter bw = new BufferedWriter(new FileWriter("HighScores.txt"));
                                for (int j = 0; j < copy.length; j++){
                                    bw.write(copy[j] + "\n");
                                }
                                bw.close();
                            }

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        stage = (Stage) ((Node) mouseEvent.getTarget()).getScene().getWindow();
                        try {
                            GameEnds gEnds = new GameEnds(bool,score);
                            gEnds.start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    //If the user loses, the following block executes
                    else {
                        score = 0;
                        boolean bool = true;
                        stage = (Stage) ((Node) mouseEvent.getTarget()).getScene().getWindow();
                        try {
                            GameEnds gEnds = new GameEnds(bool,score);
                            gEnds.start(stage);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    backgroundMusic.stop();

                    //disabling the selected edges
                    for (int j = 0; j < edge.length; j++) {
                        edge[j].setDisable(true);
                    }

                });

            });
        }
    }


    //the following method finds the sum of the user selected edges
    public int User(int[] a) {
        int sumOfUserSelecetedMST = 0;
        for (int i = 0; i < Vnumber-1; i++) {
            sumOfUserSelecetedMST = sumOfUserSelecetedMST + a[i];
        }
        return sumOfUserSelecetedMST;
    }

    //the following method is used to compare the sum of the user selected edges and the computer generated MST edges
    public boolean Check(int a, int b) {
        if (a == b) {
            return true;
        }
        return false;
    }

    public int find(int i)
    {
        while (parent[i] != i)
            i = parent[i];
        return i;
    }

    public void union1(int i, int j)
    {
        int a = find(i);
        int b = find(j);
        parent[a] = b;
    }
    public int kruskalMST(int cost[][])
    {
        int INF = Integer.MAX_VALUE;
        int mincost = 0;

        for (int i = 0; i < Vnumber; i++)
            parent[i] = i;

        int edge_count = 0;
        while (edge_count < Vnumber - 1)
        {
            int min = INF, a = -1, b = -1;
            for (int i = 0; i < Vnumber; i++)
            {
                for (int j = 0; j < Vnumber; j++)
                {
                    if (find(i) != find(j) && cost[i][j] < min)
                    {
                        min = cost[i][j];
                        a = i;
                        b = j;
                    }
                }
            }

            union1(a, b);
            edge_count++;
            mincost += min;
        }
        return mincost;
    }
}
