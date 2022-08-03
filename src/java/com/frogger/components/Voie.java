package com.frogger.components;

import java.util.ArrayList;
import java.util.Random;

public class Voie {
    private static int nb_voies = 8;
    private float sens ;
    private int voie_id ;  // qui vaut 0 ,1,2,3
    private int voie_num ;
    private float v;
    private double frequence;
    public ArrayList <Voiture> voitures = new ArrayList <Voiture> ();
    private ArrayList <Box> boxes = new ArrayList<>() ;

    public Voie (int voie_numero, int voie_id) {
        voie_num = voie_numero;
        this.sens = (float) (Math.random() - 0.5);
        if (this.sens >0) { this.v = this.sens* 16 + 4;}
    else {this. v = 16*this.sens - 4;}; // TODO adapter le 20 à la situation

        this.frequence = (float) 0.01 * Math.random()  + 0.04; // Math.random();
        this.voitures = new ArrayList <> ();
        this.boxes = boxes ;
        this.voie_id = voie_id ;
    }

    public static int getNb_voies(){
        return nb_voies;
    }

    public double getSens() {
        return this.sens;
    }

    public double getV() {
        return this.v;
    }

    public double getFrequence() {
        return this.frequence;
    }

    public int getVoie_id() {
        return voie_id;
    }

    public void setVoie_id(int voie_id) {
        this.voie_id = voie_id;
    }

    public ArrayList<Box> getBoxes() {
        return boxes;
    }

    public void nouvelle_voiture(){
        /**
         * Ajoute une voiture dans la voie
         */
        double r = Math.random ();
        if ((r < this.frequence)&& (non_superpose ())){
            voitures.add( new Voiture (this.v , this.voie_num ));
        }
    }

    /**
     * Vérifie si deux voitures ne se chevauchent pas sur la voie
     * @return booleen
     */
    public boolean non_superpose () {
            for (Voiture voiture : this.voitures) {
                if (this.v >0) {
                    if (3 * Plateau.getX_taille_case() + Plateau.getX_0() >= voiture.getG_voiture())
                    {
                    return false;}
                }

                else if (-3 * Plateau.getX_taille_case() + Plateau.getX_taille_case() <= voiture.getD_voiture()) {
                return false; }
            }
            return true;
    }

}
