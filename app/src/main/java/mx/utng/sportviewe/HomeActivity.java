package mx.utng.sportviewe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button logOut = findViewById(R.id.signOut);

        logOut.setOnClickListener(v -> {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();

            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}