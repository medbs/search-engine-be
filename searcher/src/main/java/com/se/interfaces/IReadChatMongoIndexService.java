package com.se.interfaces;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

public interface IReadChatMongoIndexService {

    TopDocs searchUsingPhraseQuery(String[] textToFind, IndexSearcher searcher) throws IOException;

    TopDocs searchMultipleWords(String textToFind, IndexSearcher searcher) throws ParseException, IOException;
}
