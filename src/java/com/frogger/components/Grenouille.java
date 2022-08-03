package com.frogger.components;

import com.frogger.gamelogic.Partie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class Grenouille {
    public Image im;
    private static int taille = 84;
    private float x; //abscisse du centre de la grenouille
    private float y; //ordonnée du centre de la grenouille


    public Grenouille () {   // on instancie la grenouille en entrant ses cooordonnées de départ à gauche et en bas.
        // Celles en haut et à droite sont déduites par translation de la taille d'une case (x_taille_case et y_taille_case))
        //this.b = b + Plateau.getEps();    // on rajoute une petite quantité eps pour ne pas remplir exactement la case
        // On s'assure ainsi de ne pas avoir de problèmes de collisions avec les voies voisines

        this.x= 680;
        this.y=777;
    }


    public float Getx(){
        return this.x;
    }
    public static int GetTaille(){
        return taille;
    }

    public float Gety(){
        return this.y;
    }

    public void Setx(float val){
        this.x=val;
    }

    public void Sety(float val){
        this.y=val;
    }


    public void show_grenouille(ImageView iv){
        iv.setImage(this.im);
        iv.setTranslateX(this.x - taille/2);
        iv.setTranslateY(this.y);
        iv.setFitHeight(taille);
        iv.setFitWidth(taille);
        iv.setSmooth(true);
        iv.setCache(true);
    }


    public boolean bord_d(){
        if (this.x>=Plateau.getX_plateau() - this.taille/2){  // X_plateau l'abscisse du bord droit du plateau
            return true;
        }
        else{
            return false;
        }
    }

    public boolean bord_g(){
        if (this.x<=Plateau.getX_0() + taille/2){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean bord_b(){
        if (this.y>=Voie.getNb_voies()* Plateau.getY_taille_case() - taille/2){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Déplace la grenouille selon dx et dy
     * @param dx
     * @param dy
     */
    public void deplacement (float dx, float dy) {// on déplace la grenouille de dx vers la droite et de dy vers le haut

        // on regarde si on veut se déplacer à droite
        if ((dx > 0)&! (bord_d()) ) {     //si on veut se déplacer à droite et on vérifie que l'on a pas atteint la dernière case de la voie
                this.x += dx;

            }
        else if ((dx < 0) &! (bord_g() )) {  //si on veut se déplacer à gauche  //on vérifie que l'on a pas atteint la première case de la voie
                this.x += dx;
            }
        else if ((dy > 0) &!(bord_b())) { //si on veut se déplacer vers le bas //on vérifie que l'on a pas atteint le bas du plateau
                this.y += dy;
            }
        else if ((dy < 0)) {
            this.y += dy;
        }
    }

    public boolean collision (Voiture voiture){  // on regarde si la voiture entrée en paramètre intercepte la grenouille
        /**
         * Vérifie si la grenouille touche une voiture
         * @param voiture
         * @return booleen
         */
        float h = this.y - (float)taille/2;
        if ( Math.abs(voiture.GetNumero_voie()*Plateau.getY_taille_case() -Plateau.getY_taille_case()/2  + 6-h) <= 40) // on vérifie que la voiture et la grenouille sont sur la même voie
        {
            float g = this.x  - (float) taille/2;
            float d = this.x  + (float)taille/2;
            float b = this.y  + (float)taille/2;
            float g_v = voiture.getG_voiture();
            float d_v = voiture.getD_voiture();

            return (g_v <= g && g <= d_v) || (g_v <= d && d <= d_v) || (g <= g_v && g_v <= d) || (g <= d_v && d_v <= d);

        }
        return false;

    }

}
