package menus;

import java.util.ArrayList;

import DLibX.DConsole;
import utils.AbstractGamepad;

public abstract class Menu {

    protected DConsole dc;
    protected ArrayList<AbstractGamepad> gamepads;

    public Menu(DConsole dc, ArrayList<AbstractGamepad> gamepads) {
        this.dc = dc;
        this.gamepads = gamepads;
    }

    /**
     * runs a given menu
     * 
     */
    public abstract void play();
    
}
