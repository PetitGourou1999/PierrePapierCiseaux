package com.example.pierrepapierciseaux.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pierrepapierciseaux.R;

import java.util.ArrayList;

public class UtilisateurAdapter extends RecyclerView.Adapter<UtilisateurAdapter.ViewHolder> {

    private ArrayList<Utilisateur> mPeople;
    private ArrayList<UtilisateurRanked> mPeopleRanked;


    public UtilisateurAdapter(ArrayList<Utilisateur> people) {
        mPeople = people;
        mPeopleRanked = new ArrayList<UtilisateurRanked>();

        checkUserPosition();

    }

    private void checkUserPosition() {
        int previousScore = 0;
        int rank = 1;

        for (int i = 0; i < this.mPeople.size(); i++) {
            UtilisateurRanked userRanked = new UtilisateurRanked(mPeople.get(i));

            if (i == 0) {
                previousScore = mPeople.get(i).getScore();
            } else {
                previousScore = mPeople.get(i - 1).getScore();
            }

            if (previousScore != mPeople.get(i).getScore()) {
                rank++;
            }

            userRanked.setPosition(rank);
            mPeopleRanked.add(userRanked);

        }
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
        holder.rankField.setText((mPeopleRanked.get(position).getPosition()) + ". ");
        holder.nameField.setText(mPeopleRanked.get(position).getUser().getPrenom()
                + " " + mPeopleRanked.get(position).getUser().getNom());
        holder.scoreField.setText(" " + mPeopleRanked.get(position).getUser().getScore());
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
