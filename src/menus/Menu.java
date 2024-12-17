package menus;

import DLibX.*;
import utils.Gamepad;
import java.util.ArrayList;
import net.java.games.input.Controller;

public abstract class Menu {

    protected DConsole dc;
    protected ArrayList<Gamepad> gamepads;
    protected Controller[] controllers;

    public Menu(DConsole dc, ArrayList<Gamepad> gamepads, Controller[] controllers) {
        this.dc = dc;
        this.gamepads = gamepads;
        this.controllers = controllers;
    }

    /**
     * runs a given menu
     * 
     * @return null
     */
    public abstract void play();
    
}
