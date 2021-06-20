package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.jsoup.nodes.Element;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class HtmlDivStyle {
    private final String marginParameters;
    private final String widthParameters;


    public HtmlDivStyle(XWPFParagraph xwpfParagraph, CTSectPr ctSectPr) {
        this.marginParameters = new HtmlMargin(xwpfParagraph, ctSectPr).marginStyleParameters();
        this.widthParameters = new HtmlWidth(ctSectPr).widthParameters();
    }

    public HtmlDivStyle(CTSectPr ctSectPr) {
        this.marginParameters = new HtmlMargin(ctSectPr).marginStyleParameters();
        this.widthParameters = "";
    }

    public void applyTo(Element div) {
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("float:none;");
        if (!this.marginParameters.equals("")) stringBuilder.append(marginParameters);
        //if (!this.widthParameters.equals("")) stringBuilder.append(widthParameters);
        div.attributes().put("style", stringBuilder.toString());
    }
}
