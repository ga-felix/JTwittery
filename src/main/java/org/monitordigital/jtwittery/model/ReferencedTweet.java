package org.monitordigital.jtwittery.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@IdClass(ReferencedTweetId.class)
public class ReferencedTweet implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    @Id
    private Tweet tweet;
    @Id
    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;

}