package ru.durnov.docx;

import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Document;
import ru.durnov.chapters.Images;

import java.io.IOException;

public class Docx implements Document {

    private final String url;

    public Docx(String url) {
        this.url = url;
    }

    @Override
    public Archive archive() throws IOException {
        return new DocxArchive(url);
    }
}
