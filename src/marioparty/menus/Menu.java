package marioparty.menus;

import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.utils.Gamepad;

public abstract class Menu {

    protected DConsole dc;
    protected ArrayList<Gamepad> gamepads;

    public Menu(DConsole dc, ArrayList<Gamepad> gamepads) {
        this.dc = dc;
        this.gamepads = gamepads;
    }

    /**
     * runs a given menu
     * 
     */
    public abstract void play();
    
}
