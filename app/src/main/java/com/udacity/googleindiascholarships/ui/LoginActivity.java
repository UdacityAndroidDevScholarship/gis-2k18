package com.udacity.googleindiascholarships.ui;

import android.content.DialogInterface;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.udacity.googleindiascholarships.Common.Common;
import com.udacity.googleindiascholarships.Model.User;
import com.udacity.googleindiascholarships.R;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Button btnSignIn,btnRegister;
    private Button signInBtn;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private String TAG = "LoginActivty";
    private FirebaseAuth mAuth;
    private SignInButton googleSignInBtn;
    private ProgressBar progressBar;
    RelativeLayout rootLayout;


    FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseDatabase db;
    DatabaseReference users;

    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        rootLayout=(RelativeLayout) findViewById(R.id.root_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBar = (ProgressBar) findViewById(R.id.sign_in_progress);
        signInBtn = (Button) findViewById(R.id.sign_in_btn);
        // Init Progress Dialog
        mProgressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dialog);
        //  Setting Some Option of the mProgressDialog
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Authenticating...");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        //initialize firebase authentication for simple sign in and other things

        db=FirebaseDatabase.getInstance();
        users=db.getReference("Users");
        btnRegister=(Button) findViewById(R.id.btnRegister);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);
        mAuthStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() !=null){
                    User user = new User();
                    user.setEmail(mAuth.getCurrentUser().getEmail());
                    Common.currentUser = user;


                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }
        };

        //login
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDialog();
            }
        });


        //registration
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegistrationDialog();//to show the pop up dialog
            }
        });
        //end here


        googleSignInBtn = (SignInButton) findViewById(R.id.google_sign_in_btn);
        googleSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignInBtn.setEnabled(false);
                mProgressDialog.show();
                signIn();
            }
        });
    }

    private void showRegistrationDialog() {
        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);

        dialog.setTitle("REGISTER");
        dialog.setMessage("Please use email to register");

        LayoutInflater inflater= LayoutInflater.from(this);
        View register_layout=inflater.inflate(R.layout.layout_register,null);

        final MaterialEditText edtEmail=register_layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtName=register_layout.findViewById(R.id.edtName);
        final MaterialEditText edtPhone=register_layout.findViewById(R.id.edtPhone);
        final MaterialEditText edtPassword=register_layout.findViewById(R.id.edtPassword);



        dialog.setView(register_layout);

        //this set the buttons below the pop up dialog huhu

        dialog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {



                mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //Save user to database

                                User user=new User();

                                user.setEmail(edtEmail.getText().toString());
                                user.setName(edtName.getText().toString());
                                user.setPhone(edtPhone.getText().toString());
                                user.setPassword(edtPassword.getText().toString());
                                Common.currentUser =user;
                                //USE firebase user ID as primary key for the table
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Snackbar.make(rootLayout,"Registered successfully",Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar.make(rootLayout,"Registration failed"+e.getMessage(),Snackbar.LENGTH_SHORT)
                                                        .show();
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(rootLayout,"Registration failed!!!"+e.getMessage(),Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });




        //this set the cancel button of the dialog
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();

    }

    private void showLoginDialog() {
        final AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("SIGN IN");
        dialog.setMessage("Please use email to sign in");

        LayoutInflater inflater= LayoutInflater.from(this);
        View login_layout=inflater.inflate(R.layout.layout_login,null);

        final MaterialEditText edtEmail=login_layout.findViewById(R.id.edtEmailLogin);

        final MaterialEditText edtPassword=login_layout.findViewById(R.id.edtPasswordLogin);


        dialog.setView(login_layout);

        //this set the buttons below the pop up dialog

        dialog.setPositiveButton("SIGN IN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                // disable the sign in button when the request is processing
                btnSignIn.setEnabled(false);

                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Snackbar.make(rootLayout, "Email address is required", Snackbar.LENGTH_SHORT).show();
                    btnSignIn.setEnabled(true);
                    return;
                }

                if (TextUtils.isEmpty(edtEmail.getText().toString())) {
                    Snackbar.make(rootLayout, "Password Is required", Snackbar.LENGTH_SHORT).show();
                    btnSignIn.setEnabled(true);
                    return;
                }
                if (edtPassword.getText().toString().length() < 6) {
                    Snackbar.make(rootLayout, "Password is tooo too short!", Snackbar.LENGTH_SHORT).show();
                    btnSignIn.setEnabled(true);
                    return;
                }

                final SpotsDialog waitingDialog=new SpotsDialog(LoginActivity.this);
                waitingDialog.show();

                //Login


                mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(),edtPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                                User user=new User();
                                user.setEmail(edtEmail.getText().toString());
                                user.setPassword(edtPassword.getText().toString());
                                Common.currentUser =user;
                                waitingDialog.dismiss(); //stopping the progress bar after the log in is successful

                                startActivity(new Intent(LoginActivity.this,MainActivity.class));

                                finish();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        waitingDialog.dismiss(); //stopping the progress bar after the log in fail
                        Snackbar.make(rootLayout, "Faileddd", Snackbar.LENGTH_SHORT).show();

                        //reactivating the sign in button if the sign in failed
                        btnSignIn.setEnabled(true);

                    }
                });
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }
    //main sign in enddd
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mAuth.addAuthStateListener(mAuthStateListener);
        if(currentUser!=null){
            Toast.makeText(this, "Already signed in", Toast.LENGTH_SHORT).show();
            Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    }


    public void signIn() {
        Toast.makeText(LoginActivity.this, "Tapped", Toast.LENGTH_SHORT).show();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Log.i(TAG, "onActivityResult: "+result);
            Log.i(TAG, "onActivityResult: "+result.getStatus());
            Log.i(TAG, "onActivityResult: "+result.getSignInAccount());
            Log.i(TAG, "onActivityResult: "+result.isSuccess());
            Log.i(TAG, "onActivityResult: "+result.toString());

            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // [START_EXCLUDE]
                Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onActivityResult: "+result.getStatus().getStatusMessage());
                // [END_EXCLUDE]
            }
            hideProgress();
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                            Toast.makeText(LoginActivity.this, "Signed in", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // The Hide Progress Bar Always Called After Request Completion
                        hideProgress();
                    }
                });
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services  error.", Toast.LENGTH_SHORT).show();
    }


    /**
     * This Function Will Visible The Progress Dialog
     */
    private void showProgress(){
        mProgressDialog.show();
    }

    /**
     * This Function Will Hide The Progress Dialog
     */
    private void hideProgress(){
        mProgressDialog.hide();
    }

}


