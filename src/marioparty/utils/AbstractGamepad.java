package marioparty.utils;

import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;

public abstract class AbstractGamepad {
    protected Controller controller;

    public AbstractGamepad() {
    }

    /**
     * Gets if a button is pressed
     * 
     * @param button the button identifier
     * @return button is down
     */
    public abstract boolean getButtonPressed(Identifier button);

    /**
     * Get controller output
     * 
     */
    public abstract void poll();

    public boolean getAButton() {
        return getButtonPressed(Identifier.Button._0);
    }

    public boolean getBButton() {
        return getButtonPressed(Identifier.Button._1);
    }

    public boolean getXButton() {
        return getButtonPressed(Identifier.Button._2);
    }

    public boolean getYButton() {
        return getButtonPressed(Identifier.Button._3);
    }

    /**
     * Returns if the top left back paddle is pressed
     * 
     * @return button is down
     */
    public boolean getLeftPaddleButton() {
        return getButtonPressed(Identifier.Button._4);
    }

    /**
     * Returns if the top right back paddle is pressed
     * 
     * @return button is down
     */
    public boolean getRightPaddleButton() {
        return getButtonPressed(Identifier.Button._5);
    }

    /**
     * Returns if the back/select button is pressed
     * 
     * @return button is down
     */
    public boolean getBackButton() {
        return getButtonPressed(Identifier.Button._6);
    }

    /**
     * Returns if the start button is pressed
     * 
     * @return button is down
     */
    public boolean getStartButton() {
        return getButtonPressed(Identifier.Button._7);
    }

    /**
     * Returns if L3/left stick is pressed
     * 
     * @return button is down
     */
    public boolean getLeftPushStickButton() {
        return getButtonPressed(Identifier.Button._8);
    }

    /**
     * Returns if R3/right stick is pressed
     * 
     * @return button is down
     */
    public boolean getRightPushStickButton() {
        return getButtonPressed(Identifier.Button._9);
    }

    public double getLeftStickX() {
        return controller.getComponent(Identifier.Axis.X).getPollData();
    }

    public double getLeftStickY() {
        return controller.getComponent(Identifier.Axis.Y).getPollData();
    }

    public double getRightStickX() {
        return controller.getComponent(Identifier.Axis.RX).getPollData();
    }

    public double getRightStickY() {
        return controller.getComponent(Identifier.Axis.RY).getPollData();
    }

    /**
     * Returns how far the triggers are pressed down
     * 
     * It doesn't fully reach 1, so use something smaller for a threshold
     * 
     * If its positive, right is pressed down,
     * If its negative, left is down
     * 
     * @return ~ -1.0 to ~1.0
     */
    public double getTriggers() {
        return controller.getComponent(Identifier.Axis.Z).getPollData();
    }

    /**
     * Returns the DPad/POV
     * 
     * @return 0 - 1
     */
    public double getPOV() {
        return controller.getComponent(Identifier.Axis.POV).getPollData();
    }
}
