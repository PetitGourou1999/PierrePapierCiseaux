package com.example.pierrepapierciseaux.helpers;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pierrepapierciseaux.R;
import com.example.pierrepapierciseaux.data.Element;
import com.example.pierrepapierciseaux.data.ElementManager;
import com.example.pierrepapierciseaux.data.EnumGameTypes;
import com.example.pierrepapierciseaux.data.EnumResults;
import com.example.pierrepapierciseaux.data.ResultWrapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class GameHelper {

    private Context appContext;
    private EnumGameTypes gameType;

    public Integer humanScore;
    public Integer botScore;

    public Element chosenElementBot;

    public ElementManager elementManager;

    public GameHelper(Context appContext, EnumGameTypes gameType) {
        this.appContext = appContext;
        this.gameType = gameType;
        humanScore = 0;
        botScore = 0;
        elementManager = new ElementManager(this.appContext);
    }

    public ResultWrapper makeBotChoice(Element chosenElementHuman) {
        chosenElementBot = elementManager.getRandomElementClassic();
        EnumResults result = chosenElementHuman.checkWeakness(chosenElementBot);

        switch (result) {
            case TIE:
                return new ResultWrapper((appContext.getString(R.string.resultRound)
                        + " " + appContext.getString(R.string.resultTie)),
                        appContext.getColor(R.color.turquoise));
            case DEFEAT:
                botScore++;
                return new ResultWrapper((appContext.getString(R.string.resultRound)
                        + " " + appContext.getString(R.string.resultDefeat)),
                        appContext.getColor(R.color.red));
            case VICTORY:
                humanScore++;
                return new ResultWrapper((appContext.getString(R.string.resultRound)
                        + " " + appContext.getString(R.string.resultVictory)),
                        appContext.getColor(R.color.green));
        }

        return new ResultWrapper((appContext.getString(R.string.resultRound)
                + " " + appContext.getString(R.string.resultTie)),
                appContext.getColor(R.color.turquoise));
    }

    public int updatePlayerScore(boolean hasWon) {
        SharedPreferences prefs = appContext.getSharedPreferences("preferences-key-name", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        int userScore = prefs.getInt("userScore", 0);
        editor.remove("userScore");

        editor.commit();

        switch (gameType) {
            case CLASSIC:
                if (hasWon)
                    userScore += 3;
                else
                    userScore -= 1;
                break;
            case VARIANT4:
                if (hasWon)
                    userScore += 5;
                else
                    userScore -= 2;
                break;
            case VARIANT7:
                if (hasWon)
                    userScore += 8;
                else
                    userScore -= 3;
                break;
        }

        if (userScore < 0)
            userScore = 0;

        editor.putInt("userScore", userScore);
        editor.commit();

        updateFirebase(userScore);

        return userScore;
    }

    private void updateFirebase(int userScore) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        if (firebaseUser != null) {
            String userID = firebaseUser.getUid();
            int finalUserScore = userScore;
            databaseReference.child(userID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("score", finalUserScore);
                    if (snapshot.exists()) {
                        databaseReference.child(userID).updateChildren(map);
                    } else {
                        databaseReference.child(userID).setValue(map);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(appContext, appContext.getString(R.string.ajout_user_fail),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
