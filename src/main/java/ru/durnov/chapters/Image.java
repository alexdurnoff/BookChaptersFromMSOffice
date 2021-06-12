package ru.durnov.chapters;

import java.io.IOException;

public interface Image {
    String name();
    byte[] asByteArray() throws IOException;
}
