package org.monitordigital.jtwittery.model;


import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
public class ReferencedTweetId implements Serializable {

    private Long tweet;
    private ReferenceType referenceType;

    public ReferencedTweetId(Tweet tweet, ReferenceType referenceType) {
        this.tweet = tweet.getId();
        this.referenceType = referenceType;
    }
}