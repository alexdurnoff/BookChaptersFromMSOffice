package ru.durnov.chapters;

public class OfficeImages implements Images{
    private final ImageExtractor imageExtractor;

    public OfficeImages(ImageExtractor imageExtractor) {
        this.imageExtractor = imageExtractor;
    }

    @Override
    public ImageExtractor imageExtractor() {
        return this.imageExtractor;
    }
}
