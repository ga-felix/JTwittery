package org.monitordigital.jtwittery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TweetType {

    STANDARD("standard"),
    RETWEET("retweet"),
    QUOTE("quote"),
    REPLY("reply");

    private final String type;

}
