package fall2018.csc2017.slidingtiles.sudokugames.controller;
/*
* controller for joining view with controller part, observe every part of the current movement of games
* and help do the corresponding reaction
* */
import android.content.Context;
import android.content.Intent;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.MovementController;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;
import fall2018.csc2017.slidingtiles.sudokugames.view.FinalScoreSKActivity;

public class MovementControllerSK extends MovementController {
    private BoardManagerSudoku boardManagerSK;
    private int selectedPos = 0;
    private boolean isSelected = false;

    public MovementControllerSK (BoardManagerSudoku manager) {
        this.boardManagerSK = manager;
    }

    @Override
    public void setBoardManager(BasicBoardManager boardManager) {
        boardManagerSK = (BoardManagerSudoku) boardManager;
    }

    @Override
    public void processTapMovement(Context context, int position){
        System.out.println("haha, I am here!");
        if (boardManagerSK.isValidTap(position)) {

            if (boardManagerSK.hasWon()) {
                SaveScore saveTool = new SaveScore();
                saveTool.saveScoreIntoMap(context, boardManagerSK.getGameIndex(), boardManagerSK.getScore());
                Intent intent = new Intent(context, FinalScoreSKActivity.class);
                context.startActivity(intent);
            }
            if (isSelected) {
                boardManagerSK.updateSudokuTiles(0, selectedPos);
            }
            this.selectedPos = position;
            isSelected = true;
            boardManagerSK.updateSudokuTiles(-1, position);
        }

        System.out.println("current position is: " + position);
    }
    /**
     * to load the position of the selected target
     */
    public void loadVal(Context ctx, int val) {

        UserPanel panel = UserPanel.getInstance();
        GameCacheSystem sys = GameCacheSystem.getInstance();
        boardManagerSK.addScore();
        System.out.println("updating tiles...");
        boardManagerSK.updateSudokuTiles(val, selectedPos);
        sys.update(panel.getName(), boardManagerSK);
        sys.save(User.SD_GAME_INDEX, ctx);
        if (boardManagerSK.hasWon()) {
            SaveScore saveTool = new SaveScore();
            saveTool.saveScoreIntoMap(ctx, boardManagerSK.getGameIndex(), boardManagerSK.getScore());
            Intent intent = new Intent(ctx, FinalScoreSKActivity.class);
            ctx.startActivity(intent);
        }
        //        isSelected = false;
    }

    /**
     * return if the position has been selected or not
     */
    public boolean selected() {
        return isSelected;
    }

    /**
     *revert the current selected state of the activity
     */
    public void changeSelect() {
        isSelected = !isSelected;
    }
}
