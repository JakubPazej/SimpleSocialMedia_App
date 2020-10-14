package com.example.simple_social_media;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class reading_screen extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static String Message = "";
    public static ArrayList<String> values = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        update();
        setContentView(R.layout.reading_screen);
    }

    public void onClickPost(View view){
        Intent intent = new Intent(this,posting_screen.class);
        startActivity(intent);
    }

    public void update() {
        db = FirebaseFirestore.getInstance();
        values.clear();
        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        System.out.println("1");
                        if(task.isSuccessful()) {
                            for (QueryDocumentSnapshot value : task.getResult()) {
                                System.out.println("2");
                                Message = value.getData().toString();
                                cleanUp();
                                values.add(Message);
                            }
                            System.out.println("3");

                            TextView textView = findViewById(R.id.textView2);
                            textView.setText(values.get(0));

                            TextView textView1 = findViewById(R.id.textView3);
                            textView1.setText(values.get(1));

                            TextView textView2 = findViewById(R.id.textView4);
                            textView2.setText(values.get(2));

                            TextView textView3 = findViewById(R.id.textView5);
                            textView3.setText(values.get(3));

                            TextView textView4 = findViewById(R.id.textView6);
                            textView4.setText(values.get(4));
                        }
                        else{
                            Log.w("tag", "Error getting posts.",task.getException()) ;
                        }
                    }
                });



    }

    public void cleanUp(){
        String redo = "";
        char b = '=';
        char c = '}';
        boolean print = false;
        for(int i=0;i<Message.length();i++) {
            if(Message.charAt(i)==c ) {
                print = false;
            }

            if (print == true) {
                redo+=(Message.charAt(i));
            }

            if(Message.charAt(i)==b) {
                print = true;
            }
        }
        Message = redo;
    }
}