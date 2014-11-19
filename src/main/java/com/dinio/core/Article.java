package com.dinio.core;

import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class Article {
    private String title;
    private String text;
    private URL link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }
}
