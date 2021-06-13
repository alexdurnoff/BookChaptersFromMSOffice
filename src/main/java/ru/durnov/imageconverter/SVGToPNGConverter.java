package ru.durnov.imageconverter;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class SVGToPNGConverter implements Converter{
    private final ByteArrayInputStream inputStream;
    private final ByteArrayOutputStream outputStream;

    public SVGToPNGConverter(Path inputPath) throws IOException {
        this.inputStream = new ByteArrayInputStream(Files.readAllBytes(inputPath));
        this.outputStream = new ByteArrayOutputStream();
    }

    public SVGToPNGConverter(byte[] bytes){
        this.inputStream = new ByteArrayInputStream(bytes);
        this.outputStream = new ByteArrayOutputStream();
    }

    @Override
    public byte[] asByteArray() throws IOException, TranscoderException {
        TranscoderInput transcoderInput = new TranscoderInput(this.inputStream);
        TranscoderOutput transcoderOutput = new TranscoderOutput(this.outputStream);
        PNGTranscoder transcoder = new PNGTranscoder();
        transcoder.transcode(transcoderInput, transcoderOutput);
        return this.outputStream.toByteArray();
    }
}
