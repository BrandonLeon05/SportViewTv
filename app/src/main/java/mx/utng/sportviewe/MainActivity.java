package mx.utng.sportviewe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button submit;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase Auth
        //mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
        submit = findViewById(R.id.submitButton);

        TextView signUpPage = findViewById(R.id.signUpPage);

        signUpPage.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUp.class);
            startActivity(intent);
            finish();
        });

        mAuth = FirebaseAuth.getInstance();

        submit.setOnClickListener(v -> {

            String em = email.getText().toString();
            String pass = password.getText().toString();

            if (TextUtils.isEmpty(em)){
                Toast.makeText(this,"Enter login email",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Enter login password",Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();

                            Intent logS = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(logS);
                            finish();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(MainActivity.this,error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser !=null){
            Intent logS = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(logS);
            finish();
        }
    }

    /*
    //El siguiente metodo sirve para checar si el usuario actual está registrado
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        Log.i("User:",""+currentUser);
    }*/

}