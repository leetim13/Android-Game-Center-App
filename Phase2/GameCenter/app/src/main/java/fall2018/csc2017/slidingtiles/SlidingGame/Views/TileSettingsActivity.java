package fall2018.csc2017.slidingtiles.SlidingGame.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.HashMap;

import fall2018.csc2017.slidingtiles.SlidingGame.Managers.BoardManager;
import fall2018.csc2017.slidingtiles.LoginActivity;
import fall2018.csc2017.slidingtiles.R;
import fall2018.csc2017.slidingtiles.SlidingGame.Components.ImageTile;
import fall2018.csc2017.slidingtiles.Helpers.ActivityHelper;
import fall2018.csc2017.slidingtiles.Structures.InputFilterMinMax;
import fall2018.csc2017.slidingtiles.users.UserPanel;

/**
 * The Tiles Settings Activity that is the initial activity when the user clicks on the tiles game.
 */
public class TileSettingsActivity extends AppCompatActivity{
    /**
     * The main radioGroup of 3 RadioButton for each board complexity.
     */
    RadioGroup radioGroup;
    final static int RESULT_LOAD_IMAGE = 1;
    /**
     * The 3 RadioButton for each corresponding board complexity.
     */
    RadioButton radioButton;

    public static Bitmap chosenImage;
    /**
     * The maximum number of undo steps per move (inputted by user).
     */

    public static boolean isImageTile = false; // whether we should use the ImageTile

    private static int undo;

    private HashMap<String, Integer> hook = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tile_settings);
        setConstant();

        final Button buttonApply = findViewById(R.id.button_apply);

        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //onClick button for apply button
                int radioID = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(radioID); //use radioButton.getID() to see which board is selected
                String s = radioButton.getResources().getResourceEntryName(radioID); // s is now "three", "four", "or "five".

                EditText mEdit = findViewById(R.id.text_undo);
                String setting = mEdit.getText().toString();
                undo = (setting.equals("")) ? 0 : Integer.valueOf(setting);

                if (undo >= 3) {
                    GameActivity.maxUndoSteps = undo;
                    int i = hook.get(s);
                    if (chosenImage != null && isImageTile) {
                        ImageTile.bitmapCollection = ActivityHelper.splitBitmap(chosenImage, i, i);
                    } else {
                        isImageTile = false;
                    }

                    BoardManager boardManager = new BoardManager(i, i);
                    LoginActivity.userBoardHashMap.put(UserPanel.getInstance().getName(), boardManager);
                    switchToGame();
                }

                else {
                    final TextView invalidView = findViewById(R.id.text_tile_warnning);
                    ActivityHelper.disableButton(v, invalidView, "Minimum number of undo steps should be at least 3");
                }
            }
        });

        addUploadImage();
    }

    private void setConstant() {
        hook.put("three", 3);
        hook.put("four", 4);
        hook.put("five", 5);

        radioGroup = findViewById(R.id.radioGroup);
        EditText et = findViewById(R.id.text_undo);
        et.setFilters(new InputFilter[]{ new InputFilterMinMax("3", "100000")});
        et.setText("3");

    }

    private void addUploadImage() {
        Button imageLoader = findViewById(R.id.image_loader);
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
                chosenImage = bitmap;
                isImageTile = true;
            }
            catch (IOException e) {
                System.out.println("no file specified");
            }
        }
    }

    private void switchToGame(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
