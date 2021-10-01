package com.example.pierrepapierciseaux;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Utilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    /*UI*/
    private EditText editTextEmail, editTextPassword;
    private Button login, register;

    /*Métier*/
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference databaseReference;
    private String userID;

    /**
     * Récupération des éléments de l'UI
     */
    private void getUIElements() {
        editTextEmail = findViewById(R.id.loginMail);
        editTextPassword = findViewById(R.id.loginPassword);

        login = findViewById(R.id.loginButton);
        register = findViewById(R.id.registerButtonLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        getUIElements();

        register.setOnClickListener(e -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        login.setOnClickListener(e -> {
            singIn();
        });
    }

    /**
     * Méthode de connexion
     */
    private void singIn() {
        String emailTexte = editTextEmail.getText().toString().trim();
        String passwordTexte = editTextPassword.getText().toString().trim();

        if (emailTexte.isEmpty() || passwordTexte.isEmpty()) {
            showToast(R.string.champ_non_renseigné);
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(emailTexte).matches()) {
                showToast(R.string.email_pas_valide);
            } else {
                singInFirebase(emailTexte, passwordTexte);
            }
        }

    }

    /**
     * Méthode de connexion Firebase
     *
     * @param emailTexte    le mail
     * @param passwordTexte le mot de passe
     */
    private void singInFirebase(String emailTexte, String passwordTexte) {
        ProgressDialog dialog = ProgressDialog.show(this, "",
                getString(R.string.please_wait), true);
        mAuth.signInWithEmailAndPassword(emailTexte, passwordTexte).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    dialog.dismiss();
                    singInAndRedirect();
                } else {
                    showToast(R.string.login_user_fail);
                    dialog.dismiss();
                }
            }
        });
    }

    private void singInAndRedirect() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userID = firebaseUser.getUid();

        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Utilisateur myUser = snapshot.getValue(Utilisateur.class);

                if (myUser != null) {
                    Intent newIntent = new Intent(LoginActivity.this, ActivityMainMenu.class);
                    newIntent.putExtra("user", myUser);
                    startActivity(newIntent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast(R.string.login_user_fail);
            }
        });
    }

    /**
     * Affichage d'un message Toast
     *
     * @param p l'id du la chaîne de caractères
     */
    private void showToast(int p) {
        Toast toast = Toast.makeText(LoginActivity.this, getString(p), Toast.LENGTH_LONG);
        toast.show();
    }
}