package de.wiese.jannik.truth_or_drink;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.wiese.jannik.truth_or_drink.ui.activities.GameActivity;

public class MainActivity extends Activity {

    public static final String PLAYER_1_KEY = "Player 1 Name";
    public static final String PLAYER_2_KEY = "Player 2 Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }

    private void initUi() {
        EditText player1Text = findViewById(R.id.et_player_1_name);
        EditText player2Text = findViewById(R.id.et_player_2_name);
        Button startGameButton = findViewById(R.id.btn_start_game);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String player1Name = "Spieler 1";
                String player2Name = "Spieler 2";

                String player1EtContent = player1Text.getText().toString();
                String player2EtContent = player2Text.getText().toString();

                if (!player1EtContent.equals("")) {
                    player1Name = player1EtContent;
                }
                if (!player2EtContent.equals("")) {
                    player2Name = player2EtContent;
                }

                Intent startGameIntent = new Intent(MainActivity.this, GameActivity.class);

                startGameIntent.putExtra(PLAYER_1_KEY, player1Name);
                startGameIntent.putExtra(PLAYER_2_KEY, player2Name);

                startActivity(startGameIntent);
            }

        });
    }
}