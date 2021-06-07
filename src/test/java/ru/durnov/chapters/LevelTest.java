package ru.durnov.chapters;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.jupiter.api.Test;
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

    @Test
    public void test1(){
        Level level = new Level(this.xwpfDocument);
        XWPFParagraph xwpfParagraph = this.xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Heading 1");
        xwpfParagraph.createRun().setText("1. Главный заголовок");
        assertEquals(1, level.levelByParagraph(xwpfParagraph));
        xwpfParagraph = this.xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.3 Общие положения");
        assertEquals(2, level.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.4 Думы о прекрасном");
        assertEquals(2, level.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.4.5 Думы о прекрасном");
        assertEquals(3, level.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Heading 3");
        xwpfParagraph.createRun().setText("Думы о прекрасном");
        assertEquals(2, level.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Heading 1");
        xwpfParagraph.createRun().setText("Думы о прекрасном");
        assertEquals(1, level.levelByParagraph(xwpfParagraph));
        xwpfParagraph = this.xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2. Общие положения");//Последняя точка убирается!!
        assertEquals(2, level.levelByParagraph(xwpfParagraph));
        xwpfParagraph = xwpfDocument.createParagraph();
        xwpfParagraph.setStyle("Обычный");
        xwpfParagraph.createRun().setText("2.1.4.5 Думы о прекрасном");
        assertEquals(5, level.levelByParagraph(xwpfParagraph));
    }

}