package utils;

import net.java.games.input.Component.Identifier;

public abstract class AbstractGamepad {

    public abstract boolean getButtonPressed(Identifier button);

    public abstract void poll();

    public abstract boolean getAButton();

    public abstract boolean getBButton();

    public abstract boolean getXButton();

    public abstract boolean getYButton();

    public abstract boolean getLeftPaddleButton();

    public abstract boolean getRightPaddleButton();

    public abstract boolean getBackButton();

    public abstract boolean getStartButton();

    public abstract boolean getLeftPushStickButton();

    public abstract boolean getRightPushStickButton();

    public abstract double getLeftStickX();

    public abstract double getLeftStickY();

    public abstract double getRightStickX();

    public abstract double getRightStickY();

    public abstract double getTriggers();

    public abstract double getPOV();
}
