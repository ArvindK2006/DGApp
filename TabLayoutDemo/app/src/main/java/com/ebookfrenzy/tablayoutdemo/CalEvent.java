package com.ebookfrenzy.tablayoutdemo;

public class CalEvent {

    private String summary, htmlLink, iCalUID;

    public CalEvent(String s, String link, String uid){
        summary = s;
        htmlLink = link;
        iCalUID = uid;
    }
}
