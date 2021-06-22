package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.jsoup.nodes.Element;
import ru.durnov.html.PicturesElement;

import java.util.ArrayList;
import java.util.List;

public class PicturesElements {
    private final XWPFRun xwpfRun;

    public PicturesElements(XWPFRun xwpfRun) {
        this.xwpfRun = xwpfRun;
    }

    public List<Element> elements(){
        List<Element> elementList = new ArrayList<>();
        xwpfRun.getEmbeddedPictures().forEach(xwpfPicture -> {
            elementList.add(new PicturesElement(xwpfPicture).element());
        });
        return elementList;
    }
}
