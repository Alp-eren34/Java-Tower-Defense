package main;

import inputs.KeyBoardListener;
import inputs.MyMouseListeners;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {

    private Random random;
    private Game game;
    private Dimension size;
    private MyMouseListeners myMouseListeners;
    private KeyBoardListener keyboardListener;


    public GameScreen(Game game) {
        this.game = game;

        setPanelSize();
    }

    public void initInputs(){
        myMouseListeners = new MyMouseListeners(game);
        keyboardListener = new KeyBoardListener(game);

        addMouseListener(myMouseListeners);
        addMouseMotionListener(myMouseListeners);
        addKeyListener(keyboardListener);

        requestFocus();
    }

    private void setPanelSize() {
        size = new Dimension(640,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);

        game.getRender().render(g);
    }

}
