package com.se.service;

import com.se.dto.ResponseDto;
import com.se.interfaces.IMongoSearchService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MongoSearchService implements IMongoSearchService {

    @Autowired
    SearchService searchService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoSearchService.class);


    public ResponseDto<List<Document>> searchMultipleWords(String wordsToFind, IndexSearcher searcher) {

        try {
            String words = wordsToFind.replace("+", " ");
            //Create search query
            QueryParser qp = new QueryParser("word", new StandardAnalyzer());
            Query query = qp.parse(words);

            LOGGER.info("Query: " + query.toString());

            //search the se
            TopDocs hits = searcher.search(query, 10);

            List<Document> docsList = Arrays.stream(hits.scoreDocs)
                    .map(scoreDoc -> searchService.toDocument(scoreDoc, searcher))
                    .collect(Collectors.toList());

            return new ResponseDto<>(true, "null", docsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseDto<>(true, e.getMessage(), null, HttpStatus.OK);
        }
    }


    public TopDocs searchUsingPhraseQuery(String[] wordsToFind, IndexSearcher searcher) throws IOException {

        Query importantQuery = new TermQuery(new Term("word", "set"));
        Query optionalQuery = new TermQuery(new Term("word", "order"));
        Query forbidQuery = new TermQuery(new Term("word", "kek"));
        BooleanQuery query = new BooleanQuery.Builder()
                .add(importantQuery, BooleanClause.Occur.MUST)
                .add(optionalQuery, BooleanClause.Occur.SHOULD)
                .add(forbidQuery, BooleanClause.Occur.MUST_NOT)
                .build();
        TopDocs hits = searcher.search(query, 10);
        return hits;
    }
}
