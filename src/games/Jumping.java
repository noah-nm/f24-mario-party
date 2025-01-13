package games;

import utils.Gamepad;
import DLibX.DConsole; //1200 , 800
import net.java.games.input.Controller;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

public class Jumping extends Game {
    public Jumping(DConsole dc, Gamepad[] playerControllers, Controller[] controllers) { // inheritance stuff
        super(dc, playerControllers, controllers);
    }

    public class PlayerInfo {
        private DConsole d;
        private Gamepad player;

        public PlayerInfo(DConsole dc, Gamepad playerControllers, int playerY, int pSize, int playerLife) { // player
            // Information

            this.d = dc;
            this.player = playerControllers;

        }

        private int pSize = 75;
        private int playerY = 450;

        public void draw() { // draw players
            dc.setPaint(Color.RED); // player 1
            dc.fillRect(175, playerY, pSize, pSize);
            dc.setPaint(Color.BLUE); // player 2
            dc.fillRect(350, playerY, pSize, pSize);
            dc.setPaint(Color.GREEN); // player 3
            dc.fillRect(525, playerY, pSize, pSize);
            dc.setPaint(Color.YELLOW); // player 4
            dc.fillRect(700, playerY, pSize, pSize);
        }

        public int getPlayerLife() {
            return 1;
        }

        public void move() {
            for (int i = 0; i < playerControllers.length; i++) {
                if (playerControllers[i].getAButton()) {
                    playerY = playerY + 50;
                }
            }
            
        }
    }

    public class Log {

        private int LogX = 1200;
        private int LogY = 450;
        private int logXChange = -5;
        private int lSize = 50;

        public Log(DConsole dc, int LogX, int LogY, int lSize) {
        }

        public void move() {
            LogX = this.logXChange - 7;
        }

        public void draw() {
            dc.setPaint(new Color(150, 75, 0));
            dc.fillRect(LogX, 50, lSize, lSize);
        }

        public int getX() {
            return LogX;
        }

        public int getY() {
            return LogY;
        }
    }

    ArrayList<Log> logs = new ArrayList<>(); // Arraylist of logs
    ArrayList<PlayerInfo> players = new ArrayList<>(); // Arraylist of players

    @Override
    public void play() { // game itself

        for (int i = 0; i < 1000; i++) { // print logs
            Log log = new Log(dc, 1200, 50, 50);
            logs.add(log);
        }

        for (int i = 0; i < playerControllers.length; i++) {
            PlayerInfo player = new PlayerInfo(dc, playerControllers[i], 50, 20, 1);
            players.add(player);
        }

        dc.clear();

        for (int i = 0; i < playerControllers.length; i++) {
            players.get(i).move();
        }

        dc.setPaint(new Color(135, 206, 235));
        dc.fillRect(250, 250, 1000000, 1000000);

        dc.setPaint(new Color(127, 130, 116));
        dc.fillRect(400, 750, 400, 800);

        for (int i = 0; i < 1000; i++) {
            logs.get(i).draw();
        }

        for (int i = 0; i < 4; i++) {
            players.get(i).draw();
        }

        dc.redraw();
        dc.pause(20);

        if ((players.get(0).getPlayerLife() == 0)) {

        }

    }

}
