package com.se.service;

import com.se.dto.TextWordChatMessage;
import com.se.interfaces.IIndexService;
import com.se.utils.ChatDocumentFields;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class IndexService implements IIndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexService.class);

    public void index(Directory index, List<TextWordChatMessage> textWordChatMessage) {

        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        try {
            IndexWriter indexWriter = new IndexWriter(index, config);

            for (TextWordChatMessage msg : textWordChatMessage) {
                Document document = toDocument(msg);
                indexWriter.addDocument(document);
                indexWriter.commit();
            }

            indexWriter.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }


    public Document toDocument(TextWordChatMessage textWordChatMessage) {

        Document document = new Document();

        document.add(new StringField(ChatDocumentFields.WORD_FIELD, textWordChatMessage.getWord(), Field.Store.YES));
        //document.add(new SortedDocValuesField(ChatDocumentFields.WORD_FIELD, new BytesRef(textWordChatMessage.getWord())));

        document.add(new StringField(ChatDocumentFields.TEXT_FIELD, textWordChatMessage.getText(), Field.Store.YES));
       // document.add(new SortedDocValuesField(ChatDocumentFields.TEXT_FIELD, new BytesRef(textWordChatMessage.getText())));

        return document;
    }


    public static void displayQuery(Query query) {
        LOGGER.info("Query: " + query.toString());
    }
}
