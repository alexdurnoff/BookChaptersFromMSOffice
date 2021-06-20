package ru.durnov.chapters;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NonHeaderChapterTitle {
    private final static Pattern pattern = Pattern.compile("^(\\s*[0-9]+\\s*(\\.)*)+");
    private final Matcher matcher;
    private final String title;

    public NonHeaderChapterTitle(String paragraphText){
        this.matcher = pattern.matcher(paragraphText);
        if (matcher.find()){
            this.title = matcher.group().trim();
        } else {
            throw new IllegalArgumentException("Can't return content because paragraph is not chapter header");
        }

    }

    public String title(){
        return this.title;
    }


}
