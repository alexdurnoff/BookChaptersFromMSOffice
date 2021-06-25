package ru.durnov.docx;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс возвращает количество уровней внутри заголовка.
 */
@Slf4j
public class DotNumbersInHeader {
    private final Matcher matcher;
    private final String text;
    private final static Pattern pattern = Pattern.compile("^(\\s*[0-9]+\\s?\\.?)+");



    public DotNumbersInHeader(XWPFParagraph xwpfParagraph){
        this.text = xwpfParagraph.getText();
        this.matcher = pattern.matcher(text);
    }

    public DotNumbersInHeader(Paragraph paragraph){
        this.text = paragraph.text();
        this.matcher = pattern.matcher(text);
    }

    /**
     * Метод возвращает количество точек между цифрами в начале заголовка. Исключается точка в конце выражения.
     * @return
     */
    public int levelDepth(){
        int result = 0;
        if (matcher.find()){
            String header = matcher.group();
            if (header.endsWith(".")) header = header.substring(0,header.length()-1);
            char[] chars = header.toCharArray();
            for (char aChar : chars) {
                if (aChar == '.') result++;
            }
            return result;
        }
        throw new IllegalArgumentException("The Paragraph is not chapter header!" + "\n" +
                 "Text is " + this.text);
    }
}
