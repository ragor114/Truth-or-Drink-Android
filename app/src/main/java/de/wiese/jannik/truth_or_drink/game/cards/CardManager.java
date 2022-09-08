package de.wiese.jannik.truth_or_drink.game.cards;

import android.content.Context;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import de.wiese.jannik.truth_or_drink.R;

public class CardManager {

    public static final String FINAL_TEXT = "The End.";

    private int playedCards;
    private ArrayList<Card> cards;

    public CardManager(Context context) {
        playedCards = 0;
        initCards(context);
    }

    private void initCards(Context context) {
        cards = new ArrayList<>();

        String content = context.getString(R.string.questions);
        Log.d("JSON", content);
        try {
            JSONObject jsonQuestions = new JSONObject(content);
            JSONArray jsonArrayOfQuestions = jsonQuestions.getJSONArray("questions");
            Log.d("JSON", jsonArrayOfQuestions.getString(0));

            for (int i = 0; i < jsonArrayOfQuestions.length(); i++) {
                cards.add(new Card(jsonArrayOfQuestions.getString(i)));
            }

            Collections.shuffle(cards);
            Log.d("JSON", cards.get(0).getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlayer1Turn() {
        return playedCards % 2 == 0;
    }

    public String drawCard() {
        playedCards++;

        if(playedCards > cards.size()) return FINAL_TEXT;

        return cards.get(playedCards-1).getText();
    }

    public int getPlayedCards() {
        return playedCards-1;
    }

}
