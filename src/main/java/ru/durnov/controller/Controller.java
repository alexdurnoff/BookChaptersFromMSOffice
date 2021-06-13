package ru.durnov.controller;

import org.apache.batik.transcoder.TranscoderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.durnov.chapters.Chapters;

import java.io.IOException;

@RestController
@RequestMapping(path = "/", produces = "application/json")
public class Controller {
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Response response(@RequestBody Request request) throws IOException, TranscoderException {
        return new Response(
                request.document().archive().pathToArchive()
        );
    }
}
