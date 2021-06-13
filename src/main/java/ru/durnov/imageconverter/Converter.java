package ru.durnov.imageconverter;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;
import java.nio.file.Path;

public interface Converter {
    byte[] asByteArray() throws IOException, TranscoderException;
}
