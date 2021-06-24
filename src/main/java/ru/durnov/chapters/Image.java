package ru.durnov.chapters;

import org.apache.batik.transcoder.TranscoderException;

import java.io.IOException;

public interface Image {
    String name();
    byte[] asByteArray() throws IOException, TranscoderException;
    default void setName(String name){
        throw new UnsupportedOperationException();
    }

    default String extension(){
        throw new UnsupportedOperationException();
    }

}
