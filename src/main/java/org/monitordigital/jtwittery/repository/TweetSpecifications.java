package org.monitordigital.jtwittery.repository;

import org.monitordigital.jtwittery.model.tweet.Tweet;
import org.monitordigital.jtwittery.model.tweet.TweetType;
import org.monitordigital.jtwittery.model.user.User;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.util.List;

import static java.util.Objects.isNull;

public class TweetSpecifications {

    public static Specification<Tweet> withAuthors(List<User> authors) {
        if (isNull(authors)) {
            return null;
        } else {
            return (root, query, cb) -> root.get("author").in(authors);
        }
    }

    public static Specification<Tweet> withTypes(List<TweetType> types) {
        if (isNull(types)) {
            return null;
        } else {
            return (root, query, cb) -> root.get("type").in(types);
        }
    }

    public static Specification<Tweet> withSince(OffsetDateTime since) {
        if (isNull(since)) {
            return null;
        } else {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("createdAt"), since);
        }
    }

    public static Specification<Tweet> withUntil(OffsetDateTime until) {
        if (isNull(until)) {
            return null;
        } else {
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), until);
        }
    }


}
