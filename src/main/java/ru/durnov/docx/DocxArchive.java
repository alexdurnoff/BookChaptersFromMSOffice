package ru.durnov.docx;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.durnov.chapters.Archive;
import ru.durnov.chapters.Chapters;
import ru.durnov.chapters.Images;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class DocxArchive implements Archive {
    private final String documentUrl;
    private final XWPFDocument xwpfDocument;

    public DocxArchive(String documentUrl) throws IOException {
        try {
            this.documentUrl = Path.of(documentUrl).getFileName().toString();
            this.xwpfDocument = new XWPFDocument(Files.newInputStream(Path.of(documentUrl)));
        } catch (IOException e) {
            StringBuilder stringBuilder = new StringBuilder();
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                String str = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName();
                log.error(str);
                stringBuilder.append("\n").append(str);
            }
            throw new IOException("IO Exception " + e.getMessage() + stringBuilder.toString());
        }
    }
    
    @Override
    public String archiveUrl() {
        return "/tmp/" + this.documentUrl.replace(".docx", ".zip");
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
