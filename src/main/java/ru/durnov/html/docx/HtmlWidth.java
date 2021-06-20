package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class HtmlWidth {
    private final double width;

    public HtmlWidth (CTSectPr ctSectPr) {
        if (ctSectPr != null){
            if (ctSectPr.getPgSz() != null){
                this.width = ctSectPr.getPgSz().getW().intValue()/20.;
            } else {
                this.width = 0.;
            }
        } else {
            this.width = 0;
        }
    }

    public String widthParameters(){
        StringBuilder stringBuilder = new StringBuilder();
        if (this.width > 0.){
            stringBuilder.append("width:").append(this.width).append(';');
        }
        return stringBuilder.toString();
    }
}
