package AI;

import java.util.List;

// Depth First Search with depth limitation DFID
public class MACDFSL {

    public MissionaryAndCannibalState exec(MissionaryAndCannibalState initialState) {
        int limit = 20;
        return recursiveDLS(initialState, limit);
    }

    private MissionaryAndCannibalState recursiveDLS(MissionaryAndCannibalState state, int limit) {
        if (state.isGoal()) {
            return state;
        } else if (limit == 0) {
            return null;
        } else {
            List<MissionaryAndCannibalState> successors = state.generateSuccessors();
            for (MissionaryAndCannibalState child : successors) {
                MissionaryAndCannibalState result = recursiveDLS(child, limit - 1);
                if (null != result) {
                    return result;
                }
            }
            return null;
        }
    }

}
