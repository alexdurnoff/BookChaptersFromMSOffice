package ru.durnov.doc;

import org.jsoup.nodes.Element;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public interface DocContentElement {
    Element element() throws ParserConfigurationException, TransformerException;
}
