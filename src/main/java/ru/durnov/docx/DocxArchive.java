package ru.durnov.docx;

import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Images;

public class DocxArchive implements Archive {
    private final String documentUrl;

    public DocxArchive(String documentUrl) {
        this.documentUrl = documentUrl;
    }
    
    @Override
    public String archiveUrl() {
        return this.documentUrl.replace(".docx", ".zip");
    }

    @Override
    public Images images() {
        return new DocxImages(this.archiveUrl());
    }

    @Override
    public Chapters chapters() {
        return new DocxChapters(this.documentUrl);
    }
}
