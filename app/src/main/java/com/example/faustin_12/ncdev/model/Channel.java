package com.example.faustin_12.ncdev.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
@Root(name = "channel", strict = false)
public class Channel implements Serializable {
    @ElementList(entry="link" , inline = true, required = false)
    private List<Link> links;

    @ElementList(name="item", inline = true)
    private List<FeedItem> itemList;

    @Element(required = false)
    String title;
    @Element(required = false)
    String language;

    @Element(name = "ttl", required = false)
    int ttl;

    @Element(name = "pubDate", required = false)
    String pubDate;

    @Override
    public String toString(){
        return "Channel{" +
                "links=" + links +
                ", itemList=" + itemList +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", ttl=" + ttl +
                ", pubDate='" + pubDate +'\'' +
                '}';
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<FeedItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<FeedItem> itemList) {
        this.itemList = itemList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /*public Channel() {
    }

    public Channel(List<FeedItem> itemList) {
        this.itemList = itemList;
    }*/
}