package com.se.interfaces;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

import java.io.IOException;

public interface ISearchService {

    IndexSearcher createSearcher() throws IOException;

    Document toDocument(ScoreDoc scoreDoc, IndexSearcher searcher);

}
