package SpaceRouter;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * This class basically focuses on the Concept of Edges in an MST.
 * Edges are lines or paths which connect exactly 2 Vertices together.
 * The following class Vertices extends from javafx.scene.shape.Line and has all properties related to that class.
 * */

public class Edges extends Line {

    private int edgeWeight; // Length/weight of an edge
    private Vertices initialVertex; // Starting point of an edge
    private Vertices terminalVertex; // Ending point of an edge

    //Constructor for class Edges.
    //Constructs an object edge with predefined arguments to make an object with unique properties
    public Edges(Vertices v1, Vertices v2) {

        //Calls the constructor of the superclass Line to make a line object that is to be modified to make an edge
        //takes the coordinates centers of the associated vertices as arguments to join the 2 points
        super(v1.getCenterX(), v1.getCenterY(), v2.getCenterX(), v2.getCenterY());

        //Incrementing the hasEdges property of the associated vertices
        v1.incrementEdges();
        v2.incrementEdges();

        //setting the remaining properties of the Edges Object
        this.setStroke(Color.WHITE);
        this.initialVertex= v1;
        this.terminalVertex=v2;
        this.edgeWeight = WeightedEdge(v1, v2);
        this.setStrokeWidth(5);
        this.setOpacity(1);

    }

    //method to calculate the weight of the edge
    public int WeightedEdge(Vertices v1, Vertices v2) {

        //the following algo is basically formula used to find the distance between 2 points in Analytical Geometry
        double differenceX = v2.getCenterX() - v1.getCenterX();
        double differenceY = v2.getCenterY() - v1.getCenterY();
        double squareX = Math.pow(differenceX, 2);
        double squareY = Math.pow(differenceY, 2);
        double sumXY = squareX + squareY;
        int Weight = (int) Math.sqrt(sumXY); //we want our weight to be a whole number, not a floating point number

        //we want to limit the scope of weights to only 2 digit integers so we apply the following algo
        if (Weight >= 100) {
            Weight /= 10;
            return Weight;
        }
        else if (Weight < 100 && Weight >= 10) {
            return Weight /= 10;
        }
        return Weight;
    }

    //getter method for edgeWeight
    public int getEdgeWeight() {
        return edgeWeight;
    }

}
