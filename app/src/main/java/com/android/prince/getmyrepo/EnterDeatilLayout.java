package com.android.prince.getmyrepo;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class EnterDeatilLayout extends AppCompatActivity {

    ImageView back;
    ImageView forward;

    TextView textShow;
    EditText getText;

    int i = 0;

    String name;
    String interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_deatil_layout);

        back = (ImageView)findViewById(R.id.Back);
        forward = (ImageView)findViewById(R.id.Forward);

        textShow = (TextView)findViewById(R.id.TextShow);
        getText = (EditText) findViewById(R.id.GetInterest);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 0){
                    finish();
                }else {
                    i = 0;
                    textShow.setText("What's your Name  ?");
                }
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i == 0){
                    i++;
                    textShow.setText("What you are Interested about ?");
                    name = getText.getText().toString();
                    getText.setText("");
                }else {
                   interest = getText.getText().toString();
                    Intent intent = new Intent(EnterDeatilLayout.this,MainActivity.class);
                    intent.putExtra("NAME",name);
                    intent.putExtra("INTEREST",interest);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
