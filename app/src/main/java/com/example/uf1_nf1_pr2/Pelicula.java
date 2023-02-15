package com.example.uf1_nf1_pr2;

public class Pelicula {
    private String mNom,mGenero, mVotacion, muid;

    public Pelicula(String nom, String genero, String votacion, String uid){
        mNom = nom;
        mGenero = genero;
        mVotacion = votacion;
        muid = uid;
    }

    public String toString() {
        return mNom + " - " + mGenero + " - " + mVotacion;
    }

    public void setmNom(String nom){mNom = nom;}
    public void setmGenero(String genero){mGenero= genero;}
    public void setmVotacion(String uid){ muid = uid;}

    public String getNom() {return mNom;}
    public String getGenero() {return mGenero;}
    public String getVotacion() {return mVotacion;}
    public String getUid() {return muid;}

}
