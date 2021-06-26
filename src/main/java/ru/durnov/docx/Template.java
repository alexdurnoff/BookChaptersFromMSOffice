package ru.durnov.docx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Template {
    private final Document document;

    public Template(){
        String html = "<html>" +
                "<head>" +
                "<META http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" +
                "</head>" +
                "<body style=\"white-space-collapsing:preserve;\">\n" +
                "</body>\n" +
                "</html>";
        this.document = Jsoup.parse(html);
    }


    public Document document() {
        return this.document;
    }
}
