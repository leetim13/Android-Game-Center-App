package fall2018.csc2017.slidingtiles.tfgames.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fall2018.csc2017.slidingtiles.GameCenterActivity;
import fall2018.csc2017.slidingtiles.R;

public class YouLoseTFActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_lose_tf);
        addGameCenterButtonListener();

    }
    /**
     * Activate the game center button.
     */
    private void addGameCenterButtonListener() {
        Button startButton = findViewById(R.id.GameCenterButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToGameCenter();
            }
        });
    }
    public void switchToGameCenter() {
        Intent tmp = new Intent(this, GameCenterActivity.class);
        startActivity(tmp);
    }
}
