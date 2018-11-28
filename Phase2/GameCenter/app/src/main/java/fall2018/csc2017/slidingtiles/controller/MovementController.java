package fall2018.csc2017.slidingtiles.controller;

import android.content.Context;

import fall2018.csc2017.slidingtiles.helper.structure.ArrayStack;

public abstract class MovementController {

    /**
     * the collection of the previous state, which is responsible to record what happen after
     * previous steps
     */
    public ArrayStack stateStack;

    public abstract void setBoardManager(BasicBoardManager boardManager);

    public abstract void processTapMovement(Context context, int position);

}
