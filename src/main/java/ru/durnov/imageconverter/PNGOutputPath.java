package ru.durnov.imageconverter;

import java.nio.file.Path;

public class PNGOutputPath {
    private final String inputPathName;

    public PNGOutputPath(Path inputPath) {
        this.inputPathName = inputPath.toAbsolutePath().getFileName().toString();
    }

    public Path path() {
        if (this.inputPathName.endsWith(".svg")) return Path.of(this.inputPathName.replace(".svg", ".png"));
        throw new IllegalArgumentException("input path file name must be ends with .svg");
    }
}
