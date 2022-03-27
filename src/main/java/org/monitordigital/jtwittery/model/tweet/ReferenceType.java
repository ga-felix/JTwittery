package org.monitordigital.jtwittery.model.tweet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ReferenceType {

    RETWEETED("retweeted"),
    QUOTED("quoted"),
    REPLIED_TO("replied_to");

    @Getter
    private final String type;

}
