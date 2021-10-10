package com.example.pierrepapierciseaux.helpers;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.pierrepapierciseaux.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe regroupant les requêtes Firebase
 */
public class FirebaseHelper {

    /**
     * Mise à jour du score
     *
     * @param appContext le contexte de l'application
     * @param userScore  la nouvelle valeur du score
     */
    public static void updateFirebase(Context appContext, int userScore) {
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
