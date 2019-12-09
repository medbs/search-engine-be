package com.se.service;

import com.se.interfaces.ISearchService;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;

@Service
public class SearchService implements ISearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Value("${lucene.index-folder}")
    private String indexFolder;

    public IndexSearcher createSearcher() throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexFolder));

        //It is an interface for accessing a point-in-time view of a lucene se
        IndexReader reader = DirectoryReader.open(dir);

        //Index searcher
        IndexSearcher searcher = new IndexSearcher(reader);
        return searcher;
    }


    public Document toDocument(ScoreDoc scoreDoc, IndexSearcher searcher) {
        try {
            return searcher.doc(scoreDoc.doc);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
