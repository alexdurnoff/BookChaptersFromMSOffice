package ru.durnov.chapters;

import org.apache.batik.transcoder.TranscoderException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArchiveTest {

    @Test
    void testCompressFile() throws Exception {
        Archive archive = new Archive() {
            @Override
            public String archiveUrl() {
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
                                        return "????????????! ???????????????? ?????????? ?????? ???????????????? ???????????????? ????????????";
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