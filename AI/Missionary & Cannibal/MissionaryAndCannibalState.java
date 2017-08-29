/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Igor
 */
public class MissionaryAndCannibalState {

    private int cannibalLeft;
    private int missionaryLeft;
    private int cannibalRight;
    private int missionaryRight;
    private Position boat;

    private MissionaryAndCannibalState parentState;

    public MissionaryAndCannibalState(int cannibalLeft, int missionaryLeft, Position boat,
            int cannibalRight, int missionaryRight) {
        this.cannibalLeft = cannibalLeft;
        this.missionaryLeft = missionaryLeft;
        this.boat = boat;
        this.cannibalRight = cannibalRight;
        this.missionaryRight = missionaryRight;
    }

    public boolean isGoal() {
        return cannibalLeft == 0 && missionaryLeft == 0;
    }

    public boolean isValid() {
        if (missionaryLeft >= 0 && missionaryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
                && (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
                && (missionaryRight == 0 || missionaryRight >= cannibalRight)) {
            return true;
        }
        return false;
    }

    public List<MissionaryAndCannibalState> generateSuccessors() {
        List<MissionaryAndCannibalState> successors = new ArrayList<MissionaryAndCannibalState>();
        if (boat == Position.LEFT) {
            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft, missionaryLeft - 2, Position.RIGHT, cannibalRight, missionaryRight + 2));

            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft - 2, missionaryLeft, Position.RIGHT, cannibalRight + 2, missionaryRight));

            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft - 1, missionaryLeft - 1, Position.RIGHT, cannibalRight + 1, missionaryRight + 1));

            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft, missionaryLeft - 1, Position.RIGHT, cannibalRight, missionaryRight + 1));
            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft - 1, missionaryLeft, Position.RIGHT, cannibalRight + 1, missionaryRight));

        } else {
            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft, missionaryLeft + 2, Position.LEFT, cannibalRight, missionaryRight - 2));

            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft + 2, missionaryLeft, Position.LEFT, cannibalRight - 2, missionaryRight));
            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft + 1, missionaryLeft + 1, Position.LEFT, cannibalRight - 1, missionaryRight - 1));

            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft, missionaryLeft + 1, Position.LEFT, cannibalRight, missionaryRight - 1));

            testAndAdd(successors, new MissionaryAndCannibalState(cannibalLeft + 1, missionaryLeft, Position.LEFT, cannibalRight - 1, missionaryRight));
        }
        return successors;
    }

    private void testAndAdd(List<MissionaryAndCannibalState> successors, MissionaryAndCannibalState newState) {
        if (newState.isValid()) {
            newState.setParentState(this);
            successors.add(newState);
        }
    }

    public int getCannibalLeft() {
        return cannibalLeft;
    }

    public void setCannibalLeft(int cannibalLeft) {
        this.cannibalLeft = cannibalLeft;
    }

    public int getMissionaryLeft() {
        return missionaryLeft;
    }

    public void setMissionaryLeft(int missionaryLeft) {
        this.missionaryLeft = missionaryLeft;
    }

    public int getCannibalRight() {
        return cannibalRight;
    }

    public void setCannibalRight(int cannibalRight) {
        this.cannibalRight = cannibalRight;
    }

    public int getMissionaryRight() {
        return missionaryRight;
    }

    public void setMissionaryRight(int missionaryRight) {
        this.missionaryRight = missionaryRight;
    }

    public void goToLeft() {
        boat = Position.LEFT;
    }

    public void goToRight() {
        boat = Position.RIGHT;
    }

    public boolean isOnLeft() {
        return boat == Position.LEFT;
    }

    public boolean isOnRigth() {
        return boat == Position.RIGHT;
    }

    public MissionaryAndCannibalState getParentState() {
        return parentState;
    }

    public void setParentState(MissionaryAndCannibalState parentState) {
        this.parentState = parentState;
    }

    @Override
    public String toString() {
        if (boat == Position.LEFT) {
            return "(" + cannibalLeft + "," + missionaryLeft + ",L,"
                    + cannibalRight + "," + missionaryRight + ")";
        } else {
            return "(" + cannibalLeft + "," + missionaryLeft + ",R,"
                    + cannibalRight + "," + missionaryRight + ")";
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MissionaryAndCannibalState)) {
            return false;
        }
        MissionaryAndCannibalState s = (MissionaryAndCannibalState) obj;
        return (s.cannibalLeft == cannibalLeft && s.missionaryLeft == missionaryLeft
                && s.boat == boat && s.cannibalRight == cannibalRight
                && s.missionaryRight == missionaryRight);
    }
}
