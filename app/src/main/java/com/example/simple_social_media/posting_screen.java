package com.example.simple_social_media;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class posting_screen extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String Message = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posting_screen);
    }

    public void post(View view){
        CollectionReference Posts = db.collection("posts");
        EditText editText = (EditText) findViewById(R.id.Message);
        String PostMessage = editText.getText().toString();
        Map<String,Object> Posts1 = new HashMap<>();
        String ts=Long.toString(System.currentTimeMillis());

        String name = "";
        for(int i =0; i<13;i++) {
            name+=(9- (Character.getNumericValue(ts.charAt(i))));
        }

        Posts1.put(name,PostMessage);
        Posts.document(name).set(Posts1);
        Intent intent = new Intent(this,reading_screen.class);
        startActivity(intent);
    }
}