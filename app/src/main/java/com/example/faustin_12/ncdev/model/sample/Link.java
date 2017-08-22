package com.example.faustin_12.ncdev.model.sample;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
public class Link {
    @Attribute(required = false)
    public String href;
    @Attribute(required = false)
    public String rel;
    @Attribute(name = "type", required = false)
    public String contentType;
    @Text(required = false)
    public String link;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
