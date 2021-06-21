package ru.durnov.docx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * return header level by XWPFStyle
 */
public class HeaderStyleLevel {
    private final static Pattern pattern = Pattern.compile("[0-9]");
    private final Matcher matcher;

    public HeaderStyleLevel(String styleName) {
        if (!styleName.contains("Heading") && ! styleName.contains("heading") && !styleName.contains("Заголовок")){
            throw new IllegalArgumentException("This is non header style!");
        }
        this.matcher = pattern.matcher(styleName);
    }

    public int level(){
        if (matcher.find()) return Integer.parseInt(matcher.group());
        return 1;
    }
}
