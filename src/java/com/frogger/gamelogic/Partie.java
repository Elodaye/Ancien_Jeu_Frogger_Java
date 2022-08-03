package com.frogger.gamelogic;

import com.frogger.components.Plateau;
import com.frogger.components.Grenouille;

public class Partie {
    public Plateau plateau;   // une partie contient un plateau
    public Grenouille froggy;

    public Partie() {   // on entre le nombre de voies que l'on souhaite avoir à parcourir
        plateau = new Plateau();
        froggy = new Grenouille();
    }
}
