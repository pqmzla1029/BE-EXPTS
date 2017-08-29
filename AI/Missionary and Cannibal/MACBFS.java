/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MACBFS {

    public MissionaryAndCannibalState exec(MissionaryAndCannibalState initialState) {
        if (initialState.isGoal()) {
            return initialState;
        }
        Queue<MissionaryAndCannibalState> frontier = new LinkedList<MissionaryAndCannibalState>();	// FIFO queue
        Set<MissionaryAndCannibalState> explored = new HashSet<MissionaryAndCannibalState>();
        frontier.add(initialState);
        while (true) {
            if (frontier.isEmpty()) {
                return null;	// failure
            }
            MissionaryAndCannibalState state = frontier.poll();
            explored.add(state);
            List<MissionaryAndCannibalState> successors = state.generateSuccessors();
            for (MissionaryAndCannibalState child : successors) {
                if (!explored.contains(child) || !frontier.contains(child)) {
                    if (child.isGoal()) {
                        return child;
                    }
                    frontier.add(child);
                }
            }
        }
    }
}
