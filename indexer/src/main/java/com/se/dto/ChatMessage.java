package com.se.dto;

import org.bson.Document;


public class ChatMessage {
    private String text;
    private String word;
    private Long timeStamp;
    private int count;
    private int orgId;
    private int workspaceId;

    public ChatMessage() {

    }

    public ChatMessage(Long timeStamp, int count, String text, String word, int orgId, int workspaceId) {
        this.text = text;
        this.word = word;
        this.timeStamp = timeStamp;
        this.count = count;
        this.orgId = orgId;
        this.workspaceId = workspaceId;
    }

    public ChatMessage(Document document) {
        this.text = document.getString("text");
        this.word = document.getString("word");
        this.timeStamp = document.getLong("timeStamp");
        this.count = Integer.parseInt(document.getString("count"));
        this.orgId = Integer.parseInt(document.getString("orgId"));
        this.workspaceId = Integer.parseInt(document.getString("workspaceId"));
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCout() {
        return count;
    }

    public void setCout(int count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getOrgid() {
        return orgId;
    }

    public void setOrgid(int orgId) {
        this.orgId = orgId;
    }

    public int getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(int workspaceId) {
        this.workspaceId = workspaceId;
    }
}



