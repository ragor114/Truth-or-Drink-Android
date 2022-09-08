package de.wiese.jannik.truth_or_drink.ui.activities;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.wiese.jannik.truth_or_drink.MainActivity;
import de.wiese.jannik.truth_or_drink.R;
import de.wiese.jannik.truth_or_drink.game.PlayerManager;
import de.wiese.jannik.truth_or_drink.game.cards.CardManager;
import de.wiese.jannik.truth_or_drink.ui.fragments.CardFragment;

public class GameActivity extends FragmentActivity implements CardFragment.SelectionListener {

    public static final String PLAYER_1_DRINKS_KEY = "Points of Player 1";
    public static final String PLAYER_2_DRINKS_KEY = "Points of Player 2";
    public static final String TOTAL_QUESTIONS_KEY = "How many questions";

    private PlayerManager playerManager;
    private CardManager cardManager;
    private TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initPlayers();
        initCards();
        initUi();
    }

    private void initPlayers() {
        playerManager = new PlayerManager();

        Bundle extras = getIntent().getExtras();
        playerManager.addPlayer(extras.getString(MainActivity.PLAYER_1_KEY));
        playerManager.addPlayer(extras.getString(MainActivity.PLAYER_2_KEY));
    }

    private void initCards() {
        cardManager = new CardManager(this);
    }

    private void initUi() {
        header = findViewById(R.id.txt_round);

        findViewById(R.id.btn_end_game).setOnClickListener(v -> endButtonClicked());

        ImageView cardDeck = findViewById(R.id.img_card_deck);
        cardDeck.setOnClickListener(v -> onCardDeckClicked());

        setTopText();
    }

    private void endButtonClicked() {
        Toast.makeText(this, "End of Game", Toast.LENGTH_SHORT).show();

        Intent endscreenIntent = new Intent(GameActivity.this, EndActivity.class);
        endscreenIntent.putExtra(PLAYER_1_DRINKS_KEY, playerManager.getPlayer1().getNumberOfDrinks());
        endscreenIntent.putExtra(PLAYER_2_DRINKS_KEY, playerManager.getPlayer2().getNumberOfDrinks());
        endscreenIntent.putExtra(TOTAL_QUESTIONS_KEY, cardManager.getPlayedCards());
        endscreenIntent.putExtra(MainActivity.PLAYER_1_KEY, playerManager.getPlayer1().getName());
        endscreenIntent.putExtra(MainActivity.PLAYER_2_KEY, playerManager.getPlayer2().getName());
        startActivity(endscreenIntent);
    }

    private void onCardDeckClicked() {
        // Toast.makeText(this, "Deck of Cards Clicked", Toast.LENGTH_SHORT).show();

        String cardText = cardManager.drawCard();
        if(cardText.equals(CardManager.FINAL_TEXT)) endButtonClicked();

        // Call Dialog Fragment
        CardFragment cardFrag = new CardFragment(this, cardText);
        cardFrag.show(getSupportFragmentManager(), null);
    }

    private void setTopText() {
        String headerText = getString(R.string.top_header_game);

        if (cardManager.isPlayer1Turn()) {
            headerText = headerText.replace("x", playerManager.getPlayer1().getName());
            headerText = headerText.replace("y", playerManager.getPlayer2().getName());
            header.setText(headerText);
            return;
        }

        headerText = headerText.replace("x", playerManager.getPlayer2().getName());
        headerText = headerText.replace("y", playerManager.getPlayer1().getName());
        header.setText(headerText);
    }

    @Override
    public void onDrink() {
        if(cardManager.isPlayer1Turn()) {
            playerManager.getPlayer2().takeSip();
        } else {
            playerManager.getPlayer1().takeSip();
        }
        setTopText();
    }

    @Override
    public void onTruth() {
        Toast.makeText(this, "Truth is the path to peace!", Toast.LENGTH_SHORT).show();
        setTopText();
    }
}