package marioparty.games;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.App;
import marioparty.utils.Gamepad;
import net.java.games.input.Controller;

public class GameSelect extends Game {

    public class GameEntry {
        private Game game;
        private String name;
        private boolean selected = false;

        public GameEntry(Game game, String name) {
            this.game = game;
            this.name = name;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public boolean isSelected() {
            return selected;
        }

        public String getName() {
            return name;
        }
    }

    private ArrayList<GameEntry> entries = new ArrayList<>();
    private int selected = 0;
    private boolean hasFlicked = false;

    public GameSelect(DConsole dc, Gamepad[] playerGamepads, Controller[] controllers) {
        super(dc, playerGamepads, controllers);
    }

    @Override
    public void play() {


        for(Gamepad player : playerControllers) {
            player.poll();
        }

        this.dc.fillRect(this.dc.getWidth() / 2, this.dc.getHeight() / 2, 100, 100);

        for(GameEntry entry : entries) {
            entry.setSelected(false);
        }

        addEntry(this, "Game select");
        addEntry(this, "Game select");
        addEntry(this, "Game select");
        addEntry(this, "Game select");
        addEntry(this, "Game select");

        drawEntries();
    }

    public void addEntry(Game game, String name) {
        this.entries.add(new GameEntry(game, name));
    }

    public void drawEntries() {
        
        double leftY = this.playerControllers[0].getLeftStickY();

        if(this.playerControllers[0].getAButton()) {
            App.switchGame(this.entries.get(selected).game);
            System.out.println("game switched");
        }

        if(leftY < -0.5) {
            if(!hasFlicked) {
                selected--;
            }
            hasFlicked = true;
        }

        if(leftY > 0.5) {
            if(!hasFlicked) {
                selected++;
            }
            hasFlicked = true;
        }

        if(leftY < 0.5 && leftY > -0.5) {
            hasFlicked = false;
        }

        if(selected > entries.size() - 1) {
            selected = entries.size() - 1;
        }

        if(selected < 0) {
            selected = 0;
        }

        this.entries.get(selected).setSelected(true);

        int offset = 0;
        for(GameEntry entry : entries) {
            Font arialTitle = new Font("arial", 0, 20);

            if(entry.isSelected()) {
                arialTitle = new Font("arial", 0, 30);
            }
    
            this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
            this.dc.setPaint(Color.BLACK);
            this.dc.setFont(arialTitle);
            this.dc.drawString(entry.getName(), 100, 100 + offset);
            this.dc.setOrigin(DConsole.ORIGIN_CENTER);
            offset += 25;

            if(entry.isSelected()) {
                offset += 10;
            }
        }

        this.entries.clear();
    }
}
