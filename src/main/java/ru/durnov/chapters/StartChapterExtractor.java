package ru.durnov.chapters;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface StartChapterExtractor {
    StartChapter startChapter() throws ParserConfigurationException, TransformerException;
}
