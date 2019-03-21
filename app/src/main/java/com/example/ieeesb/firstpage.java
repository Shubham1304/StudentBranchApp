package com.example.ieeesb;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ieeesb.AccountActivity.LoginActivity;
import com.example.ieeesb.AccountActivity.ResetPasswordActivity;
import com.example.ieeesb.AccountActivity.SignupActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class firstpage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Button eventButton,workshopButton,teamButton,membershipButton;
    private FirebaseAuth auth;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            //setContentView(R.layout.team);
        setContentView(R.layout.activity_main3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        eventButton = findViewById(R.id.eventButton);
        workshopButton = findViewById(R.id.workshopButton);
        teamButton = findViewById(R.id.teamButton);
        membershipButton = findViewById(R.id.membershipButton);
        auth = FirebaseAuth.getInstance();
        /*


        Button test_btn = findViewById(R.id.test_btn);



        */


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        setDataToView(user);

        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(firstpage.this, LoginActivity.class));
                    finish();
                }
            }
        };
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"shubhams3013@gmail.com"});  //developer 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Hey Shubham! " + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback:"));
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {

            navigationView.setCheckedItem(R.id.nav_first_layout);
        }
        //navigationView.setNavigationItemSelectedListener(this);
        /*
        testid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstpage.this, LoginActivity.class));
            }
        });
        */


        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(firstpage.this, "Events", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(firstpage.this, Events.class));
            }
        });
        workshopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(firstpage.this, "Workshop", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(firstpage.this,Workshops.class));
            }
        });
        teamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(firstpage.this, "Team", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(firstpage.this,Team.class));

            }
        });
        membershipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(firstpage.this, "Membership", Toast.LENGTH_SHORT).show();
                url = "https://www.ieee.org/membership-catalog/index.html?N=0";
                Uri uriUrl = Uri.parse(url);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });


        /*


        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(firstpage.this, ResetPasswordActivity.class));
            }
        });
        */
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.firstpage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(firstpage.this,Settings.class);
            startActivity(intent);
            Toast.makeText(this, "Settings pressed", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_first_layout) {
            // Handle the camera action
            startActivity(new Intent(this, firstpage.class));

            /*getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame
                            , new FirstFragment())
                    .commit();*/
        } else if (id == R.id.nav_second_layout) {

            startActivity(new Intent(firstpage.this, AboutPage.class));

            /*getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame
                            , new SecondFragment())
                    .commit();
            */
        } else if (id == R.id.nav_third_layout) {
            //Toast.makeText(this, "Contact pressed", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(firstpage.this, Contact.class));
        }
        else if (id==R.id.nav_fourth_layout) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    this);

            // set title
            alertDialogBuilder.setTitle("Confirm Logout");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Are you sure you want to logout?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close
                            // current activity
                            signOut();
                            //MainActivity.this.finish();
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
        /*
        else if (id == R.id.nav_share) {
            Toast.makeText(this, "Share is pressed", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Send is pressed", Toast.LENGTH_SHORT).show();
        }
        */

        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void signOut() {
        auth.signOut();
        startActivity(new Intent(firstpage.this, LoginActivity.class));
        finish();
        /*
        // this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity



                }
            }
        };*/
    }

    @SuppressLint("SetTextI18n")
    private void setDataToView(FirebaseUser user) {

        //email.setText("User Email: " + user.getEmail());


    }


}
