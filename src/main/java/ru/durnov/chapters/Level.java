package ru.durnov.chapters;

/**
 * Class encapsulate current level
 */
public class Level {
    private int value;

    public Level(){
        this.value = 0;
    }

    public Level(int value){
        this.value = value;
    }

    public void incrementLevel(){
        this.value +=1;
    }

    public void decrementLevel(){
        this.value -=1;
    }

    public int currentLevel(){
        return this.value;
    }
}
