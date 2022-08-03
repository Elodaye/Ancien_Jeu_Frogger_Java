package com.frogger.components;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class GrenouilleTest {
    @Test
    @DisplayName("Test vérification de la position de la grenouille au bord gauche du plateau")
    void bord_g(){
        Grenouille g = new Grenouille();
        g.deplacement(48,0);
        assertEquals(g.bord_g(),true);
    }
    @Test
    @DisplayName("Test vérification de la position de la grenouille au bord droit du plateau")
    void bord_d(){
        Grenouille g = new Grenouille();
        g.deplacement(1264,0);
        assertEquals(g.bord_d(),true);
    }

    @Test
    @DisplayName("Test du déplacement d'une grenouille")
    void deplacement(){
        Grenouille g = new Grenouille();
        float a=g.Getx();
        g.deplacement(1,0);
        assertEquals(g.Getx(), a+1);
    }



}
