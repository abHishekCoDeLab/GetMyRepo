package com.android.prince.getmyrepo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ShowDetail extends AppCompatActivity {

    private String imageUrl;

    private String repoUrl;

    private String name;
    private String ownerName;
    private String star;
    private String view;
    private String fork;
    private String lang;

    private TextView nameText;
    private TextView ownerNameText;
    private TextView starText;
    private TextView viewText;
    private TextView forkText;
    private TextView langText;
    private TextView ownerToolBarText;

    private ImageView toolBarImage;

    private Button code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        final Intent intent = getIntent();
        imageUrl = intent.getStringExtra("IMAGE");
        repoUrl = intent.getStringExtra("URL");
        name = intent.getStringExtra("NAME");
        ownerName = intent.getStringExtra("OWNERNAME");
        star = intent.getStringExtra("STAR");
        view = intent.getStringExtra("VIEW");
        fork = intent.getStringExtra("FORK");
        lang = intent.getStringExtra("LANG");

        code = (Button)findViewById(R.id.Code);

        toolBarImage = (ImageView)findViewById(R.id.OwnerPhotoDetail);

        ownerToolBarText = (TextView)findViewById(R.id.OwnernameDetail);
        langText = (TextView)findViewById(R.id.RepoLangDetailView);
        forkText = (TextView)findViewById(R.id.RepoForkDetailView);
        starText = (TextView)findViewById(R.id.RepoStarDetailView);
        viewText = (TextView)findViewById(R.id.RepoViewDetailView);
        ownerNameText = (TextView)findViewById(R.id.RepoOwnerNameDetailView);
        nameText = (TextView)findViewById(R.id.RepoNameDetailView);

        nameText.setText(name);
        ownerNameText.setText(ownerName);
        viewText.setText(view);
        starText.setText(star);
        forkText.setText(fork);
        langText.setText(lang);
        ownerToolBarText.setText(name);

        Glide.with(this).load(imageUrl).into(toolBarImage);

        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ShowDetail.this,WebViewAct.class);
                myIntent.putExtra("URL",repoUrl);
                startActivity(myIntent);
            }
        });
    }
}
