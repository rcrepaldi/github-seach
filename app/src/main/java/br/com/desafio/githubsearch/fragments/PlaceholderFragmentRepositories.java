package br.com.desafio.githubsearch.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import br.com.desafio.githubsearch.R;
import br.com.desafio.githubsearch.requests.RequestAPI;

/**
 * Created by rodrigo on 15/01/16.
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragmentRepositories extends Fragment {

    private EditText inputSearch;
    private ListView listView;
    public ImageView imageLogo;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragmentRepositories newInstance(int sectionNumber) {
        PlaceholderFragmentRepositories fragment = new PlaceholderFragmentRepositories();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragmentRepositories() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_repositories, container, false);

        initElements(rootView);
        eventsComponents();

        return rootView;
    }

    /**
     * Instantiate components of layout
     * @param rootView
     */
    private void initElements(View rootView){
        inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);
        listView = (ListView) rootView.findViewById(R.id.listViewRepositories);
        imageLogo = (ImageView) rootView.findViewById(R.id.imageLogo);
    }

    /**
     * Call events of components
     */
    private void eventsComponents(){

        // Calls by clicking the keyboard search button
        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                // Verifity editText vaues is equals null
                if(inputSearch.getText().toString().equals("")){
                    Toast.makeText(getActivity(), getString(R.string.message_edittext_null), Toast.LENGTH_SHORT).show();
                    return true;
                }

                // Performs the call of the RequestAPI
                RequestAPI ru = new RequestAPI(PlaceholderFragmentRepositories.this);
                ru.execute(inputSearch.getText().toString());

                return false;
            }
        });

        // Calls by clicking the item list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click list position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Return compoent of ImageView
     * @return
     */
    public ImageView getImageLogo(){
        return imageLogo;
    }

    /**
     * Return compoent of ListView
     * @return
     */
    public ListView getListView(){
        return listView;
    }

    /**
     * Return string with message of pop-up loading
     * @return
     */
    public String getMessageLoading(){
        return getString(R.string.message_loading_repositories);
    }
}