package com.example.pierrepapierciseaux;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pierrepapierciseaux.data.Utilisateur;
import com.example.pierrepapierciseaux.data.UtilisateurAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityScoreboard extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private UtilisateurAdapter mUserAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList<Utilisateur> userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_scoreboard);

        userlist = new ArrayList<Utilisateur>();
        loadUsers();

        Log.d("LIST AFTER", userlist.toString());




    }

    private void loadUsers() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    Utilisateur myUser = dsp.getValue(Utilisateur.class);
                    if (myUser != null){
                        userlist.add(myUser);
                    }
                }

                RecyclerView recyclerView = findViewById(R.id.recyclerScoreboard);

                mLayoutManager = new LinearLayoutManager(ActivityScoreboard.this);
                mUserAdapter = new UtilisateurAdapter(userlist);

                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(mUserAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast(R.string.ajout_user_fail);
            }
        });
    }


    /**
     * Affichage d'un message Toast
     *
     * @param p l'id du la chaîne de caractères
     */
    private void showToast(int p) {
        Toast toast = Toast.makeText(ActivityScoreboard.this, getString(p), Toast.LENGTH_LONG);
        toast.show();
    }
}