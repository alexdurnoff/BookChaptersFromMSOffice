package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Images;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocArchive implements Archive {
    private final String documentUrl;
    private final HWPFDocument hwpfDocument;

    public DocArchive(String url) throws IOException {
        this.documentUrl = url;
        this.hwpfDocument = new HWPFDocument(Files.newInputStream(Path.of(url)));
    }
    
    @Override
    public String archiveUrl() {
        return this.documentUrl.replace(".doc", ".zip");
    }

    @Override
    public Images images() {
        return new DocImages(this.hwpfDocument);
    }

    @Override
    public Chapters chapters() {
        return new DocChapters(this.hwpfDocument);
    }
}
