package ru.durnov.html;

import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.jsoup.nodes.Element;

public class PicturesElement {
    private final String fileName;
    private final String align;
    private final String width;
    private final String height;

    public PicturesElement(XWPFPicture xwpfPicture) {
        this.fileName = xwpfPicture.getPictureData().getFileName();
        this.width = String.valueOf(xwpfPicture.getWidth());
        this.height = String.valueOf(xwpfPicture.getDepth()/ xwpfPicture.getWidth());
        this.align = "center";
    }

    public PicturesElement(CharacterRun characterRun, PicturesTable picturesTable){
        Picture picture = picturesTable.extractPicture(characterRun, true);
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
        element.attributes().put("src", "library/" + fileName);

        return element;
    }
}
