package br.com.desafio.githubsearch.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.desafio.githubsearch.R;
import br.com.desafio.githubsearch.requests.RequestUser;

public class UserActivity extends AppCompatActivity {

    private ImageView imageUser;
    private TextView textViewLogin, textViewRepos, textViewLocation, textViewEmail,
            textViewPublicRepos, textViewPublicGits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initElements();

        Intent intent = getIntent();
        String usuer = intent.getStringExtra("LOGIN");

        RequestUser requestUser = new RequestUser(this);
        requestUser.execute(usuer);
    }

    private void initElements(){
        imageUser           = (ImageView) findViewById(R.id.imageUser);
        textViewLogin       = (TextView) findViewById(R.id.textViewLogin);
        textViewRepos       = (TextView) findViewById(R.id.textViewRepos);
        textViewLocation    = (TextView) findViewById(R.id.textViewLocation);
        textViewEmail       = (TextView) findViewById(R.id.textViewEmail);
        textViewPublicRepos = (TextView) findViewById(R.id.textViewPublicRepos);
        textViewPublicGits  = (TextView) findViewById(R.id.textViewPublicGits);
    }

    public ImageView getImageUser(){
        return imageUser;
    }

    public TextView getTextViewLogin(){
        return textViewLogin;
    }

    public TextView getTextViewRepos(){
        return textViewRepos;
    }

    public TextView getTextViewLocation(){
        return textViewLocation;
    }

    public TextView getTextViewEmail(){
        return textViewEmail;
    }

    public TextView getTextViewPublicRepos(){
        return textViewPublicRepos;
    }

    public TextView getTextViewPublicGits(){
        return textViewPublicGits;
    }
}
