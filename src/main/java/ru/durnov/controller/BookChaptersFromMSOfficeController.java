package ru.durnov.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.batik.transcoder.TranscoderException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(path = "/", produces = "application/json")
public class BookChaptersFromMSOfficeController {
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Response response(@RequestBody Request request) throws Exception {
        log.info(request.toString());
        Response response;
        try {
            response = new Response(request.document().archive().pathToArchive());

        } catch (IOException exception){
            log.error(exception.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Ошибка чтения-записи" + exception.getMessage(),
                    exception);
        } catch (TranscoderException exception){
            log.error("TranscoderException");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка преобразования файла",exception);
        } catch (Exception e){
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error",e);
        }
        return response;
    }
}
