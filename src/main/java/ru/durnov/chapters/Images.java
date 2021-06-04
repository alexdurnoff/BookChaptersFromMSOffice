package ru.durnov.chapters;

public interface Images {
    ImageExtractor imageExtractor();
    default void saveImages(){
        this.imageExtractor().imageList().forEach(Image::saveToArchive);
    };
}
