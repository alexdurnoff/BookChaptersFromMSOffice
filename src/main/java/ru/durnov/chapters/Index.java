package ru.durnov.chapters;

/**
 * Class for iterating by document paragraphs.
 * Encapsulate current index.
 */
public class Index {
    private int value = 0;

    public int currentIndex(){
        return value;
    }

    public void incrementIndex(){
        this.value +=1;
    }

}
