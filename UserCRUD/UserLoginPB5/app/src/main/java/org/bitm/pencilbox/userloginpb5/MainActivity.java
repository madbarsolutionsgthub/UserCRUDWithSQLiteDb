package org.bitm.pencilbox.userloginpb5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView, textView1;
    private EditText nameET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text1);
        textView1 = findViewById(R.id.text2);
        nameET = findViewById(R.id.userNameET);
        passwordET = findViewById(R.id.userPasswordET);
        textView.setText("Hello Android");
    }

    public void saveUser(View view) {
        String name = nameET.getText().toString();
        String password = passwordET.getText().toString();

        if(name.isEmpty()){
            nameET.setError("This field must not be empty");
        }else if(password.isEmpty()){
            passwordET.setError("This field must not be empty");
        }else{
            /*textView.setText(name);
            textView1.setText(password);*/
            //Explicit intent
            Intent intent = new Intent(this,HomeActivity.class);
            intent.putExtra("greetings","Welcome");
            startActivity(intent);
        }
    }
}
