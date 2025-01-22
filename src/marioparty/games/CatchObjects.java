package marioparty.games;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import DLibX.DConsole;
import marioparty.App;
import marioparty.menus.Leaderboard;
import marioparty.utils.AbstractGamepad;

public class CatchObjects extends Game {

  public CatchObjects(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
    super(dc, playerControllers, scores);
  }

  class Ball {

    // Instance variables
    private double x;
    private double y;
    private int size;
    private int xChange;
    private int yChange;
    private DConsole d;
    private Random r;
    private Color col;

    public Ball(DConsole dc, Random r) {
      this.d = dc;
      this.r = r;

      this.setPosition();

      this.size = this.r.nextInt(25) + 10;
      this.xChange = this.r.nextInt(18) - 5;
      this.yChange = this.r.nextInt(23) + 1;
      this.col = new Color(this.r.nextInt(255), this.r.nextInt(255), this.r.nextInt(255), 255);
    }

    public void draw() {
      this.d.setPaint(this.col);
      this.d.fillEllipse(this.x, this.y, this.size, this.size);
    }

    public void move() {
      this.y += this.yChange;
      this.x += this.xChange;

      if (this.y > this.d.getHeight() + this.size) {
        this.resetPosition();
      }
    }

    public void resetPosition() {
      this.x = this.r.nextInt(this.d.getWidth());
      this.y = -this.size;
    }

    public double getX() {
      return this.x;
    }

    public double getY() {
      return this.y;
    }

    private void setPosition() {
      this.x = this.r.nextInt(this.d.getWidth());
      this.y = this.r.nextInt(this.d.getHeight());
    }
  }

  public void play() {

    dc.setOrigin(DConsole.ORIGIN_BOTTOM_LEFT);

    Random randGen = new Random();
    ArrayList<Ball> balls = new ArrayList<>();

    // Create 1000 balls
    for (int i = 0; i < 25; i++) {
      Ball newBall = new Ball(dc, randGen);
      balls.add(newBall);
    }

    int x2 = 500;
    int x1 = 300;
    int x3 = 700;
    int x4 = 900;
    int collect1 = 0;
    int collect2 = 0;
    int collect3 = 0;
    int collect4 = 0;
    int[] place = new int[4];
    boolean printed0 = false;
    boolean printed1 = false;
    boolean printed2 = false;
    boolean printed3 = false;
    int rank = 0;

    Color colq = new Color(230, 190, 255, 255);
    Color wall = new Color(250, 160, 60, 255);

    while (true) {
      dc.clear();

      for (AbstractGamepad player : this.playerControllers) {
        player.poll();
      }

      // Controls for boxes

      if (playerControllers[0].getXButton()) {
        x1 = x1 - 20;
      }
      if (playerControllers[0].getBButton()) {
        x1 = x1 + 20;
      }
      if (playerControllers[1].getXButton()) {
        x2 = x2 - 20;
      }
      if (playerControllers[1].getBButton()) {
        x2 = x2 + 20;
      }
      if (playerControllers[2].getXButton()) {
        x3 = x3 - 20;
      }
      if (playerControllers[2].getBButton()) {
        x3 = x3 + 20;
      }
      if (playerControllers[3].getXButton()) {
        x4 = x4 - 20;
      }
      if (playerControllers[3].getBButton()) {
        x4 = x4 + 20;
      }

      // Boundary conditions for boxes
      x1 = Math.max(0, Math.min(1200, x1));
      x2 = Math.max(0, Math.min(1200, x2));
      x3 = Math.max(0, Math.min(1200, x3));
      x4 = Math.max(0, Math.min(1200, x4));

      // Draw background
      dc.setPaint(colq);
      dc.fillRect(0, 1000, 10000, 10000);

      // Draw boxes
      dc.setPaint(Color.RED);
      dc.fillRect(x1, 700, 100, 20);

      dc.setPaint(Color.BLUE);
      dc.fillRect(x2, 700, 100, 20);

      dc.setPaint(Color.GREEN);
      dc.fillRect(x3, 700, 100, 20);

      dc.setPaint(Color.YELLOW);
      dc.fillRect(x4, 700, 100, 20);

      // Move and draw balls
      for (Ball ball : balls) {
        ball.move();
        ball.draw();
      }

      // Check for ball collection
      for (Ball ball : balls) {
        double x = ball.getX();
        double y = ball.getY();

        if (x > x1 && x < (x1 + 100) && y > 700 && y < 730) {
          collect1++;
          ball.resetPosition();
        }

        if (x > x2 && x < (x2 + 100) && y > 700 && y < 730) {
          collect2++;
          ball.resetPosition();
        }

        if (x > x3 && x < (x3 + 100) && y > 700 && y < 730) {
          collect3++;
          ball.resetPosition();
        }

        if (x > x4 && x < ( x4 + 100) && y > 700 && y < 730) {
          collect4++;
          ball.resetPosition();
        }

        // winners
        if (collect1 > 20 && printed0 == false) {
          place[0] = 4 - rank;
          rank++;
          printed0 = true;
        }

        if (collect2 > 20 && printed1 == false) {
          place[1] = 4 - rank;
          rank++;
          printed1 = true;
        }

        if (collect3 > 20 && printed2 == false) {
          place[2] = 4 - rank;
          rank++;
          printed2 = true;
        }

        if (collect4 > 20 && printed3 == false) {
          place[3] = 4 - rank;
          rank++;
          printed3 = true;
        }
      }

      // Display scores
      dc.setPaint(wall);
      dc.fillRect(1000, 300, 300, 300);
      dc.setPaint(Color.BLACK);
      dc.drawString("Box 1: " + collect1, 1040, 200);
      dc.drawString("Box 2: " + collect2, 1040, 230);
      dc.drawString("Box 3: " + collect3, 1040, 260);
      dc.drawString("Box 4: " + collect4, 1040, 290);

      if (rank == 4) {
        scores[0] += place[0];
        scores[1] += place[1];
        scores[2] += place[2];
        scores[3] += place[3];
        break;

      }

      dc.redraw();
      DConsole.pause(20);

    }
    App.switchGame(new Leaderboard(dc, playerControllers, scores));
  }

}
