package example_projects.laggy_invaders;// Write your compliance statement here:
//
// What are your extra features?
//      - Level selection
//      - Use of items
//      - Scoring display
//      - Using bullets instead of a beam
// How is your new alien different from the one described by the Alien class?
//      Our special alien is larger and stays at top. It also shoots ammo at
//      the spaceship.


import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import uwcse.graphics.*;

/**
 * A SpaceInvader displays a fleet of alien ships and a space ship. The player
 * directs the moves of the spaceship and can shoot at the aliens.
 */

public class SpaceInvader extends GWindowEventAdapter {
    // Possible actions from the keyboard
    /** No action */
    public static final int DO_NOTHING = 0;

    /** Steer the space ship */
    public static final int SET_SPACESHIP_DIRECTION = 1;

    /** To shoot at the aliens */
    public static final int SHOOT = 2;

    public static final int screenCenter = 250;

    /** Item identification */
    public static final int HEAL = 0;
    public static final int SHIELD = 1;
    public static final int POWER_UP = 2;

    // Period of the animation (in ms)
    // (the smaller the value, the faster the animation)
    private int animationPeriod = 25;

    // Current action from the keyboard
    private int action;

    // score that get updated throughout the game
    private int score;

    // high score
    private int highScore;

    private int level;

    // bullet cool-down to lessen lag
    private int bulletTimer;

    // Game window
    private GWindow window;

    // The space ship
    private SpaceShip spaceShip;

    // Direction of motion given by the player
    private int dirFromKeyboard = MovingObject.LEFT;

    // Text that gets updated throughout the game
    private TextShape txt;
    private TextShape health;

    // The list of aliens
    private ArrayList<Alien> aliens = new ArrayList<Alien>();
    private ArrayList<BigBossAlien> bosses = new ArrayList<BigBossAlien>();

    // List of Bullets
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private ArrayList<Bullet> alienAmmo = new ArrayList<Bullet>();

    // For Items
    private ArrayList<Item> items = new ArrayList<Item>();

    // Level class that determines the level
    private Level levelBuilder;

    // default winning and losing messages to display
    private String won = "YOU WON!";
    private String lost = "GAME OVER";

    /**
     * Constructs a space invader game
     */
    public SpaceInvader() {
        this.window = new GWindow("Space invaders", 500, 500);
        this.window.setExitOnClose();
        this.window.addEventHandler(this); // this SpaceInvader handles all of
        // the events fired by the graphics
        // window

        // Display the game rules
        String rulesOfTheGame = "Save the Earth! Destroy all of the aliens ships.\n" + "To move left, press 'a'.\n"
                + "To move right, press 'd'.\n" + "To shoot, press 'w'.\n" + "To quit, press 'Q'.";
        JOptionPane.showMessageDialog(null, rulesOfTheGame, "Space invaders", JOptionPane.INFORMATION_MESSAGE);
        this.initializeGame();
    }

    /**
     * Initializes the game (draw the background, aliens, and space ship)
     */
    private void initializeGame() {
        // Clear the window
        this.window.erase();

        // Background (starry universe)
        Rectangle background = new Rectangle(0, 0, this.window.getWindowWidth(), this.window.getWindowHeight(),
                Color.black, true);
        this.window.add(background);
        // Add 50 stars here and there (as small circles)
        Random rnd = new Random();
        for (int i = 0; i < 50; i++) {
            // Random radius between 1 and 3
            int radius = rnd.nextInt(3) + 1;
            // Random location (within the window)
            // Make sure that the full circle is visible in the window
            int x = rnd.nextInt(this.window.getWindowWidth() - 2 * radius);
            int y = rnd.nextInt(this.window.getWindowHeight() - 2 * radius);
            this.window.add(new Oval(x, y, 2 * radius, 2 * radius, Color.WHITE, true));
        }

        // Instructions printed on the game
        String movementInstructions = "Use 'a' to move left, 'd' to move right and 'w' to attack";
        TextShape instructions = new TextShape(movementInstructions, 100, 25, Color.RED);
        this.window.add(instructions);

        // For levels
        levelMenu();

        String[] lvl = {"infinite mode", "level 1", "level 2", "level 3", "level 4", "level 5"};

        TextShape displayLvl = new TextShape(lvl[level], 10, 10, Color.RED);
        this.window.add(displayLvl);

        // Reset
        this.score = 0;

        String sc = ("Score: " + score);
        txt = new TextShape(sc, 400, 10, Color.RED);

        this.window.add(new TextShape("High Score: " + highScore, 400, 25, Color.RED));

        int x = 5 * SpaceShip.WIDTH;
        int y = 10 * Alien.RADIUS;

        levelBuilder = new Level(this.window, x, y);
        levelBuilder.setLevel(this.level);

        // Create the space ship at the bottom of the window
        x = this.window.getWindowWidth() / 2;
        y = this.window.getWindowHeight() - SpaceShip.HEIGHT / 2;
        this.spaceShip = new SpaceShip(this.window, new Point(x, y));

        sc = ("health: " + this.spaceShip.getHealth());
        health = new TextShape(sc, 400, 470, Color.RED);

        this.window.add(health);

        // start timer events
        this.window.startTimerEvents(this.animationPeriod);

    }

    /**
     * Moves the objects within the graphics window every time the timer fires an
     * event
     */
    public void timerExpired(GWindowEvent we) {
        // Perform the action requested by the user?
        switch (this.action) {
            case SpaceInvader.SET_SPACESHIP_DIRECTION:
                this.spaceShip.setDirection(this.dirFromKeyboard);
                break;
            case SpaceInvader.SHOOT:
                // bullet spawn
                if (bulletTimer % 3 == 0) {
                    Point point = new Point(this.spaceShip.getLocation().x, 455);
                    Bullet bullet = new Bullet(this.window, point, 1);
                    bullets.add(bullet);
                }
                bulletTimer++;
                break;
        }

        this.action = SpaceInvader.DO_NOTHING; // Don't do the same action
        // twice

        // Show the new locations of the objects
        this.updateGame();
    }

    /**
     * Selects the action requested by the pressed key
     */
    public void keyPressed(GWindowEvent e) {
        // Don't perform the actions (such as shoot) directly in this method.
        // Do the actions in timerExpired, so that the alien ArrayList can't be
        // modified at the same time by two methods (keyPressed and timerExpired
        // run in different threads).

        switch (Character.toLowerCase(e.getKey())) // not case sensitive
        {
            case 'a':
                this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
                dirFromKeyboard = MovingObject.LEFT;
                break;

            case 'd':
                this.action = SpaceInvader.SET_SPACESHIP_DIRECTION;
                dirFromKeyboard = MovingObject.RIGHT;
                break;

            case 'w': // shoot at the aliens
                this.action = SpaceInvader.SHOOT;
                break;

            case 'q': // quit the game
                System.exit(0);

            default: // no new action
                this.action = SpaceInvader.DO_NOTHING;
                break;
        }
    }

    /**
     * Updates the game
     */
    private void updateGame() {

        // ROUND ENDED
        if (this.aliens.size() == 0 && levelBuilder.levelIsOver() && this.bosses.size() == 0){
            gameOver(won);
        }

        if (this.spaceShip.getHealth() <= 0){
            gameOver(lost);
        }

        this.window.suspendRepaints(); // to speed up the drawing

        if (levelBuilder.progressLevel()){
            levelBuilder.spawn();

            // updating list of aliens
            aliens = levelBuilder.updateAliens(aliens);
            bosses = levelBuilder.updateBigBossAlien(bosses);

        }

        updateAliens();
        updateBoss();

        updateItems();
        updateSpaceship();

        updateScore();
        updateHealth();

        // Display it all
        this.window.resumeRepaints();
    }

    /**
     * Updates the aliens (checks their lives, moves them and sees if they hit the spaceship)
     */
    private void updateAliens(){

        // Move the aliens
        for (Alien a : aliens) {
            a.move();

            isAlienShot(a);

            if (a.isDead()) {
                a.erase();
                items.add(a.killDrop());
                this.score += a.getScore();
            }

            // ROUND ENDED
            if (a.playerKilled()) {
                gameOver(lost);
            }

            int alienX = a.center.x;
            int shipX = this.spaceShip.center.x;

            boolean hit1 = alienX < shipX + SpaceShip.WIDTH && alienX > shipX - SpaceShip.WIDTH;
            boolean hit2 = a.center.y + 10 > this.spaceShip.center.y - SpaceShip.HEIGHT;

            if (hit1 && hit2){
                this.spaceShip.isHit();
                a.killAlien();
                a.erase();
            }
        }

        removeAliens();
    }

    /**
     * updates the items (moves and sees if they are used)
     */
    private void updateItems(){
        for (int i = 0; i < items.size(); i++){
            if (items.get(i).center.x == -1 || items.get(i).center.y > 520 || items.get(i).getUsedStat()){
                items.get(i).erase();
                items.remove(i);
            }
        }

        for (Item item: items){
            item.move();

            int spaceshipX = this.spaceShip.center.x;
            int spaceshipY = this.spaceShip.center.y;
            int itemX = item.center.x;
            int itemY = item.center.y;

            boolean isHit1 = (spaceshipX - SpaceShip.WIDTH) < itemX && (spaceshipX + SpaceShip.WIDTH) > itemX;
            boolean isHit2 = (spaceshipY - SpaceShip.HEIGHT) < itemY && (spaceshipY + SpaceShip.HEIGHT) > itemY;

            if (isHit1 && isHit2){
                this.spaceShip.useItem(item);
                item.isUsed();
            }
        }
    }

    /**
     * updates the boss alien (similar to updateAliens())
     */
    private void updateBoss(){
        for (BigBossAlien b: bosses){
            b.move();

            isAlienShot(b);

            if (b.isDead()){
                b.erase();
                this.score += b.getScore();
            }

            bossIsShooting(b);
        }

        for (int i = 0; i < this.bosses.size(); i++){
            if (this.bosses.get(i).isDead()) {
                this.bosses.remove(i);
            }
        }
    }

    /**
     * updates the spaceship (movement and shooting)
     */
    private void updateSpaceship(){
        this.spaceShip.move();
        isSpaceshipShot();
    }

    /**
     * updates and displays the score
     */
    private void updateScore(){
        this.window.remove(txt);

        String sc = ("Score: " + score);
        txt = new TextShape(sc, 400, 10, Color.RED);

        this.window.add(txt);
    }

    /**
     * updates and displays health of the spaceship
     */
    private void updateHealth(){
        this.window.remove(health);

        String sc = ("health: " + this.spaceShip.getHealth());
        health = new TextShape(sc, 400, 470, Color.RED);

        this.window.add(health);
    }

    /**
     * checks if it is time for the boss alien to shoot
     *
     * @param b is the boss alien that is currently being checked if it is shooting
     */
    private void bossIsShooting(BigBossAlien b){
        switch (b.getPhase()){
            case 1:
                alienAmmo.addAll(b.phase1());
                break;
            case 2:
                alienAmmo.addAll(b.phase2());
                break;
            case 3:
                alienAmmo.addAll(b.phase3());
                break;
        }
    }

    /**
     * checks if the spaceship is hit by a bullet
     */
    private void isSpaceshipShot(){
        for (Bullet ammo: alienAmmo){
            ammo.move();

            int spaceshipX = this.spaceShip.center.x;
            int spaceshipY = this.spaceShip.center.y;
            int ammoX = ammo.center.x;
            int ammoY = ammo.center.y;

            boolean isHit1 = (spaceshipX - SpaceShip.WIDTH) < ammoX && (spaceshipX + SpaceShip.WIDTH) > ammoX;
            boolean isHit2 = (spaceshipY - SpaceShip.HEIGHT) < ammoY && (spaceshipY + SpaceShip.HEIGHT) > ammoY;

            if (isHit1 && isHit2){
                this.spaceShip.isHit();
                ammo.isUsed();
            }
        }

        for (int i = 0; i < alienAmmo.size(); i++){
            if (alienAmmo.get(i).getUsedStatus()) {
                alienAmmo.get(i).erase();
                alienAmmo.remove(i);
            }
        }
    }

    /**
     * checks if a regular alien is hit by a bullet
     *
     * @param a is the alien being checked if it is shot
     */
    private void isAlienShot(Alien a){

        for (Bullet b : bullets) {
            b.move();

            int alienX = a.getLocation().x - 10;
            int alienY = a.getLocation().y;
            int bulletX = b.center.x;
            int bulletY = b.center.y;

            if (bulletX < alienX + 25 && bulletX > alienX && alienY - 10 >= bulletY) {
                a.isShot(this.spaceShip.getDamage());
                b.isUsed();
            }
        }

        for (int i = 0; i < bullets.size(); i++){
            if (bullets.get(i).getUsedStatus()) {
                bullets.get(i).erase();
                bullets.remove(i);
            }
        }
    }

    /**
     * checks if the boss alien is hit by a bullet
     *
     * @param boss is the boss alien being checked if it is shot
     */
    private void isAlienShot(BigBossAlien boss){

        for (Bullet b : bullets) {
            b.move();

            int alienX = boss.getLocation().x - BigBossAlien.RADIUS;
            int alienY = boss.getLocation().y;
            int bulletX = b.center.x;
            int bulletY = b.center.y;

            if (bulletX < alienX + BigBossAlien.RADIUS * 2 && bulletX > alienX && alienY + BigBossAlien.RADIUS >= bulletY) {
                boss.isShot();
                b.isUsed();
            }
        }

        removeBullets();
    }

    /**
     * removes dead aliens from the screen
     */
    private void removeAliens(){
        // removes the dead aliens from the game
        for (int i = 0; i < this.aliens.size(); i++){
            if (this.aliens.get(i).isDead()) {
                this.aliens.remove(i);
            }
        }
    }

    /**
     * removes used bullets from the screen
     */
    private void removeBullets(){
        for (int i = 0; i < bullets.size(); i++){
            if (bullets.get(i).getUsedStatus()) {
                bullets.get(i).erase();
                bullets.remove(i);
            }
        }
    }

    /**
     * resets the game window and objects lists to prepare for the next round, or exits if the player
     * no longer wants to play.
     *
     * @param msg game over message
     */
    private void gameOver(String msg){
        window.stopTimerEvents();
        if (anotherGame(msg)) {
            bullets.clear();
            alienAmmo.clear();
            aliens.clear();
            bosses.clear();
            items.clear();
            window.dispose();
            this.initializeGame();
        }else
            System.exit(0);
    }

    /**
     * A window that asks the player which level they would like to play
     */
    private void levelMenu(){

        String[] availableLevels = {"1 ", "2 ", "3 ", "4 ", "5 "};
        JList<String> list = new JList<String>(availableLevels);
        JOptionPane.showMessageDialog(null, list, "select level", JOptionPane.INFORMATION_MESSAGE);
        this.level = list.getSelectedIndex() + 1;
    }

    /**
     * Does the player want to play again?
     */
    public boolean anotherGame(String s) {
        String newHighScore = "your score was: " + score;

        if (score > highScore){
            highScore = score;
             newHighScore = "Congratulations! You got a new high score: " + highScore;
        }

        int choice = JOptionPane.showConfirmDialog(null, s + "\nDo you want to play again?\n" +
                newHighScore, "Game over", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        return (choice == JOptionPane.YES_OPTION);
    }

    /**
     * Starts the application
     */
    public static void main(String[] args) {
        new SpaceInvader();
    }
}