package de.wiese.jannik.truth_or_drink.ui.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.wiese.jannik.truth_or_drink.R;
import de.wiese.jannik.truth_or_drink.ui.activities.GameActivity;

public class CardFragment extends DialogFragment {

    private SelectionListener listener;
    private String question;
    
    public CardFragment(SelectionListener listener, String question){
        super(R.layout.fragment_card);
        this.listener = listener;
        this.question = question;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View layout = inflater.inflate(R.layout.fragment_card, null, false);

        initUI(layout);

        builder.setView(layout);

        return builder.create();
    }

    private void initUI(View layout) {
        TextView questionView = layout.findViewById(R.id.txt_question);
        questionView.setText(question);

        layout.findViewById(R.id.btn_drink).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDrink();
                dismiss();
            }
        });
        layout.findViewById(R.id.btn_truth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onTruth();
                dismiss();
            }
        });
    }

    public interface SelectionListener {

        void onDrink();
        void onTruth();

    }

}