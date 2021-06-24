package ru.durnov.chapters;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public interface Document {
    Archive archive() throws IOException, ParserConfigurationException, TransformerException;
}
