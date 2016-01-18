package br.com.desafio.githubsearch.objects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rodrigo on 15/01/16.
 */
public class ParserReponse {

    /**
     * Parser users
     * @param response
     * @return
     */
    public static String[] parserResponseUsers(JSONObject response) {

        try {

            String totalCount = response.getString("total_count").toString();

            JSONArray items = response.getJSONArray("items");

            String[] users = new String[items.length()];
            for (int i = 0; i < items.length(); i++) {
                JSONObject object = (JSONObject) items.get(i);
                users[i] = object.getString("login");
            }
            return users;

        } catch (JSONException e) {

        }
        return null;
    }

    /**
     * Parser repositories
     * @param response
     * @return
     */
    public static String[] parserResponseRepositories(JSONObject response) {

        try {

            String totalCount = response.getString("total_count").toString();

            JSONArray items = response.getJSONArray("items");

            String[] repositories = new String[items.length()];
            for (int i = 0; i < items.length(); i++) {
                JSONObject object = (JSONObject) items.get(i);
                repositories[i] = object.getString("full_name");
            }
            return repositories;

        } catch (JSONException e) {

        }
        return null;
    }
}
