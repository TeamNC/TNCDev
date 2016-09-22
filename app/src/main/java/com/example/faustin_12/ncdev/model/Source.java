package com.example.faustin_12.ncdev.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
public class Source {
    @Attribute(name = "url", required = false)
    private String url;
    @Text(required = false)
    private String text;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
