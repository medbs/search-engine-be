package com.se.dto;

import org.bson.Document;

import java.io.Serializable;

public class TextWordChatMessage implements Serializable {

    private static final long serialVersionUID = 4288580173555383877L;
    private String word;
    private String text;

    public TextWordChatMessage() {

    }

    public TextWordChatMessage(String word, String text) {
        this.word = word;
        this.text = text;
    }

    public TextWordChatMessage(Document document) {
        this.text = document.getString("text");
        this.word = document.getString("word");
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

