package games;

import DLibX.DConsole;
import utils.AbstractGamepad;
import java.awt.Color;
import java.awt.Font;

public class Guessing extends Game{
    
    public Guessing(DConsole dc, AbstractGamepad[] playerControllers, int[] scores){
        super(dc, playerControllers, scores);
    }

    public void play(){

        dc.clear();
        int randomNumber =  (int)(Math.random()* 100);
        int player1Guess = 50;
        int player2Guess = 50;
        int player3Guess = 50;
        int player4Guess = 50;
        boolean player1GuessLocked = false;
        boolean player2GuessLocked = false;
        boolean player3GuessLocked = false;
        boolean player4GuessLocked = false;
        
        dc.pause(100);

        while(true){

            dc.clear();
            dc.setPaint(Color.BLACK);
            dc.setOrigin(DConsole.ORIGIN_CENTER);
            Font arialBig = new Font("arial", 1, 80);
            Font arialMedium = new Font("arial", 1, 40);
    
            dc.setFont(arialBig);
            dc.drawString("Guess the number!", dc.getWidth() / 2, 40);
    
            dc.setPaint(Color.RED);
            dc.setFont(arialMedium);
            dc.drawString(1, dc.getWidth() / 2, 120);
            dc.setFont(arialBig);
            dc.drawString(player1Guess, dc.getWidth() / 2, 170);
    
            dc.setPaint(Color.BLUE);
            dc.setFont(arialMedium);
            dc.drawString(2, dc.getWidth() / 2, 240);
            dc.setFont(arialBig);
            dc.drawString(player2Guess, dc.getWidth() / 2, 290);
    
            dc.setPaint(Color.GREEN);
            dc.setFont(arialMedium);
            dc.drawString(3, dc.getWidth() / 2, 360);
            dc.setFont(arialBig);
            dc.drawString(player3Guess, dc.getWidth() / 2, 410);
    
            dc.setPaint(new Color(112, 3, 255));
            dc.setFont(arialMedium);
            dc.drawString(4, dc.getWidth() / 2, 480);
            dc.setFont(arialBig);
            dc.drawString(player4Guess, dc.getWidth() / 2, 530);

            if(player1GuessLocked == true){
                dc.drawImage("src//misc//checkmark.png", dc.getWidth() / 2 + 150, 170);
            }
            if(player2GuessLocked == true){
                dc.drawImage("src//misc//checkmark.png", dc.getWidth() / 2 + 150, 290);
            }
            if(player3GuessLocked == true){
                dc.drawImage("src//misc//checkmark.png", dc.getWidth() / 2 + 150, 410);
            }
            if(player4GuessLocked == true){
                dc.drawImage("src//misc//checkmark.png", dc.getWidth() / 2 + 150, 530);
            }

            if(playerControllers[0].getTriggers() > 0.5 && player1Guess < 100 && player1GuessLocked == false){
                player1Guess++;
            }
            else if(playerControllers[0].getTriggers() < -0.5 && player1Guess > 0 && player1GuessLocked == false){
                player1Guess--;
            }
            if(playerControllers[1].getTriggers() > 0.5 && player2Guess < 100 && player2GuessLocked == false){
                player2Guess++;
            }
            else if(playerControllers[1].getTriggers() < -0.5 && player2Guess > 0 && player2GuessLocked == false){
                player2Guess--;
            }
            if(playerControllers[2].getTriggers() > 0.5 && player3Guess < 100 && player3GuessLocked == false){
                player3Guess++;
            }
            else if(playerControllers[2].getTriggers() < -0.5 && player3Guess > 0 && player3GuessLocked == false){
                player3Guess--;
            }
            if(playerControllers[3].getTriggers() > 0.5 && player4Guess < 100 && player4GuessLocked == false){
                player4Guess++;
            }
            else if(playerControllers[3].getTriggers() < -0.5 && player4Guess > 0 && player4GuessLocked == false){
                player4Guess--;
            }
            if(playerControllers[0].getAButton() == true){
                player1GuessLocked = true;
            }
            if(playerControllers[1].getAButton() == true){
                player2GuessLocked = true;
            }
            if(playerControllers[2].getAButton() == true){
                player3GuessLocked = true;
            }
            if(playerControllers[3].getAButton() == true){
                player4GuessLocked = true;
            }

            if(playerControllers[0].getBButton() == true){
                player1GuessLocked = false;
            }
            if(playerControllers[1].getBButton() == true){
                player2GuessLocked = false;
            }
            if(playerControllers[2].getBButton() == true){
                player3GuessLocked = false;
            }
            if(playerControllers[3].getBButton() == true){
                player4GuessLocked = false;
            }

            dc.pause(100);
            dc.redraw();

            if(player1GuessLocked == true && player2GuessLocked == true && player3GuessLocked == true && player4GuessLocked == true){
                break;
            }
        }
    }
}
