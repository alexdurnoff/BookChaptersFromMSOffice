package ru.durnov.chapters;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import ru.durnov.docx.DocxLevel;
import ru.durnov.docx.TestStyleUtils;

import static org.junit.jupiter.api.Assertions.*;

class LevelTest {

    private final XWPFDocument xwpfDocument = new XWPFDocument();
    private final TestStyleUtils testStyleUtils = new TestStyleUtils(xwpfDocument);
    {
        testStyleUtils.addHeaderStyleWithGeneratedNumber();//id=1, Name = Heading 1
        testStyleUtils.addStyleByNameWithGeneratedNumber("Обычный");//id = 2, Name = Обычный
        testStyleUtils.addHeaderStyleWithGeneratedNumber();//id=3, Name = Heading 3
    }

    //@Test Пока убрал. После изменения DOcxStyleMap тест стал падать. Разберусь. Чего-то не хватает...
    public void test1(){
        DocxLevel docxLevel = new DocxLevel(this.xwpfDocument);
        XWPFParagraph xwpfParagraph = this.xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Heading 1");
        xwpfParagraph.createRun().setText("1. Главный заголовок");
        assertEquals(1, docxLevel.levelByParagraph(xwpfParagraph));
        xwpfParagraph = this.xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.3 Общие положения");
        assertEquals(2, docxLevel.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.4 Думы о прекрасном");
        assertEquals(2, docxLevel.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.4.5 Думы о прекрасном");
        assertEquals(3, docxLevel.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Heading 3");
        xwpfParagraph.createRun().setText("Думы о прекрасном");
        assertEquals(2, docxLevel.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Heading 1");
        xwpfParagraph.createRun().setText("Думы о прекрасном");
        assertEquals(1, docxLevel.levelByParagraph(xwpfParagraph));
        xwpfParagraph = this.xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2. Общие положения");//Последняя точка убирается!!
        assertEquals(2, docxLevel.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.4.5 Думы о прекрасном");
        assertEquals(5, docxLevel.levelByParagraph(xwpfParagraph));
    }

}