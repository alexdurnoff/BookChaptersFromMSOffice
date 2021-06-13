package ru.durnov.docx;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import ru.durnov.chapters.Image;
import ru.durnov.imageconverter.SVGToPNGConverter;
import ru.durnov.imageconverter.WMFToSVGCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DocxWMFImage implements Image {
    private final String fileName;
    private final byte[] bytes;

    public DocxWMFImage(XWPFPictureData xwpfPictureData) {
        this.fileName = xwpfPictureData.getFileName();
        this.bytes = xwpfPictureData.getData();
    }

    public DocxWMFImage(String fileName) throws IOException {
        this.fileName = fileName;
        this.bytes = Files.readAllBytes(Path.of(fileName));
    }

    @Override
    public String name() {
        return this.fileName.replace(".wmf", ".png");
    }

    @Override
    public byte[] asByteArray() throws IOException, TranscoderException {
        Path inputPath = Path.of("tmp/" + this.fileName);
        if (! Files.exists(inputPath)) Files.write(inputPath, this.bytes);
        String[] args = new WMFToSVGCommand(this.fileName).commandLineArray();
        net.arnx.wmf2svg.Main.main(args);
        byte[] byteArray = new SVGToPNGConverter(
                Files.readAllBytes(
                        Path.of(args[args.length-1])
                )
        ).asByteArray();
        new Thread(() -> {
            try {
                Files.delete(inputPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return byteArray;
    }
}
