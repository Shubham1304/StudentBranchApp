package com.example.ieeesb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ieeesb.AccountActivity.LoginActivity;
import com.example.ieeesb.AccountActivity.ResetPasswordActivity;
import com.example.ieeesb.AccountActivity.SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Settings extends AppCompatActivity {

    private TextView changepwd,deleteacc;

    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        changepwd = findViewById(R.id.changepwd);
        deleteacc = findViewById(R.id.deleteacc);
        auth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authListener;
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(Settings.this, LoginActivity.class));
                    finish();
                }
            }
        };

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        changepwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "Reset pwd request", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Settings.this, ChangePwd.class));
            }
        });
        deleteacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Settings.this, "delete account request", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Settings.this);

                // set title
                alertDialogBuilder.setTitle("Confirm Deleting account");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to delete your account?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                //signOut();


                                progressBar.setVisibility(View.VISIBLE);
                                if (!(user == null)) {
                                    user.delete()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(Settings.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(Settings.this, SignupActivity.class));
                                                        finish();
                                                        progressBar.setVisibility(View.GONE);
                                                    } else {
                                                        Toast.makeText(Settings.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                                                        progressBar.setVisibility(View.GONE);
                                                    }
                                                }
                                            });
                                    //MainActivity.this.finish();
                                } else {
                                    return;
                                }
                            }
                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


            }
        });

    }
}
