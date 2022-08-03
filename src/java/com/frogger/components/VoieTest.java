package com.frogger.components;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class VoieTest {

    @Test
    @DisplayName("Ajout d'une nouvelle voiture")
    void nouvelle_voiture(){
        Voie v= new Voie(1,1);
        int a=v.voitures.size();
        v.nouvelle_voiture();
        assertEquals(a,0);
        assertEquals(v.voitures.size(),a+1);

    }

}
