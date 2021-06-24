package ru.durnov.doc;

import org.apache.poi.hwpf.HWPFDocument;
import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Images;
import ru.durnov.oldword.HWPFDocumentWithoutPictures;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocArchive implements Archive {
    private final String documentUrl;
    private final HWPFDocument hwpfDocument;
    private final Images images;
    private final Chapters chapters;

    public DocArchive(String documentUrl) throws IOException {
        this.documentUrl = Path.of(documentUrl).getFileName().toString();
        this.hwpfDocument = new HWPFDocument(Files.newInputStream(Path.of(documentUrl)));
        this.images = new DocImages(this.hwpfDocument);
        this.chapters = new DocChapters(this.hwpfDocument);
    }
    
    @Override
    public String archiveUrl() {
        return "/tmp/" + this.documentUrl.replace(".doc", ".zip");
    }

    @Override
    public Images images() {
        return this.images;
    }

    @Override
    public Chapters chapters() throws IOException {
        return this.chapters;
    }

    @Override
    public void compressFiles() throws Exception {
        new DocImageCoordinator(
                this.images.imageExtractor().imageList(),
                this.chapters.chapterExtractor().chapterList()
        ).replaceImagesName();
        Archive.super.compressFiles();
    }
}
