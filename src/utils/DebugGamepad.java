package utils;

import net.java.games.input.Component.Identifier;

public class DebugGamepad extends AbstractGamepad {

    @Override
    public boolean getButtonPressed(Identifier button) {
        return false;
    }

    @Override
    public void poll() {
        return;
    }

    @Override
    public boolean getAButton() {
        return false;
    }

    @Override
    public boolean getBButton() {
        return false;
    }

    @Override
    public boolean getXButton() {
        return false;
    }

    @Override
    public boolean getYButton() {
        return false;
    }

    @Override
    public boolean getLeftPaddleButton() {
        return false;
    }

    @Override
    public boolean getRightPaddleButton() {
        return false;
    }

    @Override
    public boolean getBackButton() {
        return false;
    }

    @Override
    public boolean getStartButton() {
        return false;
    }

    @Override
    public boolean getLeftPushStickButton() {
        return false;
    }

    @Override
    public boolean getRightPushStickButton() {
        return false;
    }

    @Override
    public double getLeftStickX() {
        return 0.0;
    }

    @Override
    public double getLeftStickY() {
        return 0.0;
    }

    @Override
    public double getRightStickX() {
        return 0.0;
    }

    @Override
    public double getRightStickY() {
        return 0.0;
    }

    @Override
    public double getTriggers() {
        return 0.0;
    }

    @Override
    public double getPOV() {
        return 0.0;
    }
    
}
