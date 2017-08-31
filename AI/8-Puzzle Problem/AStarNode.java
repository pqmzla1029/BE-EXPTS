package AI;

import java.util.ArrayList;

//Nodes for handling state data
public class AStarNode {

    String name;
    ArrayList<Integer> state;
    AStarNode parent;
    int distance;
    String move;
    public int priority;

    public AStarNode(String name) {
        this.name = name;
    }

    AStarNode() {
    }

    public String getName() {
        return this.name;
    }
}
