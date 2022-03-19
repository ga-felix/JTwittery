package org.monitordigital.jtwittery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TweetType {

    STANDARD("standard"),
    RETWEET("retweet"),
    QUOTE("quote"),
    REPLY("reply");

    @Getter
    private final String type;

}
