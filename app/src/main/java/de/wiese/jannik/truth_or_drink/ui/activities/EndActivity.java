package de.wiese.jannik.truth_or_drink.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import de.wiese.jannik.truth_or_drink.MainActivity;
import de.wiese.jannik.truth_or_drink.R;

public class EndActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        initUi();
    }

    private void initUi() {
        Bundle extras = getIntent().getExtras();

        TextView totalCardsView = findViewById(R.id.txt_total_cards);
        TextView player1Name = findViewById(R.id.txt_end_player_1);
        TextView player1Sips = findViewById(R.id.txt_drinks_1);
        TextView player2Name = findViewById(R.id.txt_end_player_2);
        TextView player2Sips = findViewById(R.id.txt_drinks_2);

        totalCardsView.setText(extras.getString(GameActivity.TOTAL_QUESTIONS_KEY));
        player1Name.setText(extras.getString(MainActivity.PLAYER_1_KEY));
        player1Sips.setText("" + extras.getInt(GameActivity.PLAYER_1_DRINKS_KEY));
        player2Name.setText(extras.getString(MainActivity.PLAYER_2_KEY));
        player2Sips.setText("" + extras.getInt(GameActivity.PLAYER_2_DRINKS_KEY));
    }
}