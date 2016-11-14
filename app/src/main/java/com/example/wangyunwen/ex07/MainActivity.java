package com.example.wangyunwen.ex07;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText password = (EditText) findViewById(R.id.password);
        final EditText confirm = (EditText) findViewById(R.id.comfirm);
        Button ok = (Button) findViewById(R.id.ok);
        Button clear = (Button) findViewById(R.id.clear);

        sharedPreferences = (MainActivity.this).getSharedPreferences("password", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String p = sharedPreferences.getString("password", null);

        if(p != null) {
            password.setHint("password");
            confirm.setVisibility(View.INVISIBLE);
        }

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ps = password.getText().toString();
                String con = confirm.getText().toString();

                if(ps == null) {
                    Toast.makeText(MainActivity.this, "Password cannot be empty.",Toast.LENGTH_SHORT).show();
                }

                if(confirm.getVisibility() == View.INVISIBLE) {
                    String pass = sharedPreferences.getString("password", null);
                    if(ps.equals(pass)) {
                        Intent intent = new Intent(MainActivity.this, FileActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid password.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (ps.equals(con)) {
                        editor.putString("password", ps);
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, FileActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Password mismatch.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password.setText("");
                confirm.setText("");
            }
        });
    }
}
