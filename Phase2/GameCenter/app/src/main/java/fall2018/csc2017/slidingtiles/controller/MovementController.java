package fall2018.csc2017.slidingtiles.controller;

import android.content.Context;

import fall2018.csc2017.slidingtiles.helper.structure.ArrayStack;

public abstract class MovementController {

    public ArrayStack <Integer> stateStack = new ArrayStack <Integer> (2000); // stateStack is for the number collection of the previous state

    public abstract void setBoardManager(BasicBoardManager boardManager);

    public abstract void processTapMovement(Context context, int position);

}
