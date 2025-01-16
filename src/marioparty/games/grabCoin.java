package marioparty.games;

import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;
import DLibX.DConsole; //1200 , 800
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;

public class grabCoin extends Game  {
    private DConsole d;
    private AbstractGamepad[] players;
    
    
        public grabCoin(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) { // inheritance stuff
            super(dc, playerControllers, scores);
            this.players = playerControllers;
    }

    @Override
    public void play() {

        
    }
}
