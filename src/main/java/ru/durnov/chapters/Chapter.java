package ru.durnov.chapters;

public interface Chapter {
    String title();
    int level();
    boolean inline();
    String content();
    void saveToArchive();
}
