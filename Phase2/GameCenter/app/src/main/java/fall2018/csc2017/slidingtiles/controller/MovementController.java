package fall2018.csc2017.slidingtiles.controller;

import android.content.Context;

import fall2018.csc2017.slidingtiles.component.BasicBoardManager;
import fall2018.csc2017.slidingtiles.structure.ArrayStack;

public abstract class MovementController {

    public ArrayStack stateStack = new ArrayStack(2000);

    public abstract void setBoardManager(BasicBoardManager boardManager);

    public abstract void processTapMovement(Context context, int position);

}
