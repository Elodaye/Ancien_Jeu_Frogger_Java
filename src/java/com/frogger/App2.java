package com.frogger;

// import static org.junit.Assert.*;
import com.frogger.components.Box;
import com.frogger.components.Plateau;
import com.frogger.components.Voie;
import com.frogger.components.Voiture;
import com.frogger.components.Grenouille;
import com.frogger.gamelogic.Partie;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.Iterator;
import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import javafx.scene.image.Image;
import javafx.util.Duration;


public class App2 extends Application {

    @Override
    /**
     * Affiche l'écran d'acceuil, avec le titre principal
     * @param primaryStage la fenetre principale
     */
    public void start(Stage primaryStage) {
        Group root = new Group() ;
        Button start_btn = new Button() ;
        start_btn.setText("START") ;
        start_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                mainstart() ;
            }
        });

        Text crazyfrog_title = new Text();
        crazyfrog_title.setText("CRAZY FROG");
        crazyfrog_title.setFont(Font.font ("Eras Bold ITC", 70));
        crazyfrog_title.applyCss();
        crazyfrog_title.setX(75) ; crazyfrog_title.setY(200) ; start_btn.setLayoutX(275) ; start_btn.setLayoutY(260) ;

        root.getChildren().add(start_btn) ;
        root.getChildren().add(crazyfrog_title);

        Scene titleMenu = new Scene(root, 600, 500, Color.LIGHTSKYBLUE) ;
        Stage stage = new Stage() ;
        stage.setTitle("Crazy Frog");
        stage.setScene(titleMenu);
        stage.show();
    }

    /**
     * Lance le menu
     */
    public void mainstart() {
        Group root_mainMenu = new Group() ;
        //Group root_game = new Group() ;
        Group root_options = new Group() ;
        Scene mainMenu = new Scene(root_mainMenu, 1500, 700, Color.BLACK) ;

        // On gère ce qu'il va se passer lorsqu'on va appuyer sur le bouton game start

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // //
        int height_ecran = (int)dimension.getHeight();
        int width_ecran  = (int)dimension.getWidth();
        System.out.print (width_ecran);
        System.out.print (" ");
        System.out.print (height_ecran);
        System.out.print ("  ");

        Text menutext = new Text("MAIN MENU") ;
        menutext.setFont(Font.font ("Eras Bold ITC", 70));
        menutext.setFill(Color.WHITE);
        menutext.setLayoutX(510);
        menutext.setLayoutY(200);
        root_mainMenu.getChildren().add(menutext);

        Button gamestart_btn = new Button() ;
        gamestart_btn.setText("GAME START");
        root_mainMenu.getChildren().add(gamestart_btn) ;
        gamestart_btn.setLayoutX(690);
        gamestart_btn.setLayoutY(300);

        gamestart_btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                gamestart() ; // , width_ecran, height_ecran) ;
                System.out.print (" début du jeu ");    // jusqu'ici ça marche
            }
        });

        // On gère ce qu'il va se passer lorsqu'on va appuyer sur le bouton options

        Scene options_scene = new Scene(root_options, Color.GREY); // width_ecran,height_ecran, Color.GREY) ;

        Button options_button = new Button() ;
        options_button.setText("OPTIONS");
        root_mainMenu.getChildren().add(options_button) ;
        options_button.setLayoutX(700);
        options_button.setLayoutY(400);
        options_button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                runOptions(options_scene) ;
            }
        });

        Stage mainStage = new Stage() ;
        mainStage.setScene(mainMenu);
        //mainStage.setFullScreen(true);
        mainStage.show();
    }

    /**
     * Lance le jeu !
     */
    public void gamestart() {
        Group root = new Group() ;
        Stage stage = new Stage() ;
        stage.setFullScreen(true);
        Partie partie = new Partie();  //partie.jeu();


        Scene game_scene = new Scene(root, 1500, 800 ,Color.YELLOW);
        stage.setScene(game_scene);

        try {HBox boxz = new HBox();
;
            FileInputStream inputstream = null;
            ImageView iv2 = new ImageView();
            inputstream = new FileInputStream("C://Users//Utilisateur//Documents//Crazy_Frog.png");
            Image image = new Image(inputstream);

            stage.getScene().setOnKeyPressed(event -> {

                if (KeyCode.UP == event.getCode()) {
                    partie.froggy.deplacement(0, -Plateau.getY_taille_case());

                }
                if (KeyCode.DOWN == event.getCode()) {
                    partie.froggy.deplacement(0,  Plateau.getY_taille_case());

                }

                if (KeyCode.LEFT == event.getCode()) {

                    partie.froggy.deplacement(-Plateau.getX_taille_case(), 0);

                };
                if (KeyCode.RIGHT == event.getCode()) {
                    partie.froggy.deplacement(Plateau.getX_taille_case(), 0);
                }
                else {};

            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        };


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Voie voie : partie.plateau.getVoies()) {
                    for (Voiture voiture : voie.voitures) {
                        voiture.deplacement_voiture();
                    }
                    voie.nouvelle_voiture();
                }
                for (Voie voie : partie.plateau.getVoies()) {

                    for (Voiture voiture : voie.voitures) {
                        if (partie.froggy.collision(voiture)) {
                            partie.froggy.Setx(680);
                            partie.froggy.Sety(777);
                        }
                    }

                    for (Iterator<Voiture> it = voie.voitures.iterator(); it.hasNext(); ) {
                        Voiture voiture = it.next();
                        if (voiture.proche_bord()) {
                                    it.remove();
                        }
                    }

                }

                root.getChildren().clear();

                Pane bottom_layer = new Pane() ;
                Pane top_layer = new Pane() ;
                root.getChildren().add(bottom_layer) ;
                root.getChildren().add(top_layer) ;

                for (Voie voie : partie.plateau.getVoies()) {
                        for (Voiture voiture : voie.voitures) {
                            HBox box = new HBox();
                            ImageView iv2 = new ImageView();
                            voiture.show_voiture(iv2);
                            box.getChildren().add(iv2);
                            root.getChildren().add(box);
                        }
                }
                int nb_c = 16;
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 1, 0) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 2, 1) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 3, 1) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 4, 1) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 5, 1) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 6, 0) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 7, 3) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 8, 1) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 9, 1) ;
                    Box.draw(icase, bottom_layer) ;
                }
                for (int i=1; i<nb_c; i++) {
                    Box icase = new Box(i, 10, 0) ;
                    Box.draw(icase, bottom_layer) ;
                }

                Rectangle rect = new Rectangle(84, 84 ); //, Color.GREEN);
                rect.setTranslateX(partie.froggy.Getx() - Grenouille.GetTaille()/2);
                rect.setTranslateY(partie.froggy.Gety());
                root.getChildren().add(rect);

                checkOver(partie, root) ;
            };
        };

        timer.start();
        stage.show();
    }

    /**
     *  Vérifie ponctuellement si le jeu est dans une configuration de gagne (grenouille en haut du jeu)
     * @param partie
     * @param root
     */
    public void checkOver(Partie partie, Group root) {
        if (partie.froggy.Gety()  <= Plateau.getY_taille_case()) {
            //timer.stop();

            String win = "YOU WIN";

            HBox hBox = new HBox();
            hBox.setTranslateX(450);
            hBox.setTranslateY(350);

            root.getChildren().add(hBox);

            Text wintext = new Text("YOU WIN") ;
            wintext.setFont(Font.font ("Eras Bold ITC", 120));
            hBox.getChildren().add(wintext) ;

            Button retry = new Button() ;
            retry.setText("TRY AGAIN");
            root.getChildren().add(retry) ;
            retry.setLayoutX(1400);
            retry.setLayoutY(800);
            retry.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                    gamestart();
                }
            });


//            for (int i = 0; i < win.toCharArray().length; i++) {
//                char letter = win.charAt(i);
//
//                Text text = new Text(String.valueOf(letter));
//                text.setFont(Font.font(48));
//                text.setOpacity(0);
//
//                hBox.getChildren().add(text);
//                hBox.getChildren().add("zozozzozozozzoz");
//
//                FadeTransition ft = new FadeTransition(javafx.util.Duration.seconds(0.66), text);
//                ft.setToValue(1);
//                ft.setDelay(Duration.seconds(i * 0.15));
//                ft.play();
//            }
        };
    }


    /**
     * Affiche l'écran d'options (non implémenté)
     */
    public void runOptions( Scene scene) {
        Stage mainStage = new Stage() ;
        mainStage.setScene(scene);
        mainStage.setFullScreen(true);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch(args) ;
    }
}