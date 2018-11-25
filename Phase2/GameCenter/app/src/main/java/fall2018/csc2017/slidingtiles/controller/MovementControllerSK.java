package fall2018.csc2017.slidingtiles.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;
import fall2018.csc2017.slidingtiles.component.User;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.sudokugames.manager.BoardManagerSudoku;
import fall2018.csc2017.slidingtiles.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.system.UserPanel;

public class MovementControllerSK extends MovementController {
    private BoardManagerSudoku boardManagerSK = new BoardManagerSudoku(9);
    private SaveScore saveScore = new SaveScore();
    private int selectedPos = 0;
    @Override
    public void setBoardManager(BasicBoardManager boardManager) {
        boardManagerSK = (BoardManagerSudoku) boardManager;
    }

    @Override
    public void processTapMovement(Context context, int position){
        this.selectedPos = position;
    }
    /*
    * load the position of the selected target
    * */
    public void loadVal(Context ctx, int val) {
        UserPanel panel = UserPanel.getInstance();
        GameCacheSystem sys = GameCacheSystem.getInstance();
        boardManagerSK.addScore();
        boardManagerSK.updateSudokuTiles(val, selectedPos);
        sys.update(panel.getName(), boardManagerSK);
        sys.save(User.SD_GAME_INDEX, ctx);
    }
}
