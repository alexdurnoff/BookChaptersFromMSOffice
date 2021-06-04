package ru.durnov.docx;

import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Images;
import ru.durnov.doc.DocChapters;

public class DocxArchive implements Archive {
    private final String url;

    public DocxArchive(String url) {
        this.url=url;
    }
    
    @Override
    public String url() {
        return this.url.replace(".docx", ".zip");
    }

    @Override
    public Images images() {
        return new DocxImages(this.url());
    }

    @Override
    public Chapters chapters() {
        return new DocChapters(this.url());
    }
}
