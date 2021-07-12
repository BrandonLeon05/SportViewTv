package mx.utng.sportviewe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    private EditText email,pass,conPass;
    private ProgressBar progressBar;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        Button submit = findViewById(R.id.signUpButton);

        progressBar = findViewById(R.id.signUpProgressBar);

        email = findViewById(R.id.signUpEmail);
        pass = findViewById(R.id.signUpPassword);
        conPass = findViewById(R.id.signUpConfirmPassword);

        submit.setOnClickListener(v -> {

            String Email = email.getText().toString();
            String Pass = pass.getText().toString();
            String ConPass = conPass.getText().toString();

            if (TextUtils.isEmpty(Email)){
                Toast.makeText(this,"Ingresa una dirección de correo electronico",Toast.LENGTH_SHORT).show();
            }else if(TextUtils.isEmpty(Pass)){
                Toast.makeText(this,"Ingresa una contraseña",Toast.LENGTH_SHORT).show();
            }else if(!Pass.equals(ConPass)){
                Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
            }else{
                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(SignUp.this,"Registro Correcto",Toast.LENGTH_SHORT).show();
                        }else{
                            progressBar.setVisibility(View.GONE);
                            String error = task.getException().getMessage();
                            Toast.makeText(SignUp.this, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}