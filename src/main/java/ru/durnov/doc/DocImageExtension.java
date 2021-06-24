package ru.durnov.doc;

import ru.durnov.chapters.Image;

public class DocImageExtension {
    private final String extension;

    public DocImageExtension(Image image) {
        this.extension = image.name().substring(image.name().lastIndexOf("."));
    }

    public DocImageExtension(String imageName){
        this.extension = imageName.substring(imageName.lastIndexOf("."));
    }

    public String extension() {
        return this.extension;
    }
}
