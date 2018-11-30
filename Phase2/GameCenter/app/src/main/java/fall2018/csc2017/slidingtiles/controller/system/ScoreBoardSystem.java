package fall2018.csc2017.slidingtiles.controller.system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import fall2018.csc2017.slidingtiles.helper.IOHelper;
import fall2018.csc2017.slidingtiles.helper.SequenceBundlers;
import fall2018.csc2017.slidingtiles.model.GameScore;

/**
 * this is a ScoreBoard System providing score data for all games, and help render the scenes in
 * activities related to score board.
 */
public class ScoreBoardSystem{

    /**
     * the states for possible different games
     */
    private List<Map<String, int[]>> playerStates;

    public ScoreBoardSystem(GameScore[] scoreModels) {
        initialize(scoreModels);
    }

    /**
      *help load all the data of scores this will load the state in order convenient mode
     */
    @SuppressWarnings("unchecked")
    private void initialize(GameScore[] models) {

        List <Map<String, int[]>> states = new ArrayList<>();
        for (GameScore model: models) {
            Map<String, int[]> state = model.data();
            states.add(state);
        }
        playerStates = states;
    }

    /**
      * make the views in the view list display the desirable content of score state at the
      * specific index.
      * @param index: the index of the score state (follow the index of the constructor scorefiles)
      */
    public List<SequenceBundlers> displayScore(int index) {
        Map<String, int[]> map = playerStates.get(index);

        if (map == null) {
            System.out.println("score at index: " + index + "does not exisit");
            return null;
        }

        List<SequenceBundlers> bd = IOHelper.convertMap(map);
        Collections.sort(bd);
        return bd;
    }
}
