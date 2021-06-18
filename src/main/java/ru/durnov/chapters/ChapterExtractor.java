package ru.durnov.chapters;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

public interface ChapterExtractor {
    List<Chapter> chapterList() throws IOException, ParserConfigurationException, TransformerException;
}
