package ru.durnov.docx;

import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс возвращает количество уровней внутри заголовка.
 */
public class DotNumbersInHeader {
    private final Matcher matcher;
    private final static Pattern pattern = Pattern.compile("^(\\s?[0-9]+\\s?\\.?)+");


    public DotNumbersInHeader(XWPFParagraph xwpfParagraph){
        this.matcher = pattern.matcher(xwpfParagraph.getRuns().get(0).getText(0));
    }

    public DotNumbersInHeader(Paragraph paragraph){
        this.matcher = pattern.matcher(paragraph.getCharacterRun(0).text());
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
        throw new IllegalArgumentException("The XWPFParagraph is not chapter header!");
    }
}
