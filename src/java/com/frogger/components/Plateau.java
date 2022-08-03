package com.frogger.components;

import java.util.ArrayList;

public class Plateau {
    private  static final float x_taille_case = 86 ;
    private  static final float y_taille_case = 86;
    private  static final float x_0 = 300 - 3*84;
    private  static final int x_plateau = 1500 - 320 + 84;  // arbitraire, à modif pour l'IHM : ici pour qu'on ait 8 cases par voie
    private  static final int y_plateau = 1000 ; // tailles du plateau
    public static final int nb_voie = 8;
    private ArrayList <Voie> voies = new ArrayList <Voie> (nb_voie) ;
    private static float eps = x_plateau/x_taille_case; // à modifier pour l'IHM

    public Plateau (){
        this.voies = new ArrayList <> ();
        for (int i =1; i < nb_voie +1 ; i++) {
            Voie v = new Voie(i, 0);  // En deuxième c'est l'id de la voie entre 0 et 3
            if (!(i == 4)) {  // la voie 4 correspond à l'herve, il n'y a pas d'arbres sur celle ci
                voies.add(v);
            }

        }
    }

    public static float getX_0() {  return x_0;  }
    public static float getX_taille_case() {
        return x_taille_case;
    }
    public static float getY_taille_case() { return y_taille_case;}
    public static int getX_plateau() {
        return x_plateau;
    }
    public static int getY_plateau() {
        return y_plateau;
    }
    public ArrayList<Voie> getVoies() {
        return voies;
    }

    }

