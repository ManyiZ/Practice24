package example_projects.laggy_invaders;

import uwcse.graphics.GWindow;

import java.awt.*;
import java.util.ArrayList;

public class Level {

    // checks if all waves have spawned
    private boolean isLevelOver;

    // level progression timer
    private int levelProgression = -1;

    // the rate at which everything spawns in
    private final int PROGRESSION_RATE = 230;

    // center coordinates
    private int x;
    private int y;

    // which level to call
    private int level;

    private GWindow window;

    private ArrayList<BigBossAlien> bossAliens = new ArrayList<BigBossAlien>();
    private ArrayList<Alien> aliens = new ArrayList<Alien>();


    /**
     * Creates this MovingObject
     *
     * @param window the GWindow that the level will work on
     * @param x the x coordinate of the center point of where the aliens spawn
     * @param y the x coordinate of the center point of where the aliens spawn
     */
    public Level(GWindow window, int x, int y){
        this.window = window;
        this.x = x;
        this.y = y;
    }

    /**
     * sets the level to be called
     *
     * @param l level number
     */
    public void setLevel(int l){
        this.level = l;
    }

    /**
     * @return if level is over
     */
    public boolean levelIsOver(){
        return isLevelOver;
    }

    /**
     * @return if next wave should be spawned
     */
    public boolean progressLevel(){
        levelProgression++;
        return levelProgression % PROGRESSION_RATE == 0;
    }

    /**
     * spawns each wave of level
     */
    public void spawn(){
        switch (level){
            case 0:
                level0();
                break;
            case 1:
                level1();
                break;
            case 2:
                level2();
                break;
            case 3:
                level3();
                break;
            case 4:
                level4();
                break;
            case 5:
                level5();
                break;
        }
    }


    /**
     * hidden level - infinite mode
     */
    private void level0(){

        createAliens(this.x, this.y, 4);
        createAliens(this.x, this.y + 25, 3);
        createAliens(this.x, this.y + 50, 2);
        createAliens(this.x, this.y + 75, 1);
    }

    /**
     * level 1
     */
    private void level1(){
        switch (levelProgression / PROGRESSION_RATE){
            case 0:
                createAliens(this.x, this.y, 1);
                break;
            case 1:
                createAliens(this.x, this.y, 2);
                break;
            case 2:
                createAliens(this.x, this.y, 3);
                break;
            case 3:
                createAliens(this.x, this.y, 4);
                isLevelOver = true;
                break;
        }
    }

    /**
     * level 2
     */
    private void level2(){
        switch (levelProgression / PROGRESSION_RATE){
            case 0:
                createAliens(this.x, this.y, 2);
                break;
            case 1:
                createAliens(this.x, this.y, 2);
                createAliens(this.x, this.y + 25, 2);
                break;
            case 2:
                createAliens(this.x, this.y, 2);
                createAliens(this.x, this.y + 25, 3);
                break;
            case 4:
                createBossAlien(BigBossAlien.DIFFICULTY[0]);
                isLevelOver = true;
                break;
        }
    }

    /**
     * level 3
     */
    private void level3(){
        switch (levelProgression / PROGRESSION_RATE){
            case 0:
                createAliens(this.x, this.y + 25, 2);
                createAliens(this.x, this.y, 2);
                break;
            case 1:
                createAliens(this.x, this.y, 2);
                createAliens(this.x, this.y + 25, 3);
                break;
            case 2:
                createAliens(this.x, this.y, 3);
                createAliens(this.x, this.y + 25, 3);
                createAliens(this.x, this.y + 50, 2);
                break;
            case 4:
                createAliens(this.x, this.y + 50, 3);
                createBossAlien(BigBossAlien.DIFFICULTY[0]);
                isLevelOver = true;
                break;
        }
    }

    /**
     * level 4
     */
    private void level4(){
        switch (levelProgression / PROGRESSION_RATE){
            case 0:
                createAliens(this.x, this.y, 3);
                createAliens(this.x, this.y + 25, 2);
                break;
            case 1:
                createAliens(this.x, this.y, 3);
                createAliens(this.x, this.y + 25, 3);
                break;
            case 3:
                createAliens(this.x, this.y, 4);
                createAliens(this.x, this.y + 25, 3);
                createAliens(this.x, this.y + 50, 3);
                break;
            case 5:
                createAliens(this.x, this.y, 4);
                createAliens(this.x, this.y + 25, 4);
                createAliens(this.x, this.y + 50, 3);
                break;
            case 7:
                createAliens(this.x, this.y + 50, 4);
                createAliens(this.x, this.y + 75, 2);
                createBossAlien(BigBossAlien.DIFFICULTY[1]);
                isLevelOver = true;
                break;
        }
    }

    /**
     * level 5
     */
    private void level5(){
        switch (levelProgression / PROGRESSION_RATE){
            case 0:
                createAliens(this.x, this.y, 4);
                createAliens(this.x, this.y + 25, 3);
                break;
            case 1:
                createAliens(this.x, this.y, 4);
                createAliens(this.x, this.y + 25, 3);
                createAliens(this.x, this.y + 50, 3);
                break;
            case 3:
                createAliens(this.x, this.y, 4);
                createAliens(this.x, this.y + 25, 3);
                createAliens(this.x, this.y + 50, 3);
                createAliens(this.x, this.y + 70, 3);
                break;
            case 5:
                createAliens(this.x, this.y, 4);
                createAliens(this.x, this.y + 25, 4);
                createAliens(this.x, this.y + 50, 3);
                createAliens(this.x, this.y + 70, 3);
                break;
            case 7:
                createAliens(this.x, this.y + 25, 4);
                createAliens(this.x, this.y + 50, 3);
                createAliens(this.x, this.y + 75, 2);
                createBossAlien(BigBossAlien.DIFFICULTY[2]);
                isLevelOver = true;
                break;
        }
    }

    /**
     * updates the boss aliens list
     *
     * @param currentBigBossAlien current list of boss aliens
     * @return updated list of boss aliens
     */
    public ArrayList<BigBossAlien> updateBigBossAlien(ArrayList<BigBossAlien> currentBigBossAlien) {
        for (BigBossAlien boss : this.bossAliens) {
            currentBigBossAlien.add(boss);
        }
        this.bossAliens.clear();

        return currentBigBossAlien;
    }

    /**
     * updates the aliens list
     *
     * @param existingAliens current list of aliens
     * @return updated list of aliens
     */
    public ArrayList<Alien> updateAliens(ArrayList<Alien> existingAliens){
        for (Alien a : this.aliens){
            existingAliens.add(a);
        }
        this.aliens.clear();
        return existingAliens;
    }

    /**
     * spawns new boss alien
     *
     * @param lives set lives of new boss
     */
    public void createBossAlien(int lives){
        this.bossAliens.add(new BigBossAlien(this.window, lives));
    }

    /**
     * spawns new aliens
     *
     * @param x coordinate of spawn
     * @param y coordinate of spawn
     * @param lives of each alien
     */
    public void createAliens(int x, int y, int lives){
        // Create 12 aliens
        // Initial location of the aliens
        // (Make sure that the space ship can fire at them)
        for (int i = 0; i < 12; i++) {
            this.aliens.add(new Alien(this.window, new Point(x, y), lives));
            x += 5 * Alien.RADIUS;
        }
    }
}
