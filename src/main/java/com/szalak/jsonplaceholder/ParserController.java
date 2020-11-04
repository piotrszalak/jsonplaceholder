package com.szalak.jsonplaceholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParserController {

    private ParserService parserService;

    @Autowired
    public ParserController(ParserService parserService) {
        this.parserService = parserService;
    }


    @GetMapping("/posts")
    public ResponseEntity<?> getPostsToFiles(){
        ResponseEntity<?> responseEntity;
        try {
           parserService.saveToJsonFile();
           responseEntity = new ResponseEntity<>("Posts successfully saved to json files", HttpStatus.OK);
       }catch (Exception e){
           responseEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
       }

        return responseEntity;
    }
}
