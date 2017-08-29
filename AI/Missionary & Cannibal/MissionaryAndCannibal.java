/*
 Name		: Igor Monteiro
 Roll No.	: 7397
 Year		: BE
 Department	: COMPS
 Topic		: Missionaries and Cannibal
 Subject	: AI
 Date           :
 */

package AI;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

enum Position {

    RIGHT, LEFT
}

public class MissionaryAndCannibal {

    public static void main(String[] args) {
        System.out.println("==== Missionaries and Cannibals Problem ====");
        System.out.println("Choose the search method: ");
        System.out.println("\t 1. Breadth-first search");
        System.out.println("\t 2. Depth-limited search");
        System.out.print("\nType your choice and press ENTER: ");

        String optionStr = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            optionStr = in.readLine();
        } catch (IOException e) {
            System.out.println("[ERROR] Fail to read the typed option.");
            e.printStackTrace();
        }

        int option = Integer.parseInt(optionStr);
        MissionaryAndCannibalState initialState = new MissionaryAndCannibalState(3, 3, Position.LEFT, 0, 0);
        switch (option) {
            case 1:
                executeBFS(initialState);
                break;
            case 2:
                executeDLS(initialState);
                break;
            default:
                System.out.println("[ERROR] Invalid search option.");
        }
    }

    private static void executeBFS(MissionaryAndCannibalState initialState) {
        MACBFS search = new MACBFS();
        MissionaryAndCannibalState solution = search.exec(initialState);
        printSolution(solution);
    }

    private static void executeDLS(MissionaryAndCannibalState initialState) {
        MACDFSL search = new MACDFSL();
        MissionaryAndCannibalState solution = search.exec(initialState);
        printSolution(solution);
    }

    private static void printSolution(MissionaryAndCannibalState solution) {
        if (null == solution) {
            System.out.print("\nNo solution found.");
        } else {
            System.out.println("\nSolution (cannibalLeft,missionaryLeft,boat,cannibalRight,missionaryRight): ");
            List<MissionaryAndCannibalState> path = new ArrayList<MissionaryAndCannibalState>();
            MissionaryAndCannibalState state = solution;
            while (null != state) {
                path.add(state);
                state = state.getParentState();
            }

            int depth = path.size() - 1;
            for (int i = depth; i >= 0; i--) {
                state = path.get(i);
                if (state.isGoal()) {
                    System.out.print(state.toString());
                } else {
                    System.out.println(state.toString() + "\n     |\n     V ");
                }
            }
            System.out.println("\nDepth: " + depth);
        }
    }
}
