package fall2018.csc2017.slidingtiles;

import android.content.Context;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fall2018.csc2017.slidingtiles.Components.ImageTile;
import fall2018.csc2017.slidingtiles.Helpers.ActivityHelper;
import fall2018.csc2017.slidingtiles.Helpers.OnSwipeTouchListener;
import fall2018.csc2017.slidingtiles.users.CustomAdapter;
import fall2018.csc2017.slidingtiles.users.UserPanel;

/**
 * The game activity.
 */
public class GameActivityTF extends AppCompatActivity implements Observer {

    /**
     * The board manager.
     */
    private BoardManager boardManager;

    /**
     * The buttons to display.
     */
    private ArrayList<Button> tileButtons;

//    /**
//     * Constants for swiping directions. Should be an enum, probably.
//     */
//    public static final int UP = 1;
//    public static final int DOWN = 2;
//    public static final int LEFT = 3;
//    public static final int RIGHT = 4;

    // Grid View and calculated column height and width based on device size
    private GestureDetectGridView gridView;
    private static int columnWidth, columnHeight;
    private TextView tvSwipDescription;

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

        boardManager = LoginActivity.userBoardHashMap.get(UserPanel.getInstance().getName());

        createTileButtons(this);
        setContentView(R.layout.activity_main);


        // Add View to activity
        gridView = findViewById(R.id.grid);
        gridView.setNumColumns(boardManager.getBoardNumOfCols());
        gridView.setBoardManager(boardManager);
        boardManager.getBoard().addObserver(this);
        //gridView.setGameActivity(this);

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
        addUndoButtonListener();
        initializeView();
        tvSwipDescription.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeDown() {
                Toast.makeText(GameActivityTF.this, "You swiped Down", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(GameActivityTF.this, "You swiped Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {
                Toast.makeText(GameActivityTF.this, "You swiped Up", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(GameActivityTF.this, "You swiped Right", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void initializeView() {
        tvSwipDescription=(TextView) findViewById(R.id.tvSwipDescription);
    }





    /**
     * Create the buttons for displaying the tiles.
     *
     * @param context the context
     */
    private void createTileButtons(Context context) {
        Board board = boardManager.getBoard();
        tileButtons = new ArrayList<>();
        for (int row = 0; row != boardManager.getBoardNumOfRows(); row++) {
            for (int col = 0; col != boardManager.getBoardNumOfCols(); col++) {
                Button tmp = new Button(context);
                if(TileSettingsActivity.isImageTile) {
                    Drawable drawable = new BitmapDrawable(tmp.getResources(),
                            ((ImageTile) board.getTile(row, col)).getBack());
                    tmp.setBackground(drawable);
                } else{
                    tmp.setBackgroundResource(board.getTile(row, col).getBackground());
                }
                this.tileButtons.add(tmp);
            }
        }
    }

    /**
     * Update the backgrounds on the buttons to match the tiles.
     */
    private void updateTileButtons() throws IOException {
        Board board = boardManager.getBoard();
        int nextPos = 0;
        for (Button b : tileButtons) {
            int row = nextPos / boardManager.getBoardNumOfRows();
            int col = nextPos % boardManager.getBoardNumOfCols();
            if(TileSettingsActivity.isImageTile) {
                Drawable drawable = new BitmapDrawable(b.getResources(),
                        ((ImageTile) board.getTile(row, col)).getBack());
                b.setBackground(drawable);
            } else{
                b.setBackgroundResource(board.getTile(row, col).getBackground());
            }
            nextPos++;
        }
        LoginActivity.userBoardHashMap.put(UserPanel.getInstance().getName(), boardManager);
        ActivityHelper.saveToFile(StartingActivity.TEMP_SAVE_FILENAME, this, LoginActivity.userBoardHashMap);

    }

    /**
     * The maximum undo steps that the user sets.
     */
    static int maxUndoSteps;
    /**
     * Activate the undo button.
     */
    private void addUndoButtonListener(){
        final ImageView undo = findViewById(R.id.imageView2);
        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gridView.stackIsEmpty()) {
                    cannotUndoText();
                    return;
                }
                if(maxUndoSteps > 0) {
                    int position = gridView.getUndoPop();
                    boardManager.touchMove(position);
                    LoginActivity.userBoardHashMap.put(UserPanel.getInstance().getName(), boardManager);
                    boardManager.minusScore();
                    maxUndoSteps--;
                }
                remainedUndoText();
                display();
            }
        });
    }
    /**
     * Display error message as toast when no steps are made, but undo is clicked.
     */
    private void cannotUndoText(){
        Toast.makeText(this, "You should make a move first!", Toast.LENGTH_SHORT).show();
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
