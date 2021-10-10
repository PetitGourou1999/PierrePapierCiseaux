package com.example.pierrepapierciseaux.data;

/**
 * Classe wrapper pour un utilisateur et son rang au classement
 */
public class UtilisateurRanked {

    private Utilisateur user;
    private int position;

    /**
     * Constructeur
     *
     * @param user l'utilisateur récupéré depuis la base de données realtime Firebase
     */
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
