package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Map;

import fall2018.csc2017.slidingtiles.Helpers.ActivityHelper;
import fall2018.csc2017.slidingtiles.SlidingGame.Managers.BoardManager;
import fall2018.csc2017.slidingtiles.users.User;
import fall2018.csc2017.slidingtiles.users.UserRouter;

public class LoginActivity extends AppCompatActivity {

    private final static int NumOfUsers = 16;

    public final static HashMap<String, BoardManager> userBoardHashMap = new HashMap<>(NumOfUsers);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadFromFile(StartingActivity.TEMP_SAVE_FILENAME);

        if(userBoardHashMap.size() == 0){
            ActivityHelper.saveToFile(StartingActivity.TEMP_SAVE_FILENAME, this, userBoardHashMap);
        }
        addLoginListener();
    }


    /**
     * disable the button and show alert information
    */

    private void addLoginListener() {
        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: add verification
                EditText username = (EditText) findViewById(R.id.input_email);
                EditText password = (EditText) findViewById(R.id.input_password);

                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                User user = new User(usernameText, passwordText);
                UserRouter router = new UserRouter(getContext());
                final TextView invalidView = findViewById(R.id.invalidWord);

                if (!router.verifyUser(user, false)) {
                    ActivityHelper.disableButton(v, invalidView, "Username/Password not matched!");
                } else {
                    switchToPanel();
                }
            }
        });
    }

    private void switchToPanel() {
        Intent tmp = new Intent(this, GameCenterActivity.class);
        startActivity(tmp);
    }

    private Context getContext() {
        return this.getApplicationContext();
    }

    public void switchToRegister(View v) {
        TextView register = (TextView) findViewById(R.id.link_signup);
        register.setTextColor(Color.WHITE);
        Intent tmp = new Intent(this, RegisterActivity.class);
        startActivity(tmp);
    }

    /**
     * Load the board manager from fileName.
     *
     * @param fileName the name of the file
     */
    @SuppressWarnings("unchecked")
    private void loadFromFile(String fileName) {
        try {
            InputStream inputStream = this.openFileInput(fileName);
            if (inputStream != null) {
                ObjectInputStream input = new ObjectInputStream(inputStream);
                HashMap<String, BoardManager> newMap = (HashMap<String, BoardManager>) input.readObject();
                for(Map.Entry<String, BoardManager> entry: newMap.entrySet()) {
                    String key = entry.getKey();
                    BoardManager board = entry.getValue();
                    userBoardHashMap.put(key, board);
                }
                inputStream.close();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        } catch (ClassNotFoundException e) {
            Log.e("login activity", "File contained unexpected data type: " + e.toString());
        }
    }

}
