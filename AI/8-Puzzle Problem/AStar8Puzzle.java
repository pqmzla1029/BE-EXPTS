
package AI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

public class AStar8Puzzle {

    public static void main(String args[]) {

        int inputNumber = 0;
        Scanner sc = new Scanner(System.in);
        AStarNode[] states = new AStarNode[4];
        AStarNode goalNodeFound = new AStarNode();
        goalNodeFound = null;
        Stack stack = new Stack();
        AStarNode current = new AStarNode();
        LinkedList<ArrayList<?>> visited = new LinkedList<ArrayList<?>>();
        int count = 0;

        //Creating start node
        AStarNode start = new AStarNode();
        ArrayList<Integer> startState = new ArrayList();

        /* Demo
         3 2 0
         6 1 5
         7 4 8
         */
        System.out.println("Enter the Puzzle to solve");
        for (int i = 0; i < 9; i++) {
            inputNumber = sc.nextInt();
            startState.add(inputNumber);
        }

        start.state = startState;
        start.parent = null;
        start.move = null;
        start.priority = 0;
        start.distance = -1;

        //Creating goal node
        AStarNode goal = new AStarNode();
        ArrayList<Integer> goalState = new ArrayList();

        /* Demo
         0 1 2
         3 4 5
         6 7 8
         */
        System.out.println("Enter the Goal State");
        for (int i = 0; i < 9; i++) {
            inputNumber = sc.nextInt();
            goalState.add(inputNumber);
        }

        //Creating goal node
        goal.state = goalState;
        goal.parent = null;
        goal.distance = -1;
        goal.move = null;

        Comparator<AStarNode> comparator = new AStarCompareNode();
        PriorityQueue<AStarNode> pQ = new PriorityQueue<AStarNode>(100, comparator);
        pQ.add(start);
        visited.add(start.state);

        while (!pQ.isEmpty()) {
            count++;
            current = pQ.remove();
            states = findStates(current);

            for (int i = 0; i <= 3; i++) {
                if (states[i] != null) {
                    if (states[i].state.equals(goal.state)) {
                        goalNodeFound = states[i];
                        break;
                    } else {
                        if (!visited.contains(states[i].state)) {
                            states[i].distance = current.distance + 1;
                            visited.add(states[i].state);
                            states[i].priority = Math.min(costFunction(states[i], goal),costFunctionLength(states[i], goal));
                            pQ.add(states[i]);
                        }
                    }
                }
            }
            if (goalNodeFound != null) {
                break;
            }

        }

        while (goalNodeFound.parent != null) {
            if (goalNodeFound.move != null) {
                stack.push(goalNodeFound.move);
            }
            goalNodeFound = goalNodeFound.parent;
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        System.out.println(count + " Nodes expanded.");

    }

    private static int costFunction(AStarNode node, AStarNode goal) {

        int priority;
        int count = 0;

        //A* Function Calculation
        for (int i = 0; i < 9; i++) {
            if (node.state.get(i) != goal.state.get(i)) {
                count++;
            }
        }

        priority = node.distance + count;
        return priority;
    }



    private static int costFunctionLength(AStarNode node, AStarNode goal) {

        int priority;
        int count = 0;
        int index;
        //A* Function Calculation

        for (int i = 0; i < 9; i++) {
            index = goal.state.indexOf(node.state.get(i));
            count = count + Math.abs(index - i);
        }

        priority = node.distance + count;
        return priority;
    }


    //Find Subsequent States of the parent
    private static AStarNode[] findStates(AStarNode state) {
        AStarNode state1, state2, state3, state4;

        state1 = moveUP(state);
        state2 = moveDOWN(state);
        state3 = moveLEFT(state);
        state4 = moveRIGHT(state);

        AStarNode[] states = {state1, state2, state3, state4};

        return states;
    }

    //Move Blank Tile to the Right
    private static AStarNode moveRIGHT(AStarNode node) {
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        AStarNode childNode = new AStarNode();

        if (space != 2 && space != 5 && space != 8) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space + 1);
            childState.set(space + 1, 0);
            childState.set(space, temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "RIGHT";
            return childNode;
        } else {
            return null;
        }
    }

    //Move Blank Tile to the Left
    private static AStarNode moveLEFT(AStarNode node) {
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        AStarNode childNode = new AStarNode();

        if (space != 0 && space != 3 && space != 6) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space - 1);
            childState.set(space - 1, 0);
            childState.set(space, temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "LEFT";
            return childNode;
        } else {
            return null;
        }
    }

    //Move Blank Tile Downwards
    private static AStarNode moveDOWN(AStarNode node) {
        // TODO Auto-generated method stub
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        AStarNode childNode = new AStarNode();

        if (space <= 5) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space + 3);
            childState.set(space + 3, 0);
            childState.set(space, temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "DOWN";
            return childNode;
        } else {
            return null;
        }
    }

    //Move Blank Tile Upwards
    private static AStarNode moveUP(AStarNode node) {
        int space = node.state.indexOf(0);
        ArrayList<Integer> childState;
        int temp;
        AStarNode childNode = new AStarNode();

        if (space > 2) {
            childState = (ArrayList<Integer>) node.state.clone();
            temp = childState.get(space - 3);
            childState.set(space - 3, 0);
            childState.set(space, temp);
            childNode.state = childState;
            childNode.parent = node;
            childNode.distance = node.distance + 1;
            childNode.move = "UP";
            return childNode;
        } else {
            return null;
        }
    }

}
