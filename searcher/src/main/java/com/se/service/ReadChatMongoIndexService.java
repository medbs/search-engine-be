package com.se.service;

import com.se.interfaces.IReadChatMongoIndexService;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReadChatMongoIndexService implements IReadChatMongoIndexService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ReadChatMongoIndexService.class);


    public TopDocs searchMultipleWords(String wordsToFind, IndexSearcher searcher) throws ParseException, IOException {

        String words = wordsToFind.replace("+", " ");
        //Create search query
        QueryParser qp = new QueryParser("word", new StandardAnalyzer());
        Query query = qp.parse(words);

        LOGGER.info("Query: " + query.toString());

        //search the se
        TopDocs hits = searcher.search(query, 10);
        return hits;
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
