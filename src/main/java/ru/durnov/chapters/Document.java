package ru.durnov.chapters;

import java.io.IOException;

public interface Document {
    Archive archive() throws IOException;
}
