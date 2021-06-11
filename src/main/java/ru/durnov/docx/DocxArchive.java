package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxArchive implements Archive {
    private final String documentUrl;
    private final XWPFDocument xwpfDocument;

    public DocxArchive(String documentUrl) throws IOException {
        this.documentUrl = documentUrl;
        this.xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of(documentUrl)));
    }
    
    @Override
    public String archiveUrl() {
        return this.documentUrl.replace(".docx", ".zip");
    }

    @Override
    public Images images() {
        return new DocxImages(this.xwpfDocument);
    }

    @Override
    public Chapters chapters() {
        return new DocxChapters(this.xwpfDocument);
    }
}
