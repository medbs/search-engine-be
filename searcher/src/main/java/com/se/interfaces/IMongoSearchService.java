package com.se.interfaces;

import com.se.dto.ResponseDto;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;
import java.util.List;

public interface IMongoSearchService {

    TopDocs searchUsingPhraseQuery(String[] textToFind, IndexSearcher searcher) throws IOException;

    ResponseDto<List<Document>> searchMultipleWords(String textToFind, IndexSearcher searcher) throws ParseException, IOException;
}
