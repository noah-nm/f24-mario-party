package marioparty.games;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.App;
import marioparty.utils.AbstractGamepad;

public class GameSelect extends Game {

    // Custom class for entries

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

    public GameSelect(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);
    }

    @Override
    public void play() {

        for (AbstractGamepad player : this.playerControllers) {
            player.poll();
        }

        this.dc.fillRect(this.dc.getWidth() / 2, this.dc.getHeight() / 2, 100, 100);

        for (GameEntry entry : entries) {
            entry.setSelected(false);
        }

        // Add game entries below


        this.addEntry(new Mashing(dc, playerControllers, scores), "mashing game");
        this.addEntry(new mashingGame(dc, playerControllers, scores), "Mashing Game");
        this.addEntry(new ReactionGame(dc, playerControllers, scores), "Reaction game");
        this.addEntry(new HotPotato(dc, playerControllers, scores), "Hot potato");
        this.addEntry(new grabCoin(dc, playerControllers, scores), "Get The Coin");
        this.addEntry(new RPSGame(dc, playerControllers, scores), "Rock Paper Scissors");
        this.addEntry(new SpinningGame(dc, playerControllers, scores), "Spinning game");
        this.addEntry(new GuessingGame(dc, playerControllers, scores), "Guessing Game");
        this.addEntry(new RunningGame(dc, playerControllers, scores), "Running Game");
        this.addEntry(this, "Game select");
        this.addEntry(this, "Game select");
        // drawing entries
        // DO NOT ADD ENTRIES BELOW THIS
        this.drawEntries();
        this.dc.pause(15);
    }

    public void addEntry(Game game, String name) {
        this.entries.add(new GameEntry(game, name));
    }

    public void drawEntries() {
        AbstractGamepad p1Gamepad = this.playerControllers[0];

        if (p1Gamepad.getAButton()) {
            App.switchGame(this.entries.get(selected).game);
            System.out.println("game switched");
        }

        // handle controller inputs
        double leftY = p1Gamepad.getLeftStickY();

        // down
        if (leftY < -0.5) {
            if (!this.hasFlicked) {
                this.selected--;
            }
            this.hasFlicked = true;
        }

        // up
        if (leftY > 0.5) {
            if (!this.hasFlicked) {
                this.selected++;
            }
            this.hasFlicked = true;
        }

        // neutral state
        if (leftY < 0.5 && leftY > -0.5) {
            this.hasFlicked = false;
        }

        // handle bottom value
        if (this.selected > this.entries.size() - 1) {
            this.selected = this.entries.size() - 1;
        }

        // handle zero value
        if (this.selected < 0) {
            this.selected = 0;
        }

        // change selection
        this.entries.get(this.selected).setSelected(true);

        // offset game titles
        int offset = 0;
        for (GameEntry entry : this.entries) {
            Font arialTitle = new Font("arial", 0, 20);

            if (entry.isSelected()) {
                arialTitle = new Font("arial", 0, 30);
            }

            this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
            this.dc.setPaint(Color.BLACK);
            this.dc.setFont(arialTitle);
            this.dc.drawString(entry.getName(), 100, 100 + offset);
            this.dc.setOrigin(DConsole.ORIGIN_CENTER);
            offset += 25;

            if (entry.isSelected()) {
                offset += 10;
            }
        }

        this.entries.clear();
    }
}
