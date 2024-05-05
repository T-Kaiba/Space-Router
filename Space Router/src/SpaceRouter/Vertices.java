package SpaceRouter;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * This class basically focuses on the Concept of Vertices in an MST.
 * Vertices are points to which edges are connected.
 * The following class Vertices extends from javafx.scene.shape.Circle and has all properties related to that class.
**/

public class Vertices extends Circle{

    private int hasEdges; // the number of edges attached to a vertex

    //Constructor for class Vertices.
    //Constructs an object vertex with predefined arguments to make an object with unique properties
    public Vertices(double x, double radius, int vertexNumber){

        //Calls the constructor of the superclass Circle to make a circle object that is to be modified to make a vertex
        super(x,(Math.random()*400)+50,radius);

        //setting the remaining properties of the vertex object
        this.setFill(Color.color(Math.random(),Math.random(),Math.random()));
        this.setStroke(Color.WHITE);
        this.setStrokeWidth(3);
        this.hasEdges = 0;
    }

    //Increments the hasEdges property for a vertex whenever an associated edge object is made
    public void incrementEdges(){
        this.hasEdges = ++hasEdges;
    }

}
