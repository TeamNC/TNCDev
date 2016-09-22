package com.example.faustin_12.ncdev.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */

@Root
public class RSS {

    @Attribute
    String version;

    @Element
    Channel channel;

    public Channel getChannel() {
        return channel;
    }

    @Override
    public String toString(){
        return "RSS{" +
                "version='" + version + '\'' +
                ", channel=" + channel +
                '}';
    }
}
