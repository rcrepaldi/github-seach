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
public class PlaceholderFragmentUsers extends Fragment {

    private EditText inputSearch;
    private ListView listView;
    private ImageView imageLogo;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragmentUsers newInstance(int sectionNumber) {
        PlaceholderFragmentUsers fragment = new PlaceholderFragmentUsers();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderFragmentUsers() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_users, container, false);

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
        listView = (ListView) rootView.findViewById(R.id.listViewUsers);
        imageLogo = (ImageView) rootView.findViewById(R.id.imageLogo);
    }

    /**
     * Call events of components
     */
    private void eventsComponents(){

        final Fragment frag = this;

        // Chama ao clicar no botão de busca do teclado
        inputSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                RequestAPI ru = new RequestAPI(PlaceholderFragmentUsers.this);
                ru.execute(inputSearch.getText().toString());

                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Click list position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ImageView getImageLogo(){
        return imageLogo;
    }

    public ListView getListView(){
        return listView;
    }
}