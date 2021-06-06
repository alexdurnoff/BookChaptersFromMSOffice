package ru.durnov.chapters;

import org.junit.jupiter.api.Test;
import ru.durnov.doc.DocArchive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchiveTest {

    @Test
    void testCompressFile() throws IOException {
        Archive archive = new Archive() {
            @Override
            public String url() {
                return "Test/archiveCompressFileTest";
            }

            @Override
            public Images images() {
                return new Images() {
                    @Override
                    public ImageExtractor imageExtractor() {
                        return new ImageExtractor() {
                            @Override
                            public List<Image> imageList() {
                                return Collections.singletonList(new Image() {
                                    @Override
                                    public String name() {
                                        return "image1.txt";
                                    }

                                    @Override
                                    public void saveToArchive(String url) {
                                        try {
                                            Files.writeString(Path.of(url + "image1.txt"), "Превед, вот изображение");
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public byte[] asByteArray() {
                                        return this.name().getBytes();
                                    }
                                });
                            }
                        };
                    }
                };
            }

            @Override
            public Chapters chapters() {
                return new Chapters() {
                    @Override
                    public ChapterExtractor chapterExtractor() {
                        return new ChapterExtractor() {
                            @Override
                            public List<Chapter> chapterList() throws IOException {
                                return Collections.singletonList(new Chapter() {
                                    @Override
                                    public String title() {
                                        return "testArchiveCompressFileChapter";
                                    }

                                    @Override
                                    public int level() {
                                        return 0;
                                    }

                                    @Override
                                    public boolean inline() {
                                        return true;
                                    }

                                    @Override
                                    public String content() {
                                        return "Превед! Тестовый текст для проверки создания архива";
                                    }
                                });
                            }
                        };
                    }
                };
            }
        };
        String path = archive.pathToArchive();
        System.out.println(path);
    }

}