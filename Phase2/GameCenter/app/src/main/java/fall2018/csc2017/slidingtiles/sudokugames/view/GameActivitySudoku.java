package fall2018.csc2017.slidingtiles.sudokugames.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.component.User;
import fall2018.csc2017.slidingtiles.controller.GestureDetectGridView;
import fall2018.csc2017.slidingtiles.controller.MovementControllerSK;
import fall2018.csc2017.slidingtiles.controller.MovementControllerTF;
import fall2018.csc2017.slidingtiles.controller.UserRouter;
import fall2018.csc2017.slidingtiles.helper.ActivityHelper;
import fall2018.csc2017.slidingtiles.helper.CustomAdapter;
import fall2018.csc2017.slidingtiles.helper.SaveScore;
import fall2018.csc2017.slidingtiles.slidinggames.component.Board;
import fall2018.csc2017.slidingtiles.slidinggames.component.ImageTile;
import fall2018.csc2017.slidingtiles.slidinggames.manager.BoardManager;
import fall2018.csc2017.slidingtiles.slidinggames.view.StartingActivity;
import fall2018.csc2017.slidingtiles.slidinggames.view.TileSettingsActivity;
import fall2018.csc2017.slidingtiles.sudokugames.component.BoardSudoku;
import fall2018.csc2017.slidingtiles.sudokugames.manager.BoardManagerSudoku;
import fall2018.csc2017.slidingtiles.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.system.UserPanel;
import fall2018.csc2017.slidingtiles.tfgames.component.BoardTF;
import fall2018.csc2017.slidingtiles.tfgames.managers.BoardManagerTF;
import fall2018.csc2017.slidingtiles.tfgames.view.GameActivityTF;
import fall2018.csc2017.slidingtiles.tfgames.view.OnSwipeTouchListener;

public class GameActivitySudoku extends AppCompatActivity implements Observer{

    /**
     * The board manager.
     */
    private BoardManagerSudoku boardManager;
    public final static String SELECT_FIRST_WARNNING = "Select a target first!";

    /**
     * The buttons to display.
     */
    private List <Button> tileButtons;
    private int[] buttonList = {R.id.s1, R.id.s2, R.id.s3, R.id.s4, R.id.s5, R.id.s6, R.id.s7, R.id.s8, R.id.s9};
    private MovementControllerSK controllerSK; // control part into controller
//    /**
//     * Constants for swiping directions. Should be an enum, probably.
//     */
//    public static final int UP = 1;
//    public static final int DOWN = 2;
//    public static final int LEFT = 3;
//    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private int columnWidth, columnHeight;

    /**
     * Set up the background image for each button based on the master list
     * of positions, and then call the adapter to set the view.
     */
    // Display
    public void display() {
        try {
            updateTileButtons();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gridView.setAdapter(new CustomAdapter(tileButtons, columnWidth, columnHeight));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boardManager = (BoardManagerSudoku) GameCacheSystem.getInstance().get(UserPanel.getInstance().getName());

        if (boardManager == null) {
            boardManager = new BoardManagerSudoku(9);
        }

        createTileButtons(this);
        setContentView(R.layout.activity_main_sudoku);

        this.controllerSK = new MovementControllerSK(boardManager);
        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setController(controllerSK); // set the  controller so it can be applied to sudoku game
        gridView.setNumColumns(boardManager.getBoardNumOfCols());
//        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
//        gridView.setGameActivity(this);

        // Observer sets up desired dimensions as well as calls our display function
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        gridView.getViewTreeObserver().removeOnGlobalLayoutListener(
                                this);
                        int displayWidth = gridView.getMeasuredWidth();
                        int displayHeight = gridView.getMeasuredHeight();
                        columnWidth = displayWidth / boardManager.getBoardNumOfCols();
                        columnHeight = displayHeight / boardManager.getBoardNumOfRows();
                        display();
                    }
                });

        addCheckListener();
        addSelectionListener(); // used for buttons
    }
    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        BoardSudoku board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoardNumOfRows(); row++) {
            for (int col = 0; col != boardManager.getBoardNumOfCols(); col++) {
                Button tmp = new Button(context);
                tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() throws IOException {

        GameCacheSystem system = GameCacheSystem.getInstance();
        UserPanel user = UserPanel.getInstance();
        BoardSudoku board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoardNumOfRows();
            int col = nextPos % boardManager.getBoardNumOfCols();
            b.setBackgroundResource(board.getTile(row, col).getBackground());
            System.out.println("style is here!");
            System.out.println(board.getTile(row, col).getBackground());
            nextPos++;
        }
//        LoginActivity.userBoardHashMap.put(UserPanel.getInstance().getName(), boardManager);
        System.out.println("tile style changed!");
        system.update(user.getName(), boardManager);
//        ActivityHelper.saveToFile(UserRouter.GAME_STORAGE_TF, this,  GameCacheSystem.getInstance().getData());
        system.save(boardManager.getGameIndex(), this);
    }
    /*
    * check whether the user has won the saduko game
    * */
    private void addCheckListener() {
        Button bt = findViewById(R.id.check_button);
        final TextView notWin = findViewById(R.id.warningTextSK);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boardManager.hasWon()) {
                    SaveScore saveTool = new SaveScore();
                    saveTool.saveScoreIntoMap(getApplicationContext(), boardManager.getGameIndex(), boardManager.getScore());
                    Intent intent = new Intent(GameActivitySudoku.this, FinalScoreSKActivity.class);
                    startActivity(intent);
                }

                else {
                    ActivityHelper.disableButton(v,  notWin, "you haven't solved this game");
                }
            }
        });
    }

    /**
     * The maximum undo steps that the user sets.
     */
    static int maxUndoSteps;

    /**
     * Display error message as toast when no steps are made, but undo is clicked.
     */
    private void cannotUndoText(){
        Toast.makeText(this, "You should make a move first!", Toast.LENGTH_SHORT).show();
    }
    /*
    * add the listener for buttons handling the number sets
    * */
    private void addSelectionListener() {
        for (int id:buttonList) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (controllerSK.selected()) {
                        String text = ((Button) v).getText().toString();
                        boardManager.addScore();
                        int value = Integer.parseInt(text);
                        System.out.println("loading value:..." + value);
                        controllerSK.loadVal(getApplicationContext(), value);
                        controllerSK.changeSelect();
                    }

                    else {
                        TextView warnText = findViewById(R.id.warningTextSK);
                        ActivityHelper.disableButton(v, warnText, SELECT_FIRST_WARNNING);
                    }
                }
            });
        }
    }

    /**
     * Display remaining undo steps as toast.
     */
    private void remainedUndoText(){
        Toast.makeText(this, "Remained undo steps: " + maxUndoSteps, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void update(Observable o, Object arg) {
        display();
    }
}
