package com.frogger.components;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Voiture {
    private int taille_voiture;
    private int numero_voie;
    private float vitesse_voiture;
    private float g_voiture, d_voiture;
    public Image im;

    public Voiture (float v, int num_voie){
        this.numero_voie = num_voie;
        this.vitesse_voiture = v ;  // TODO à voir la taille
        this.taille_voiture =  (int)(3* Math.random() ) +1 ;
        if (v>0){
            d_voiture =Plateau.getX_0() ;
            g_voiture = d_voiture- taille_voiture* Plateau.getX_taille_case();
        }
        else {
            g_voiture =Plateau.getX_plateau();
            d_voiture = g_voiture+ taille_voiture * Plateau.getX_taille_case();
        }

        FileInputStream inputstream = null;
        try {
            if (this.vitesse_voiture > 0 ) {
                inputstream = new FileInputStream("C://Users//Utilisateur//Documents//flash_droit.jfif");
            }
            else {
                inputstream = new FileInputStream("C://Users//Utilisateur//Documents//flash_gauche.jfif");
            }

        } catch (FileNotFoundException e) { e.printStackTrace(); }
        assert inputstream != null;

        this.im = new Image(inputstream);
    }

    /**
     *  Affiche la représentation graphique de la voiture
     * @param image
     */
    public void show_voiture(ImageView iv){

        iv.setImage(this.im);
        iv.setTranslateX(this.g_voiture);
        iv.setTranslateY(Plateau.getY_taille_case() * this.numero_voie + 6);
        iv.setFitHeight(Plateau.getY_taille_case() - 8);
        iv.setFitWidth(this.taille_voiture * Plateau.getX_taille_case());
        iv.setSmooth(true);
        iv.setCache(true);
    }

    public int GetNumero_voie(){
        return this.numero_voie;
    }
    public int GetTailleVoit (){
        return taille_voiture;
    }

    public float getG_voiture() {
        return g_voiture;
    }

    public float getD_voiture() {
        return d_voiture;
    }

    public double getVitesse_voiture(){
        return vitesse_voiture;
    }

    /**
     * Fait avancer la voiture
     */
    public void deplacement_voiture (){
        g_voiture += vitesse_voiture; // * dt (qu'on prend à 1)  par développement limité
        d_voiture += vitesse_voiture; // * 0.1;  // TODO adapter le 0.1 à la situation
    }

    /**
     * Détecte si la voirute est sortie de la voie
     */
    public boolean proche_bord (){  // détecte si la voiture est sortie de la voie

        if (g_voiture >= Plateau.getX_plateau() && vitesse_voiture > 0)
        {
            return true;
        }

        else if (d_voiture <= Plateau.getX_0() && vitesse_voiture < 0)
        {  // TODO 0 pour abscisses des x, mais suivant l'ihm ça va peut être changé
            return true;
        }

        return false;
    }

}