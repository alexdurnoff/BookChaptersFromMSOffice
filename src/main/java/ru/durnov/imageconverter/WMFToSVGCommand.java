package ru.durnov.imageconverter;

import org.apache.poi.xwpf.usermodel.XWPFPictureData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WMFToSVGCommand {
    private final String fileName;

    public WMFToSVGCommand(XWPFPictureData xwpfPictureData) throws IOException {
        this.fileName = "tmp/" + xwpfPictureData.getFileName();
        if (! this.fileName.endsWith(".wmf")) throw new IllegalArgumentException("file extension must be .wmf");
        Files.write(Path.of(xwpfPictureData.getFileName()), xwpfPictureData.getData());
    }

    public WMFToSVGCommand(String fileName){
        if (! fileName.endsWith(".wmf")) throw new IllegalArgumentException("file extension must be .wmf");
        this.fileName = fileName;
    }

    public String[] commandLineArray() {
        String[] args = new String[2];
        args[0] = fileName;
        args[1] = fileName.replace(".wmf", ".svg");
        return args;
    }
}
