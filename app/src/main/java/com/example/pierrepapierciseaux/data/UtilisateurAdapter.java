package com.example.pierrepapierciseaux.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pierrepapierciseaux.R;

import java.util.ArrayList;
import java.util.Comparator;

public class UtilisateurAdapter extends RecyclerView.Adapter<UtilisateurAdapter.ViewHolder> {

    private ArrayList<Utilisateur> mPeople;


    public UtilisateurAdapter(ArrayList<Utilisateur> people) {
        mPeople = people;
        mPeople.sort(Comparator.comparingInt(Utilisateur::getScore));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.utilisateur_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rankField.setText((position + 1) + ". ");
        holder.nameField.setText(mPeople.get(position).getPrenom()
                + " " + mPeople.get(position).getNom());
        holder.scoreField.setText(" " + mPeople.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return mPeople.size();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView rankField;
        public TextView nameField;
        public TextView scoreField;

        public ViewHolder(View view) {
            super(view);
            rankField = (TextView) view.findViewById(R.id.textViewRankScoreboard);
            nameField = (TextView) view.findViewById(R.id.textViewUsernameScoreboard);
            scoreField = (TextView) view.findViewById(R.id.textViewScoreScoreboard);
        }

    }

}
