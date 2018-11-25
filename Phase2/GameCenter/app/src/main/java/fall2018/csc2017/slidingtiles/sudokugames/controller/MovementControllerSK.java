package fall2018.csc2017.slidingtiles.sudokugames.controller;

import android.content.Context;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.MovementController;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.sudokugames.controller.BoardManagerSudoku;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

public class MovementControllerSK extends MovementController {
    private BoardManagerSudoku boardManagerSK = new BoardManagerSudoku(9);
    private SaveScore saveScore = new SaveScore();
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
            if (isSelected) {
                boardManagerSK.updateSudokuTiles(0, selectedPos);
            }
            this.selectedPos = position;
            isSelected = true;
            boardManagerSK.updateSudokuTiles(-1, position);
        }

        System.out.println("current position is: " + position);
    }
    /*
    * load the position of the selected target
    * */
    public void loadVal(Context ctx, int val) {
        UserPanel panel = UserPanel.getInstance();
        GameCacheSystem sys = GameCacheSystem.getInstance();
        boardManagerSK.addScore();
        System.out.println("updating tiles...");
        boardManagerSK.updateSudokuTiles(val, selectedPos);
        sys.update(panel.getName(), boardManagerSK);
        sys.save(User.SD_GAME_INDEX, ctx);
    }
    /*
    * return if the position has been selected or not
    * */
    public boolean selected() {
        return isSelected;
    }
    /**
     *revert the current selected state of the activity
     */
    public void changeSelect() {
        isSelected = !isSelected;
    }
    /*
    * get the current selected position for use
    * */
    public int getSelectedPos() {
        return selectedPos;
    }
}
