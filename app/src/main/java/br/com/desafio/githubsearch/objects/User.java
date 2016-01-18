package br.com.desafio.githubsearch.objects;

/**
 * Created by rodrigo on 18/01/16.
 */
public class User {

    private String id;
    private String login;
    private String avatarUrl;
    private String reposUrl;
    private String location;
    private String email;
    private String publicRepos;
    private String publicGits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(String publicRepos) {
        this.publicRepos = publicRepos;
    }

    public String getPublicGits() {
        return publicGits;
    }

    public void setPublicGits(String publicGits) {
        this.publicGits = publicGits;
    }
}
