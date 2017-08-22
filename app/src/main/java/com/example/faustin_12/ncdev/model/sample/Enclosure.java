package com.example.faustin_12.ncdev.model.sample;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */

@Root(name = "enclosure", strict = false)
public class Enclosure {
    @Attribute(name = "url")
    private String enclosureLink = "http://";
    @Attribute(name = "length", required = false)
    private long length;
    @Attribute(name = "type", required = false)
    private String enclosureType;

    public String getEnclosureLink() {
        if (enclosureLink == null)
            return "me";
        else
            return enclosureLink;
    }

    public void setEnclosureLink(String enclosureLink) {
        this.enclosureLink = enclosureLink;
    }

    public String getEnclosureType() {
        return enclosureType;
    }

    public void setEnclosureType(String enclosureType) {
        this.enclosureType = enclosureType;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }
}
