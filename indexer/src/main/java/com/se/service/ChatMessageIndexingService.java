package com.se.service;


import com.se.dal.ChatCollectionQueries;
import com.se.dto.TextWordChatMessage;
import com.se.interfaces.IChatMessageService;
import com.se.interfaces.IIndexService;
import org.apache.lucene.store.FSDirectory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatMessageIndexingService implements IChatMessageService {

    @Value("${lucene.index-folder}")
    private String indexFolder;

    @Autowired
    ChatCollectionQueries chatCollectionQueries;

    @Autowired
    IIndexService indexService;

    public void indexChatMessages() throws IOException {

        List<TextWordChatMessage> textWordChatMessage = new ArrayList<TextWordChatMessage>();
        FSDirectory dir = FSDirectory.open(Paths.get(indexFolder));

        List<Document> chatMessageDocuments = chatCollectionQueries.MongoTimeIntervalMessages();
        textWordChatMessage = chatMessageDocuments.stream().map(TextWordChatMessage::new).collect(Collectors.toList());

        indexService.index(dir, textWordChatMessage);
    }

}
