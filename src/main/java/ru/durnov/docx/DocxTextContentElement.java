package ru.durnov.docx;

import org.apache.poi.xwpf.usermodel.*;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.html.docx.DocxChildElement;
import ru.durnov.html.docx.DocxParagraphStyle;
import ru.durnov.html.docx.HtmlDivStyle;

import java.util.List;

public class DocxTextContentElement implements DocxContentElement {
    private final XWPFParagraph xwpfParagraph;
    private final CTSectPr ctSectPr;

    public DocxTextContentElement(IBodyElement bodyElement){
        if (! (bodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException("BodyElement must be XWPFParagraph");
        this.xwpfParagraph = (XWPFParagraph) bodyElement;
        this.ctSectPr = null;
    }

    public DocxTextContentElement(IBodyElement bodyElement, CTSectPr ctSectPr) {
        if (! (bodyElement instanceof XWPFParagraph)) throw new IllegalArgumentException("BodyElement must be XWPFParagraph");
        this.xwpfParagraph = (XWPFParagraph) bodyElement;
        this.ctSectPr = ctSectPr;
    }

    @Override
    public Element element() {
        Element div  = new Element("div");
        new HtmlDivStyle(xwpfParagraph, ctSectPr).applyTo(div);
        Element element = new Element("p");
        new DocxParagraphStyle(xwpfParagraph).applyToParagraphElement(element);
        xwpfParagraph.getIRuns().forEach(iRunElement -> {
            if (iRunElement instanceof XWPFHyperlinkRun){
                XWPFHyperlinkRun xwpfHyperlinkRun = (XWPFHyperlinkRun) iRunElement;
                new DocxChildElementFromHiperLink(xwpfHyperlinkRun).appendTo(element);
            } else if (iRunElement instanceof XWPFRun) {
                XWPFRun xwpfRun = (XWPFRun) iRunElement;
                new DocxChildElement(xwpfRun).appendTo(element);
            }
        });
        element.appendTo(div);
        return div;
    }
}
