package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class LeftMargin {
    private final int value;

    public LeftMargin(XWPFParagraph xwpfParagraph, CTSectPr ctSectPr) {
        if (xwpfParagraph.getFirstLineIndent() != -1) {
            this.value = xwpfParagraph.getFirstLineIndent()/20;
        } else if(xwpfParagraph.getIndentFromLeft() != -1) {
            this.value = xwpfParagraph.getIndentFromLeft()/20;
        } else if (ctSectPr != null){
            if (ctSectPr.getPgMar() != null){
                this.value = ctSectPr.getPgMar().getLeft().intValue()/20;
            } else {
                this.value = -1;
            }
        } else {
            this.value = -1;
        }
    }

    public LeftMargin(CTSectPr ctSectPr) {
        if (ctSectPr != null){
            CTPageMar ctPageMar = ctSectPr.getPgMar();
            if (ctPageMar != null){
                this.value = ctPageMar.getLeft().intValue()/20;
            } else {
                this.value = -1;
            }
        } else {
            this.value = -1;
        }
    }

    public int value(){
        return this.value;
    }
}
