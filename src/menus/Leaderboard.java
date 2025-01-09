package menus;
import utils.Gamepad;
import DLibX.*;
import games.Game;
import java.awt.*;  
import net.java.games.input.Controller; 
 
public class Leaderboard extends Game {
    
    public Leaderboard(DConsole dc, Gamepad[] gamepads, Controller[] controllers, int[] scores) {
        super(dc, gamepads, controllers, scores);
    }

    public void play(){

        int time = 250;
        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        int index4 = 0;
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;
        int s4 = 0;
        int[] workingScores = this.scores; 
        Color[] co = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        Font arialMedium = new Font("arial", 1, 40);  

        //find winner
        for(int i = 0; i<4; i++){
            if(workingScores[i]>index1){
                index1 = workingScores[i];
                s1 = i;
            }
        }
        index1 = s1;
        workingScores[index1] = 0;

        for(int i = 0; i<4; i++){
            if(workingScores[i]>index2){
                index2 = workingScores[i];
                s2 = i;
            }
        }
        index2 = s2;
        workingScores[index2] = 0;

        for(int i = 0; i<4; i++){
            if(workingScores[i]>index3){
                index3 = workingScores[i];
                s3 = i;
            }
        }
        index3 = s3;
        workingScores[index3] = 0;

        for(int i = 0; i<4; i++){
            if(workingScores[i]>index4){
                index4 = workingScores[i];
                s4 = i;
            }
        }
        index4 = s4;
        workingScores[index4] = 0;

        //for 5 sec
        while (time > 0) {
        
            //Draw GUI
            dc.setFont(arialMedium);
            dc.setPaint(co[index1]);
            dc.fillRect(100, 100, 1000, 80);
            dc.setPaint(Color.BLACK);
            dc.drawString("Player" + (index1+1), 150, 110);
            dc.drawString(scores[index1], 950, 110);

            dc.setPaint(co[index2]);
            dc.fillRect(100, 220, 1000, 80);
            dc.setPaint(Color.BLACK);
            dc.drawString("Player" + (index2+1), 150, 230);
            dc.drawString(scores[index2], 950, 230);

            dc.setPaint(co[index3]);
            dc.fillRect(100, 340, 1000, 80);
            dc.setPaint(Color.BLACK);
            dc.drawString("Player" + (index3+1), 150, 350);
            dc.drawString(scores[index3], 950, 350);

            dc.setPaint(co[index4]);
            dc.fillRect(100, 460, 1000, 80);
            dc.setPaint(Color.BLACK);
            dc.drawString("Player" + (index4+1), 150, 470);
            dc.drawString(scores[index4], 950, 470);

            dc.pause(20);
            dc.redraw();
            time--;
        }
        

    }

}