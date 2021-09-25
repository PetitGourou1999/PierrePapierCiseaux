package com.example.pierrepapierciseaux;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pierrepapierciseaux.data.Utilisateur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    /*Firebase*/
    private FirebaseAuth mAuth;

    /*UI*/
    private ImageButton selectDate;
    private TextView date;

    private EditText nom, prenom;
    private EditText mail;
    private EditText password, confirmPassword;

    private RadioGroup sexeRadioGroup;
    private RadioButton hommeRadio;
    private RadioButton femmeRadio;

    private Button registerUser;
    private Button retour;

    /*Métier*/
    private int year;
    private int month;
    private int dayOfMonth;
    private Calendar calendar;

    private String nomTexte;
    private String prenomTexte;
    private String dateNaissTexte;
    private String emailTexte;
    private String passwordTexte;
    private String confirmPasswordTexte;
    private String sexeTexte;

    /**
     * Récupération des éléments UI
     */
    private void getUIElements() {
        selectDate = findViewById(R.id.selectDateButton);
        date = findViewById(R.id.dateTextView);
        nom = findViewById(R.id.registerNom);
        prenom = findViewById(R.id.registerPrenom);
        mail = findViewById(R.id.loginMail);
        password = findViewById(R.id.loginPassword);
        confirmPassword = findViewById(R.id.registerConfirmPassword);

        sexeRadioGroup = findViewById(R.id.radioGroup);
        femmeRadio = findViewById(R.id.radioButtonFemme);
        hommeRadio = findViewById(R.id.radioButtonHomme);
        femmeRadio.setChecked(true);

        registerUser = findViewById(R.id.registerButtonRegister);

        retour = findViewById(R.id.backButtonRegister);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        getUIElements();

        retour.setOnClickListener(e -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        selectDate.setOnClickListener(e -> {

            calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            date.setText(day + "/" + (month + 1) + "/" + year);
                        }
                    }, year, month, dayOfMonth);
            datePickerDialog.show();
        });

        registerUser.setOnClickListener(e -> {
            registerNewUser();
        });
    }

    /**
     * Récupération des valeurs saisies
     */
    private void getFormValues() {
        nomTexte = nom.getText().toString().trim();
        prenomTexte = prenom.getText().toString().trim();
        dateNaissTexte = date.getText().toString().trim();
        emailTexte = mail.getText().toString().trim();
        passwordTexte = password.getText().toString().trim();
        confirmPasswordTexte = confirmPassword.getText().toString().trim();

        sexeTexte = "";

        int checkedRadioId = sexeRadioGroup.getCheckedRadioButtonId();
        switch (checkedRadioId) {
            case R.id.radioButtonHomme:
                sexeTexte = hommeRadio.getText().toString().trim();
                break;
            case R.id.radioButtonFemme:
                sexeTexte = femmeRadio.getText().toString().trim();
                break;
            default:
                sexeTexte = femmeRadio.getText().toString().trim();
                break;
        }
    }

    /**
     * Enregistrement d'un nouvel utilisateur et vérification des champs
     */
    private void registerNewUser() {
        getFormValues();

        //TODO : vérification email en double dans Firebase ?

        if (!Patterns.EMAIL_ADDRESS.matcher(emailTexte).matches()) {
            showToast(R.string.email_pas_valide);
        } else {
            if (nomTexte.isEmpty() || prenomTexte.isEmpty() || !dateNaissTexte.contains("/") ||
                    emailTexte.isEmpty() || passwordTexte.isEmpty() || confirmPasswordTexte.isEmpty() || sexeTexte.isEmpty()) {
                showToast(R.string.champ_non_renseigné);

            } else if (!passwordTexte.equals(confirmPasswordTexte)) {
                showToast(R.string.mismatch_password);
            } else {
                registerUserFirebase();
            }
        }

    }

    /**
     * Enregistrement d'un nouvel utilisateur sur Firebase
     */
    private void registerUserFirebase() {
        ProgressDialog dialog = ProgressDialog.show(this, "",
                getString(R.string.please_wait), true);
        String finalSexeTexte = sexeTexte;
        mAuth.createUserWithEmailAndPassword(emailTexte, passwordTexte).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Utilisateur user = new Utilisateur(nomTexte, prenomTexte, dateNaissTexte, finalSexeTexte, emailTexte, 0);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                showToast(R.string.ajout_user_reussi);
                                dialog.dismiss();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            } else {
                                showToast(R.string.ajout_user_fail);
                                dialog.dismiss();
                            }
                        }
                    });
                } else {
                    showToast(R.string.ajout_user_fail);
                    dialog.dismiss();

                }

            }
        });
    }

    /**
     * Affichage d'un message Toast
     *
     * @param p l'id du la chaîne de caractères
     */
    private void showToast(int p) {
        Toast toast = Toast.makeText(RegisterActivity.this, getString(p), Toast.LENGTH_LONG);
        toast.show();
    }


}