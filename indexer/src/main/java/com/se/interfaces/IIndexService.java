package com.se.interfaces;

import com.se.dto.TextWordChatMessage;
import org.apache.lucene.document.Document;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.List;

public interface IIndexService {

    void index(Directory index, List<TextWordChatMessage> chatMessages) throws IOException;

    Document toDocument(TextWordChatMessage textWordChatMessage);
}
