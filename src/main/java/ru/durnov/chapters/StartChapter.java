package ru.durnov.chapters;

public class StartChapter implements Chapter{
    private final String title = "Начало документа";
    private final String content;

    public StartChapter(String content) {
        this.content = content;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public int level() {
        return 1;
    }

    @Override
    public boolean inline() {
        return true;
    }

    @Override
    public String content() {
        return this.content;
    }
}
