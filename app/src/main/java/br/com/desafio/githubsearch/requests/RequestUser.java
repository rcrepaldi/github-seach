package br.com.desafio.githubsearch.requests;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.Snackbar;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import br.com.desafio.githubsearch.R;
import br.com.desafio.githubsearch.activitys.UserActivity;
import br.com.desafio.githubsearch.objects.LoadingImage;
import br.com.desafio.githubsearch.objects.User;

/**
 * Created by rodrigo on 15/01/16.
 */
public class RequestUser {

    private UserActivity userActivity;
    private RequestQueue mRequestQueue;
    private ProgressDialog progressDialog;
    private String url;

    public RequestUser(UserActivity userActivity) {
        this.userActivity = userActivity;
        this.url = "https://api.github.com/users/";
        init();
    }

    private void init(){
        progressDialog = ProgressDialog.show(userActivity, "Aguarde", userActivity.getString(R.string.message_loading_user), false);
    }

    public void execute(final String user){

        //
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url+user,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            User user = new User();
                            user.setId(response.getString("id").toString());
                            user.setLogin(response.getString("login").toString());
                            user.setAvatarUrl(response.getString("avatar_url").toString());
                            user.setReposUrl(response.getString("repos_url").toString());
                            user.setLocation(response.getString("location").toString());
                            user.setEmail(response.getString("email").toString());
                            user.setPublicRepos(response.getString("public_repos").toString());
                            user.setPublicGits(response.getString("public_gists").toString());

                            setElementsValues(user);

                            LoadingImage loadingImage = new LoadingImage(user.getAvatarUrl(), userActivity.getImageUser());
                            loadingImage.start();

                        } catch (JSONException e) {
                            Snackbar.make(userActivity.findViewById(android.R.id.content), userActivity.getString(R.string.message_error), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                        }

                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        VolleyLog.d("TAG", "Error: " + error.getMessage());

                        String errorMessage;
                        if(error instanceof NoConnectionError) {
                            errorMessage = userActivity.getString(R.string.message_connection_error);
                        }else {
                            errorMessage = userActivity.getString(R.string.message_error);
                        }

                        Snackbar.make(userActivity.findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        progressDialog.dismiss();
                    }
                });

        getRequestQueue().add(stringRequest);

    }

    private void setElementsValues(User user){
        userActivity.getTextViewLogin().setText(user.getLogin().equals(null) ? "Login não informado" : "Login: " + user.getLogin());
        userActivity.getTextViewRepos().setText(user.getReposUrl().equals("null") ? "Login não informado" : "Respos: " + user.getReposUrl());
        userActivity.getTextViewLocation().setText(user.getLocation().equals("null") ? "Local não informado" : "Local: " + user.getLocation());
        userActivity.getTextViewEmail().setText(user.getEmail().equals("null") ? "Email não informado" : "Email: " + user.getEmail());
        userActivity.getTextViewPublicRepos().setText(user.getPublicRepos().equals("null") ? "Repos não informado" : "Repos: " + user.getPublicRepos());
        userActivity.getTextViewPublicGits().setText(user.getPublicGits().equals("null") ? "Gits não informado" : "Gits: " + user.getPublicGits());
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(userActivity);

        return mRequestQueue;
    }
}
