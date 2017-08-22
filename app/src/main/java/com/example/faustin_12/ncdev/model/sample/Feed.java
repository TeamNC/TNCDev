package com.example.faustin_12.ncdev.model.sample;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by FAUSTIN-12 on 06/07/2016.
 */
@Root(name = "rss", strict = false)
public class Feed implements Serializable {
    @Element(name = "channel")
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public Feed() {
    }

    public Feed(Channel mChannel) {
        this.channel = mChannel;
    }
}