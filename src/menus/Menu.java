package menus;

import DLibX.DConsole;
import utils.Gamepad;
import java.util.ArrayList;

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
