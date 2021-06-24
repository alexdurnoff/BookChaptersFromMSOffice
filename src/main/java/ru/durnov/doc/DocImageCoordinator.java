package ru.durnov.doc;

import org.jsoup.nodes.Element;
import ru.durnov.chapters.Chapter;
import ru.durnov.chapters.Image;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * Класс для правки назвний изображений внутри Doc-главы.
 * Нужен для согласования названий картинок в html-content и в папке library.
 */
public class DocImageCoordinator {
    private final Queue<Image> imageQueue;
    private final Queue<Element> imageElementQueue;


    public DocImageCoordinator(List<Image> imageList, List<Chapter> chapterList) {
        this.imageQueue = new ArrayDeque<>(imageList);
        this.imageElementQueue = new ImageElementQueue(chapterList).imageQueue();
        if (imageQueue.size() != imageElementQueue.size()) throw new IllegalStateException(
                "Number of images must be equal number of img-elements"
        );
    }

    public void replaceImagesName() {
        int number = 0;
        while (! imageQueue.isEmpty()){
            imageQueue
                    .poll()
                    .setName(
                            Objects.requireNonNull(imageElementQueue
                                    .poll())
                                    .attributes()
                                    .get("src")
                    );
        }
    }
}
