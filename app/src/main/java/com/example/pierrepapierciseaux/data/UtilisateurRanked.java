package com.example.pierrepapierciseaux.data;

public class UtilisateurRanked {

    private Utilisateur user;
    private int position;

    public UtilisateurRanked(Utilisateur user) {
        this.user = user;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
