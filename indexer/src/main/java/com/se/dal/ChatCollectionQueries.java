package com.se.dal;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;


@Component
public class ChatCollectionQueries {

    @Value("${mongodb.url}")
    private String mongoURI;

    @Value("${mongodb.sm-database}")
    private String smDatabase;


    public List<Document> MongoTimeIntervalMessages() {

        MongoClientURI connectionString = new MongoClientURI(mongoURI);
        MongoClient mongoClient = new MongoClient(connectionString);
        MongoDatabase database = mongoClient.getDatabase(smDatabase);
        MongoCollection<Document> collection = database.getCollection("chat");

        List<Document> textWordChatMessage = collection
                .find()
                .projection(include("word", "text"))
                .into(new ArrayList<Document>());

        return textWordChatMessage;

    }

}
