package ru.durnov.chapters;

import java.io.IOException;

public class OfficeDocument implements Document {
    private final Archive archive;

    public OfficeDocument(Archive archive) {
        this.archive = archive;
    }

    @Override
    public Archive archive() throws IOException {
        return this.archive;
    }
}
