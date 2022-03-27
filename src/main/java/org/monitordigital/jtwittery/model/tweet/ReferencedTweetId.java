package org.monitordigital.jtwittery.model.tweet;


import lombok.NoArgsConstructor;
import org.monitordigital.jtwittery.model.tweet.ReferenceType;
import org.monitordigital.jtwittery.model.tweet.Tweet;

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