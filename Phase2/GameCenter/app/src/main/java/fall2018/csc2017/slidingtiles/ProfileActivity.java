package fall2018.csc2017.slidingtiles;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.IOException;

import fall2018.csc2017.slidingtiles.controller.BasicBoardManager;
import fall2018.csc2017.slidingtiles.controller.StorageIndexer;
import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.controller.system.UserPanel;

/**
 * Excluded from tests because it's a view class.
 * The profile activity for an individual user's profile.
 * used to display the profile of the user
 */
public class ProfileActivity extends AppCompatActivity {

    /**
     * the title of the user profile
     */
    private TextView titleView;

    /**
     * the number of moves the player took in previous game
     */
    private TextView previousStateMove;

    /**
     * previous game name
     */
    private TextView previousStateType;

    /**
     * the name of the last game played
     */
    private TextView lastGamePlayed;

    /**
     * The text to show if there is no game played
     */
    public static final String INVALID_TEXT = "no games played";
    public final static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        display();
        addUploadImage();
    }

    /**
     * display the content on the profile page
    */
    private void display() {
        setConstant();
        GameCacheSystem sys = GameCacheSystem.getInstance();
        StorageIndexer indexer = new StorageIndexer();
        BasicBoardManager game = sys.get(UserPanel.getInstance().getName());
        int prevIndex = GameCacheSystem.getInstance().prevGame();
        String prevGame = processPrexIndex(prevIndex, game);
        String numMoves = (game != null) ? Integer.toString(game.getScore()): INVALID_TEXT;
        String lastGameName = indexer.getName(prevIndex);
        displayContent(lastGamePlayed, lastGameName);
        displayContent(titleView, UserPanel.getInstance().getName());
        displayContent(previousStateType, prevGame);
        displayContent(previousStateMove, numMoves);
    }

    /**
     * initiate the instance variable(or elements going to be displayed) for the screen
     */
    private void setConstant() {
        titleView = findViewById(R.id.tv_name);
        previousStateMove = findViewById(R.id.moveNum);
        previousStateType = findViewById(R.id.boardtype);
        lastGamePlayed = findViewById(R.id.last_play);
    }

    /**
     * set text for the content going to be displayed
    */
    private void displayContent(TextView v, String text) {
        if (text != null) {
            v.setText(text);
        }
    }

    /**
     * process the data of the previous index
     */
    private String processPrexIndex(int index, BasicBoardManager game) {

        StorageIndexer indexer = new StorageIndexer();
        if (index == -1) {
            return INVALID_TEXT;
        }
        return indexer.getName(index);
    }

    /**
     * To add OnclickListener for UploadImage
     */
    private void addUploadImage() {
        ImageView imageLoader = findViewById(R.id.avatar);
        imageLoader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selection = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(selection, RESULT_LOAD_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedTarget = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedTarget);
                BitmapDrawable paint = new BitmapDrawable(getResources(), bitmap);
                ImageView image = findViewById(R.id.avatar);
                image.setImageResource(0);
                image.setBackground(paint);
            }
            catch (IOException e) {
                System.out.println("no file specified");
            }
        }
    }

}
