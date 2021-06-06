package ru.durnov.chapters;

public interface Image {
    String name();
    void saveToArchive(String url);
    byte[] asByteArray();
}
