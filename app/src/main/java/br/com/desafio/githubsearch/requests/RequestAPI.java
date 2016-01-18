package br.com.desafio.githubsearch.requests;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import br.com.desafio.githubsearch.fragments.PlaceholderFragmentRepositories;
import br.com.desafio.githubsearch.fragments.PlaceholderFragmentUsers;
import br.com.desafio.githubsearch.objects.ParserReponse;

import br.com.desafio.githubsearch.R;

/**
 * Created by rodrigo on 15/01/16.
 */
public class RequestAPI {

    private RequestQueue mRequestQueue;
    private ProgressDialog progressDialog;
    private ListView listView;
    private Fragment fragment;
    private String url;
    private ImageView imageLogo;
    private int identifyRequest;
    private String messageLoading;
    private String[] values;

    public RequestAPI(PlaceholderFragmentUsers placeholderFragmentUsers) {
        this.fragment = placeholderFragmentUsers;
        this.listView = placeholderFragmentUsers.getListView();
        this.imageLogo = placeholderFragmentUsers.getImageLogo();
        this.messageLoading = placeholderFragmentUsers.getMessageLoading();
        this.url = "https://api.github.com/search/users?q=";
        init(1);
    }

    public RequestAPI(PlaceholderFragmentRepositories placeholderFragmentRepositories) {
        this.fragment = placeholderFragmentRepositories;
        this.listView = placeholderFragmentRepositories.getListView();
        this.imageLogo = placeholderFragmentRepositories.getImageLogo();
        this.messageLoading = placeholderFragmentRepositories.getMessageLoading();
        this.url = "https://api.github.com/search/repositories?q=";
        init(2);
    }

    private void init(int identifyRequest){
        this.identifyRequest = identifyRequest;
        imageLogo.setVisibility(View.INVISIBLE);
        progressDialog = ProgressDialog.show(fragment.getActivity(), "Aguarde", messageLoading, false);
    }

    public void execute(String value){

        //
        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET, url+value,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {

                        values = ParserReponse.parserResponse(response, identifyRequest);

                        if (values.length < 1){

                            Toast.makeText(fragment.getActivity(), "Nenhum resultado encontrado", Toast.LENGTH_SHORT).show();
                            resetFragmentUI();

                        }else {

                            ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(fragment.getActivity(), android.R.layout.simple_list_item_1, values);
                            listView.setAdapter(titleAdapter);

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
                            errorMessage = fragment.getActivity().getString(R.string.message_connection_error);
                        }else {
                            errorMessage = fragment.getActivity().getString(R.string.message_error);
                        }

                        Snackbar.make(fragment.getActivity().findViewById(android.R.id.content), errorMessage, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        resetFragmentUI();
                    }
                });

        getRequestQueue().add(stringRequest);

    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null)
            mRequestQueue = Volley.newRequestQueue(fragment.getActivity());

        return mRequestQueue;
    }

    private void resetFragmentUI(){
        imageLogo.setVisibility(View.VISIBLE);
        listView.setAdapter(null);
        progressDialog.dismiss();

        InputMethodManager imm = (InputMethodManager) fragment.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(listView.getWindowToken(), 0);
    }

    public String getUser(int position){
        return values[position];
    }
}
