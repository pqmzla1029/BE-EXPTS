
package AI;

import java.util.Comparator;

//Handle Distance from goal by setting Priorities
public class AStarCompareNode implements Comparator<AStarNode> {

    @Override
    public int compare(AStarNode node1, AStarNode node2) {

        if (node1.priority > node2.priority) {
            return 1;
        }
        if (node1.priority < node2.priority) {
            return -1;
        }
        return 0;
    }
}
