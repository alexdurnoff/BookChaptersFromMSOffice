package ru.durnov.html;

import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import ru.durnov.queue.Pictures;

public class PicturesElement {
    private final String fileName;
    private final String align;
    private final String width;
    private final String height;

    public PicturesElement(XWPFPicture xwpfPicture) {
        this.fileName = xwpfPicture.getPictureData().getFileName();
        this.width = String.valueOf(xwpfPicture.getWidth());
        this.height = "auto";
        this.align = "center";
    }

    public PicturesElement(Pictures pictures){
        Picture picture = pictures.nextPicture();
        this.fileName = picture.suggestFullFileName();
        this.width = String.valueOf(picture.getWidth());
        this.height = String.valueOf(picture.getHeight());
        this.align = "center";
    }

    public PicturesElement(Picture picture,String align){
        this.fileName = picture.suggestFullFileName();
        this.width = String.valueOf(picture.getWidth());
        this.height = String.valueOf(picture.getHeight());
        this.align = "center";
    }

    public Element element() {
        Element element = new Element("img");
        Attributes attributes = element.attributes();
        attributes.put("src", "library/" + fileName);
        attributes.put("width", this.width);
        attributes.put("height", this.height);
        return element;
    }
}
