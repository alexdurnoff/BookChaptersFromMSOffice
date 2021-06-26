package ru.durnov.doc;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.durnov.chapters.Chapter;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;


public class ImageElementQueue {
    private final Queue<Element> imageElementQueue = new ArrayDeque<>();

    public ImageElementQueue(List<Chapter> chapterList) {
        chapterList.forEach(chapter -> {
            Document document = Jsoup.parse(chapter.content());
            imageElementQueue.addAll(document.getElementsByTag("img"));
        });
    }

    public Queue<Element> imageQueue() {
        return this.imageElementQueue;
    }
}
