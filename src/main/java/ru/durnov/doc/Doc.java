package ru.durnov.doc;

import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Document;
import ru.durnov.chapters.Images;

import java.io.IOException;

public class Doc implements Document {

    private final String url;

    public Doc(String url) {
        this.url = url;
    }


    @Override
    public Archive archive() throws IOException {
        return new DocArchive(url);
    }
}
