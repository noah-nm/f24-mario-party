package marioparty.menus;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import DLibX.DConsole;
import marioparty.utils.AbstractGamepad;

public class PlayerSelect extends Menu {

    private AbstractGamepad[] playerControllers = new AbstractGamepad[4];

    public PlayerSelect(DConsole dc, ArrayList<AbstractGamepad> gamepads) {
        super(dc, gamepads);
    }

    /**
     * Runs the player selection screeen
     * 
     */
    public void play() {
        boolean exitMenu = false;

        while (!exitMenu) {

            drawGui();
            
            for (AbstractGamepad gamepad : this.gamepads) {
                gamepad.poll();
               
                // allow players to select a color (player number)
                if (gamepad.getBButton() && playerControllers[0] == null && playerControllers[0] != gamepad) {
                    playerControllers[0] = gamepad;
                } else if (gamepad.getXButton() && playerControllers[1] == null && playerControllers[1] != gamepad) {
                    playerControllers[1] = gamepad;
                } else if (gamepad.getAButton() && playerControllers[2] == null && playerControllers[2] != gamepad) {
                    playerControllers[2] = gamepad;
                } else if (gamepad.getYButton() && playerControllers[3] == null && playerControllers[3] != gamepad) {
                    playerControllers[3] = gamepad;
                }
                
            }

            // allow a player to cancel their choice
            // god this is ugly
            for (AbstractGamepad playerController : playerControllers) {
                if (playerController != null) {
                    if (playerController.getBackButton()) {
                        for (int i = 0; i < playerControllers.length; i++) {
                            if (playerControllers[i] != null && playerControllers[i].equals(playerController)) {
                                playerControllers[i] = null;
                                break;
                            }
                        }
                    }
                }
            }
            
            // break loop to next screen
            if (
                playerControllers[0] != null &&
                playerControllers[1] != null &&
                playerControllers[2] != null &&
                playerControllers[3] != null &&
                playerControllers[0].getStartButton()
            ) {
                exitMenu = true;
                System.out.println(playerControllers);
            }
        }    
    }

    /**
     * final player assignments
     * 
     * @return list of final player assignments (Gamepad[])
     */
    public AbstractGamepad[] getPlayers() {
        for (int i = 0; i < this.playerControllers.length; i++) {
            System.out.println(this.playerControllers[i]);
        }
        return this.playerControllers;
    }

    // draw static GUI elements
    private void drawGui() {

        // fonts
        Font arialTitle = new Font("arial", 1, 80);
        Font arialSmall = new Font("arial", 1,30);
        Font arialMedium = new Font("arial", 1, 40);
        Font arialVerySmall = new Font("arial", 1, 20);

        this.dc.clear();

        // background
        this.dc.setOrigin(DConsole.ORIGIN_TOP_LEFT);
        this.dc.setPaint(new Color(121, 224, 218, 200));
        this.dc.fillRect(0, 0, this.dc.getWidth(), this.dc.getHeight());

        // title text
        
        this.dc.setPaint(Color.RED);
        this.dc.setOrigin(DConsole.ORIGIN_CENTER);
        this.dc.setFont(arialTitle);
        this.dc.drawString("Player Select", this.dc.getWidth() / 2, (this.dc.getHeight() / 2) - 200);

        // instructions
        this.dc.setFont(arialSmall);
        this.dc.drawString("Press START when ready", this.dc.getWidth() /  2, this.dc.getHeight() / 2 - 100);
        this.dc.setPaint(Color.BLACK);
        this.dc.drawString("Press BACK to unselect", this.dc.getWidth() / 2, this.dc.getHeight() / 2 + 350);

        // draw player blobs
        this.dc.setPaint(Color.RED);
        this.dc.fillEllipse(225, 500, 100, 100);
        this.dc.setPaint(Color.BLUE);
        this.dc.fillEllipse(475, 500, 100, 100);
        this.dc.setPaint(Color.GREEN);
        this.dc.fillEllipse(725, 500, 100, 100);
        this.dc.setPaint(Color.YELLOW);
        this.dc.fillEllipse(975, 500, 100, 100);

        // text on blob
        this.dc.setPaint(Color.BLACK);
        this.dc.setFont(arialSmall);
        this.dc.drawString("Red", 225, 495);
        this.dc.drawString("Blue", 475, 495);
        this.dc.drawString("Green", 725, 495);
        this.dc.drawString("Yellow", 975, 495);

        // Player number text 
        this.dc.setFont(arialMedium);
        this.dc.setPaint(Color.RED);
        this.dc.drawString("Player 1", 225, 600);
        this.dc.setPaint(Color.BLUE);
        this.dc.drawString("Player 2", 475, 600);
        this.dc.setPaint(Color.GREEN);
        this.dc.drawString("Player 3", 725, 600);
        this.dc.setPaint(Color.YELLOW);
        this.dc.drawString("Player 4", 975, 600);
        
        // draw buttons
        this.dc.setPaint(new Color(245, 14, 10, 220)); // light red B button
        this.dc.fillEllipse(225, 700, 50, 50);
        this.dc.setPaint(new Color(24, 72, 204, 220)); // light blue X button
        this.dc.fillEllipse(475, 700, 50, 50);
        this.dc.setPaint(new Color(48, 201, 61, 220)); // light green A button
        this.dc.fillEllipse(725, 700, 50, 50);
        this.dc.setPaint(new Color(227, 185, 16, 220)); // light yellow Y button
        this.dc.fillEllipse(975, 700, 50, 50);

        // button text
        this.dc.setPaint(Color.BLACK);
        this.dc.setFont(arialVerySmall);
        this.dc.drawString("B", 225, 695);
        this.dc.drawString("X", 475, 695);
        this.dc.drawString("A", 725, 695);
        this.dc.drawString("Y", 975, 695);

        Color notTaken = new Color(180, 21, 11);
        Color taken = new Color(39, 219, 39);

        // change color if player 1 taken
        if (playerControllers[0] == null) {
            this.dc.setPaint(notTaken);
        } else {
            this.dc.setPaint(taken);
        }

        // player 1 indicator
        this.dc.fillEllipse(225, 420, 25, 25);

        // change color if player 2 taken
        if (playerControllers[1] == null) {
            this.dc.setPaint(notTaken);
        } else {
            this.dc.setPaint(taken);
        }

        // player 2 indicator
        this.dc.fillEllipse(475, 420, 25, 25);

        // change color is player 3 is taken 
        if (playerControllers[2] == null) {
            this.dc.setPaint(notTaken);
        } else {
            this.dc.setPaint(taken);
        }
        
        // player 3 indicator
        this.dc.fillEllipse(725, 420, 25, 25);

        // change color is player 4 is taken
        if (playerControllers[3] == null) {
            this.dc.setPaint(notTaken);
        } else {
            this.dc.setPaint(taken);
        }

        // player 4 indicator
        this.dc.fillEllipse(974, 420, 25, 25);

        this.dc.redraw();
    }
}
