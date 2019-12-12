package com.se.service;


import com.se.dal.ChatCollectionQueries;
import com.se.dto.ResponseDto;
import com.se.dto.TextWordChatMessage;
import com.se.interfaces.IChatIndexService;
import com.se.interfaces.IIndexService;
import org.apache.lucene.store.FSDirectory;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatIndexService implements IChatIndexService {

    private final Logger logger = LoggerFactory.getLogger(ChatIndexService.class);

    @Value("${lucene.index-folder}")
    private String indexFolder;

    @Autowired
    ChatCollectionQueries chatCollectionQueries;

    @Autowired
    IIndexService indexService;

    public ResponseDto<?> indexChatMessages() {

        try {
            FSDirectory dir = FSDirectory.open(Paths.get(indexFolder));

            List<Document> chatMessageDocuments = chatCollectionQueries.MongoTimeIntervalMessages();
            if (chatMessageDocuments.isEmpty()) {
                return new ResponseDto<>(false, "querying mongo returned empty result ", null, HttpStatus.BAD_REQUEST);
            } else {
                List<TextWordChatMessage> textWordChatMessage = chatMessageDocuments.stream().map(TextWordChatMessage::new).collect(Collectors.toList());
                indexService.index(dir, textWordChatMessage);
                return new ResponseDto<>(true, "success", textWordChatMessage, HttpStatus.OK);
            }

        } catch (IOException e) {
            return new ResponseDto<>(false, e.getMessage(), null, HttpStatus.BAD_REQUEST);
        }
    }
}
