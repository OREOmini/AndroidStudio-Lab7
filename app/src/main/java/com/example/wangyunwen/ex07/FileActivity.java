package com.example.wangyunwen.ex07;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        final EditText input = (EditText) findViewById(R.id.input);
        Button save = (Button) findViewById(R.id.save);
        Button load = (Button) findViewById(R.id.load);
        Button clear = (Button) findViewById(R.id.clear);

        sharedPreferences = (FileActivity.this).getSharedPreferences("mytext", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input.setText("");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("text", input.getText().toString());
                editor.commit();
                Toast.makeText(FileActivity.this, "Save successfully.",Toast.LENGTH_SHORT).show();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = sharedPreferences.getString("text", "nothing");
                if(text.equals("nothing")) {
                    Toast.makeText(FileActivity.this, "Fail to load file.",Toast.LENGTH_SHORT).show();
                } else {
                    input.setText(text);
                    Toast.makeText(FileActivity.this, "Load successfully.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
