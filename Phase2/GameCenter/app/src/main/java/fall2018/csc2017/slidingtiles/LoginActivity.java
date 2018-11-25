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

import fall2018.csc2017.slidingtiles.controller.system.GameCacheSystem;
import fall2018.csc2017.slidingtiles.helper.ActivityHelper;
import fall2018.csc2017.slidingtiles.slidinggames.controller.BoardManager;
import fall2018.csc2017.slidingtiles.model.component.User;
import fall2018.csc2017.slidingtiles.controller.UserRouter;
import fall2018.csc2017.slidingtiles.slidinggames.view.StartingActivity;

public class LoginActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        addLoginListener();
    }
    /*
    add the login listener
    * */
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

}
