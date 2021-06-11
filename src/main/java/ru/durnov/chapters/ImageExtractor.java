package ru.durnov.chapters;

import java.io.IOException;
import java.util.List;

public interface ImageExtractor {
    List<Image> imageList() throws IOException;
}
