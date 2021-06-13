package ru.durnov.imageconverter;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFRenderer;
import org.freehep.graphicsio.svg.SVGGraphics2D;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class Emf2SVGConverter implements Converter{
    private final byte[] bytes;

    public Emf2SVGConverter(XWPFPictureData xwpfPictureData) throws IOException {
        if (xwpfPictureData.getPictureType() != Document.PICTURE_TYPE_EMF) throw new IllegalArgumentException(
                "XWPFPictureData type must be EMF"
        );
        this.bytes = xwpfPictureData.getData();
    }

    public Emf2SVGConverter(Path inputPath) throws IOException {
        this.bytes = Files.readAllBytes(inputPath);
        String path = inputPath.getFileName().toString();
        if (! path.endsWith(".emf")) throw new IllegalArgumentException("File extension must be .emf");
    }

    public Emf2SVGConverter(byte[] bytes){
        this.bytes = bytes;
    }

    @Override
    public byte[] asByteArray() throws IOException {
        EMFRenderer emfRenderer = new EMFRenderer(
                new EMFInputStream(
                        new ByteArrayInputStream(this.bytes)
                )
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        SVGGraphics2D svgGraphics2D = new SVGGraphics2D(
                outputStream,
                emfRenderer.getSize()
        );
        svgGraphics2D.setProperties(new SVGConverterProperties().properties());
        svgGraphics2D.startExport();
        emfRenderer.paint(svgGraphics2D);
        svgGraphics2D.endExport();
        return outputStream.toByteArray();
    }
}
