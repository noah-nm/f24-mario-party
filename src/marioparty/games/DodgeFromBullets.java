package marioparty.games;
import marioparty.utils.Gamepad;
import marioparty.utils.AbstractGamepad;
import DLibX.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class DodgeFromBullets extends Game {

    private DConsole dc;
    private Player[] players;  // Array to hold multiple players
    private ArrayList<Bullet> bullets;
    private Random random;
    private int score;

    // Constructor
    public DodgeFromBullets(DConsole dc, AbstractGamepad[] playerControllers, int[] scores) {
        super(dc, playerControllers, scores);  // No controllers used
        this.dc = dc;  // Assigning the DConsole object
        this.players = new Player[4]; // Initialize the players array with 4 players
        this.bullets = new ArrayList<>();
        this.random = new Random();
        this.score = 0;

        // Create 4 players and position them horizontally, assign each player a color
        players[0] = new Player(100, 100, 50, 100, Color.RED);    // Red
        players[1] = new Player(100, 250, 50, 100, Color.BLUE);   // Blue
        players[2] = new Player(100, 400, 50, 100, Color.GREEN);  // Green
        players[3] = new Player(100, 550, 50, 100, Color.ORANGE); // Orange
    }

    // The main game loop
    public void play() {
        while (true) { 
            
            for (AbstractGamepad playerController : playerControllers) {
                playerController.poll();
            }
            
            // Keep playing the game until it's over
            // Clear the screen
            dc.clear();

            // Handle player movement with keyboard
            if (this.playerControllers[0].getXButton()) {  // X button to go(left)
                players[0].moveLeft();
            }
            if (this.playerControllers[0].getBButton()) {  // B button to go (right)
                players[0].moveRight();
            }
            if (this.playerControllers[0].getYButton()) {  // Y button to (jump)
                players[0].jump();
            }
            if (this.playerControllers[0].getAButton()) {  // A button to (crouch)
                players[0].crouch(true);
            } else {
                players[0].crouch(false);
            }

            // Controls for Player 2 (WASD controls for example)
            if (this.playerControllers[1].getXButton()) {  // X button to go(left)
                players[1].moveLeft();
            }
            if (this.playerControllers[1].getBButton()) {  // B button to go (right)
                players[1].moveRight();
            }
            if (this.playerControllers[1].getYButton()) {  // Y button to (jump)
                players[1].jump();
            }
            if (this.playerControllers[1].getAButton()) {  // A button to (crouch)
                players[1].crouch(true);
            } else {
                players[1].crouch(false);
            }

            // Controls for Player 3 (IJKL controls)
            if (this.playerControllers[2].getXButton()) {  // X button to go(left)
                players[2].moveLeft();
            }
            if (this.playerControllers[2].getBButton()) {  // B button to go (right)
                players[2].moveRight();
            }
            if (this.playerControllers[2].getYButton()) {  // Y button to (jump)
                players[2].jump();
            }
            if (this.playerControllers[2].getAButton()) {  // A button to (crouch)
                players[2].crouch(true);
            } else {
                players[2].crouch(false);
            }

            // Controls for Player 4 (Arrow keys for example)
            if (this.playerControllers[3].getXButton()) {  // X button to go(left)
                players[3].moveLeft();
            }
            if (this.playerControllers[3].getBButton()) {  // B button to go (right)
                players[3].moveRight();
            }
            if (this.playerControllers[3].getYButton()) {  // Y button to (jump)
                players[3].jump();
            }
            if (this.playerControllers[3].getAButton()) {  // A button to (crouch)
                players[3].crouch(true);
            } else {
                players[3].crouch(false);
            }

            // Update the players' positions
            for (Player player : players) {
                player.update();
            }

            // Add new bullets randomly at the top (falling down instead of moving left)
            if (random.nextInt(100) < 5) { // Adjust frequency of bullet spawn
                bullets.add(new Bullet(random.nextInt(dc.getWidth()), 0, 50, 10));
            }

            // Update and draw bullets (falling down)
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                bullet.update();
                bullet.draw();

                // Check for collision with any player
                for (Player player : players) {
                    if (player.collidesWith(bullet)) {
                        dc.clear();  // Clear the screen before displaying game over
                        dc.drawString("Game Over!", dc.getWidth() / 2 - 50, dc.getHeight() / 2);
                        
                        dc.drawString("Score: " + score, dc.getWidth() / 2 - 50, dc.getHeight() / 2 + 30);
                        dc.pause(2000);  // Pause for 2 seconds to display the game over screen
                        return; // End the game
                    }
                }
            }

            // Draw all players
            for (Player player : players) {
                player.draw();
            }

            // Increment score over time as long as the players survive
            dc.setPaint(Color.BLACK);
            score++;
            dc.drawString("Score: " + score, 50, 50);

            // Update the screen
            dc.redraw();  // Ensure the screen is refreshed

            // Pause for a brief moment to control game speed
            dc.pause(10);  // Add a slight delay between each frame
        }
    }

    // ----------------------------Player class----------------------------
    class Player {
        int x, y, width, height;
        int velocityY;
        final int GRAVITY = 1;
        final int JUMP_STRENGTH = -15;
        final int normalHeight = 100;  // Default height of the player
        final int crouch = 50;  // Height when the player crouches
        Color color; // Color for each player

        public Player(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.velocityY = 0;
            this.color = color;  // Set the player's color
        }

        public void moveLeft() {
            if (x > 0) {
                x -= 5;
            }
        }

        public void moveRight() {
            if (x < dc.getWidth() - width) {
                x += 5;
            }
        }

        public void jump() {
            if (y == dc.getHeight() - height) {
                velocityY = JUMP_STRENGTH;
            }
        }

        public void crouch(boolean isCrouching) {
            if (isCrouching) {
                height = crouch;  // Set height to crouched height
            } else {
                height = normalHeight;  // Restore the original height
            }
        }

        public void update() {
            // Apply gravity
            velocityY += GRAVITY;

            // Update position
            y += velocityY;

            // Prevent player from falling off the screen
            if (y > dc.getHeight() - height) {
                y = dc.getHeight() - height;
                velocityY = 0;
            }
        }

        public void draw() {
            dc.setPaint(color);;  // Set the player's color
            dc.fillRect(x, y, width, height);
        }

        public boolean collidesWith(Bullet bullet) {
            return (x < bullet.x + bullet.width && x + width > bullet.x &&
                    y < bullet.y + bullet.height && y + height > bullet.y);
        }
    }

    // Bullet class
    class Bullet {
        int x, y, width, height;
        int speed = 15;  // Speed at which bullets fall down

        public Bullet(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void update() {
            // Move the bullet downward (falling effect)
            y += speed;

            // Remove bullet if it's off-screen
            if (y > dc.getHeight()) {
                bullets.remove(this);
            }
        }

        public void draw() { 
            dc.fillRect(x, y, width, height);
        }
    }
}
