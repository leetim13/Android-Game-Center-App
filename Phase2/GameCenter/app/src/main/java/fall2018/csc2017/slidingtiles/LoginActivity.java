package fall2018.csc2017.slidingtiles;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.helper.ActivityHelper;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.controller.UserRouter;

/**
 * Excluded from tests because it's a view class.
 * Login Activity for all games.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addLoginListener();
    }

    /**
     * add the login listener, identify and verify if login is valid
     */
    private void addLoginListener() {
        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: add verification
                EditText username = findViewById(R.id.input_email);
                EditText password = findViewById(R.id.input_password);

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

    /**
     * Switch to Game Center Activity.
     */
    private void switchToPanel() {
        Intent tmp = new Intent(this, GameCenterActivity.class);
        startActivity(tmp);
    }

    /**
     * Get context of current game
     */
    private Context getContext() {
        return this.getApplicationContext();
    }

    /**
     * Switch to Register Activity.
     */
    public void switchToRegister(View v) {
        TextView register = findViewById(R.id.link_signup);
        register.setTextColor(Color.WHITE);
        Intent tmp = new Intent(this, RegisterActivity.class);
        startActivity(tmp);
    }

}
