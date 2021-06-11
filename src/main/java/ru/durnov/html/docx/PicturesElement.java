package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.jsoup.nodes.Element;

public class PicturesElement {
    private final XWPFPicture xwpfPicture;

    public PicturesElement(XWPFPicture xwpfPicture) {
        this.xwpfPicture = xwpfPicture;
    }

    public Element element() {
        Element element = new Element("a");
        element.attributes().put("href", "file://library/" + xwpfPicture.getPictureData().getFileName());
        return element;
    }
}
