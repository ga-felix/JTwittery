package org.monitordigital.jtwittery.model.tweet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.monitordigital.jtwittery.model.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Tweet implements Serializable {

    @Id
    private Long id;
    private String text;
    private OffsetDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;
    private Short likes;
    private Short retweets;
    private Short quotes;
    private Short replies;
    @Enumerated(EnumType.STRING)
    private TweetType type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReferencedTweet> referenced;

}