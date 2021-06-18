package ru.durnov.chapters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentChapterTitle {
    private final static Pattern pattern = Pattern.compile("^(\\s*[0-9]+\\s*(\\.)*)+");
    private final String text;


    public ContentChapterTitle(String text) {
        this.text = text;
    }

    public String title() {
        Matcher matcher = pattern.matcher(this.text);
        if (matcher.find()){
            return matcher.group();
        }
        throw new IllegalArgumentException("Can't return content because paragraph is not chapter header");
    }
}
