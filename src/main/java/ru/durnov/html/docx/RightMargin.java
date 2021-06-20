package ru.durnov.html.docx;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;

public class RightMargin {
    private final int value;

    public RightMargin(XWPFParagraph xwpfParagraph, CTSectPr ctSectPr) {
        if (xwpfParagraph.getIndentFromRight() != -1){
            this.value = xwpfParagraph.getIndentFromRight()/20;
        } else if (ctSectPr != null){
            if (ctSectPr.getPgMar() != null){
                this.value = ctSectPr.getPgMar().getRight().intValue()/20;
            } else {
                this.value = -1;
            }
        } else {
            this.value = -1;
        }
    }

    public RightMargin(CTSectPr ctSectPr) {
        if (ctSectPr != null){
            CTPageMar ctPageMar = ctSectPr.getPgMar();
            if (ctPageMar != null){
                this.value = ctPageMar.getRight().intValue()/20;
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
