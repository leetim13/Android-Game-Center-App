package fall2018.csc2017.slidingtiles.controller;

import android.content.Context;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;

public abstract class MovementController {

    public abstract void setBoardManager(BasicBoardManager boardManager);

    public abstract void processTapMovement(Context context, int position);

}
