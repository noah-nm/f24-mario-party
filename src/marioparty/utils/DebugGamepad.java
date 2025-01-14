package marioparty.utils;

import net.java.games.input.Component.Identifier;

/**
 * Represents a gamepad that has no input
 * 
 * Mainly will be used for testing and to prevent IndexOutOfBounds exceptions
 */
public class DebugGamepad extends AbstractGamepad {

    @Override
    public boolean getButtonPressed(Identifier button) {
        return false;
    }

    @Override
    public void poll() {
        return;
    }
    
}
