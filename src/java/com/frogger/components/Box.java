package com.frogger.components;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Box {
    // Je voulais faire public class Box implement Ibox {} mais ca a pas l'air de marcher
    // soit je met Box en abstract et il veut plus l'instancier, soit il veut pas implementer Ibox

    private int xbox ;
    private int ybox ;
    private int box_id ;

    public Box (int xbox, int ybox, int box_id) {
        this.xbox = xbox ;
        this.ybox = ybox ;
        this.box_id = box_id ;
    }

    public int getXbox() {
        return xbox;
    }

    public int getYbox() {
        return ybox;
    }

    public int getBox_id() {
        return box_id;
    }

    public void setXbox(int xbox) {
        this.xbox = xbox;
    }

    public void setYbox(int ybox) {
        this.ybox = ybox;
    }

    public void setBox_id(int box_id) {
        this.box_id = box_id;
    }

    /**
     * Affiche la box sur le layer indiqué
     *
     * @param box la box a afficher
     * @param layer couche de type Pane sur laquelle afficher la box
     *
     */
    public static void draw(Box box, Pane layer) {


        int a = 85 ;
        int tx = -50 ;
        int ty = 863 ;

        if (box.getBox_id() == 0) {
            Rectangle rect = new Rectangle(a,a, Color.LIGHTGREEN) ;
            rect.setTranslateY(ty-(a+1)*box.getYbox());
            rect.setTranslateX(tx+(a+1)*box.getXbox());
            layer.getChildren().add(rect) ;
    }

        if (box.getBox_id() == 1) {
            Rectangle rect = new Rectangle(a,a, Color.GREY) ;
            rect.setTranslateY(ty-(a+1)*box.getYbox());
            rect.setTranslateX(tx+(a+1)*box.getXbox());

            layer.getChildren().add(rect) ;
        }

        if (box.getBox_id() == 3) {
            Rectangle rect = new Rectangle(a,a, Color.ROSYBROWN) ;
            rect.setTranslateY(ty-(a+1)*box.getYbox());
            rect.setTranslateX(tx+(a+1)*box.getXbox());
            layer.getChildren().add(rect) ;
        }

    }
}
