package org.monitordigital.jtwittery.repository;

import org.monitordigital.jtwittery.model.tweet.ReferencedTweet;
import org.monitordigital.jtwittery.model.tweet.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferencedTweetRepository extends JpaRepository<ReferencedTweet, Tweet> {
}