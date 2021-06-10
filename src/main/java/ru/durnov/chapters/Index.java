package ru.durnov.chapters;

/**
 * Class for iterating by document paragraphs.
 * Encapsulate current index.
 */
public class Index {
    private int value = 0;

    public Index(){
        this.value = 0;
    }

    public Index(int currentIndex) {
        this.value = currentIndex;
    }

    public int currentIndex(){
        return value;
    }

    public void incrementIndex(){
        this.value +=1;
    }

    public void decrementIndex(){this.value--;}

}
