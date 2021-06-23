package ru.durnov.html.docx;

import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import ru.durnov.doc.ParagraphWithSection;

public class HtmlMargin {
    private final int leftMargin;
    private final int rightMargin;


    public HtmlMargin(int leftMargin, int rightMargin, int topMargin, int bottomMargin) {
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;

    }

    public HtmlMargin(XWPFParagraph xwpfParagraph, CTSectPr ctSectPr){
        this.leftMargin = new LeftMargin(xwpfParagraph, ctSectPr).value();
        this.rightMargin = new RightMargin(xwpfParagraph, ctSectPr).value();
    }

    public HtmlMargin(CTSectPr ctSectPr) {
        this.leftMargin = new LeftMargin(ctSectPr).value();
        this.rightMargin = new RightMargin(ctSectPr).value();
    }

    public HtmlMargin(ParagraphWithSection paragraphWithSection) {
        this.leftMargin = paragraphWithSection.section().getMarginLeft()/20;
        this.rightMargin = paragraphWithSection.section().getMarginRight()/20;
    }
    public HtmlMargin(Section section) {
        this.leftMargin = section.getMarginLeft()/20;
        this.rightMargin = section.getMarginRight()/20;
    }


    public String marginStyleParameters(){
        StringBuilder stringBuilder = new StringBuilder();
        if (this.leftMargin != -1) {
            stringBuilder.append("margin-left:").append(leftMargin).append(';');
        }
        if (this.rightMargin != -1) {
            stringBuilder.append("margin-right:").append(rightMargin).append(';');
        }
        return stringBuilder.toString();
    }

}
