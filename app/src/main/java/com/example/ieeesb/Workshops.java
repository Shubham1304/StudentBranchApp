package com.example.ieeesb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Workshops extends AppCompatActivity {
    private TextView edit_text_view,edit_text_view_2;
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference noteRef = db.document("Workshops/List");
    private static final String TAG = "firstpage";
    private String workshopstring1 = "";
    private String workshopstring2  = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshops);
        edit_text_view = findViewById(R.id.edit_text_view_ws);
        edit_text_view_2 = findViewById(R.id.edit_text_view_ws_2);

        DocumentReference docRef = db.collection("Workshops").document("List");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());


                        for(int i=0;i<10;i++){
                            String title2 = "upcomingworkshop" + i;
                            String event2 = document.getString(title2);
                            if(event2 != null)
                            {
                                workshopstring2 = workshopstring2 + "\n\n" + event2;
                                Log.d(TAG, "event1 field " + document.getString(title2));

                            }
                        }edit_text_view.setText(workshopstring2 + "\n");
                        for(int i=0;i<10;i++){
                            String title1 = "workshop" + i;
                            String event1 = document.getString(title1);
                            if(event1 != null)
                            {
                                workshopstring1 = workshopstring1 + "\n\n" + event1;
                                Log.d(TAG, "event1 field " + document.getString(title1));
                            }
                        }edit_text_view_2.setText(workshopstring1 + "\n");

                        //edit_text_view.setText(document.getData().toString().trim());


                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });


/*
        noteRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            String title = documentSnapshot.getString(KEY_TITLE);
                            String description = documentSnapshot.getString(KEY_DESCRIPTION);

                            //Map<String, Object> note = documentSnapshot.getData();

                            edit_text_view.setText("Title: " + title + "\n" + "Description: " + description);
                        } else {
                            Toast.makeText(Workshops.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Workshops.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
        */

    }
}
