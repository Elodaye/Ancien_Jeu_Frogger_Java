package com.frogger.components;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VoitureTest {

    @Test
    @DisplayName("Test pour le déplacement des voitures")
    void deplacement_voiture() {
        Voiture v = new Voiture(5, 1);
        float g = v.getG_voiture();
        float d = v.getD_voiture();
        v.deplacement_voiture();
        assertEquals(v.getG_voiture(), g + v.getVitesse_voiture());
        assertEquals(v.getD_voiture(), d + v.getVitesse_voiture());

    }

}
