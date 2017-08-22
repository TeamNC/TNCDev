package com.example.faustin_12.ncdev.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
@Root(name = "item", strict = false)
public class FeedItem implements Serializable {
    @Element(name = "title")
    private String title;
    @Element(name = "link", required = false)
    private String link;
    @Element(name = "description", required = false)
    private String description;
    @Element(name = "author", required = false)
    private String author;
    //@ElementBoiteSnack(name = "category", required = false)
    //private String category;
    @Element(name = "comments", required = false)
    private String comments;
    @Element(name = "enclosure", required = false)
    private Enclosure enclosure;
    @Element(name = "guid", required = false)
    private String guid;
    @Element(name = "pubDate", required = false)
    private String pubDate;
    @Element(name = "source", required = false)
    private Source source;

    private String internalImageUrl;

    public String getInternalImageUrl() {
        return internalImageUrl;
    }

    public void setInternalImageUrl(String internalImageUrl) {
        this.internalImageUrl = internalImageUrl;
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString(){
        return "Item{" +
                "title='" + title + '\'' +
                ", link='" + link +'\'' +
                ", description='" + description +  '\'' +
                ", author='" + author +'\'' +
                //", category='" + category + '\'' +
                ", comments='" + comments + '\'' +
                ", enclosure='" + enclosure + '\'' +
                ", guid='" +  guid +'\'' +
                ", pubDate='" + pubDate +'\'' +
                ", source='" + source +'\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

   /* public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }*/

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Enclosure getEnclosure() {
        if (enclosure == null)
        {
            Enclosure me = new Enclosure();
            me.setEnclosureLink("me");
            me.setEnclosureType("default");
            return me;
        }
        else
            return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}