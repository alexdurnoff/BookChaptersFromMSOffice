package ru.durnov.chapters;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface ChapterFactory {
    Chapter chapter() throws ParserConfigurationException, TransformerException;
}
