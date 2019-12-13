package com.se.rest;


import com.se.dto.ResponseDto;
import com.se.interfaces.IMongoSearchService;
import com.se.interfaces.ISearchService;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1")
public class MongoSearchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoSearchController.class);

    @Autowired
    IMongoSearchService readChatMongoIndexService;

    @Autowired
    ISearchService searchService;


    @RequestMapping(value = "/search/{word}", method = RequestMethod.POST)
    public ResponseEntity<Object> searchWords(@PathVariable(name = "word") String words) throws Exception {

        IndexSearcher searcher = searchService.createSearcher();
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseDto<List<Document>> response = readChatMongoIndexService.searchMultipleWords(words, searcher);

        return new ResponseEntity<>(response, httpHeaders, HttpStatus.OK);
    }

}
