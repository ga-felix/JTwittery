package org.monitordigital.jtwittery.service.twitter.downloader.mounter;

import com.twitter.clientlib.ApiException;
import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.TweetSearchResponse;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

@Builder
public class FullArchiveQuery {

    private OffsetDateTime since;
    private OffsetDateTime until;
    private String sinceId;
    private String untilId;
    private Integer maxResults;
    private String sortOrder;
    @Builder.Default private Set<String> expansions = new HashSet<>(asList(
            "author_id",
            "in_reply_to_user_id",
            "referenced_tweets.id",
            "referenced_tweets.id.author_id"));
    @Builder.Default private Set<String> tweetFields = new HashSet<>(asList(
            "id",
            "text",
            "author_id",
            "created_at",
            "public_metrics",
            "referenced_tweets"));
    private Set<String> userFields;
    private Set<String> mediaFields;
    private Set<String> placeFields;
    private Set<String> pollFields;

    public TweetSearchResponse call(String query,
                                    TwitterApi api,
                                    String nextToken,
                                    String paginationToken) throws ApiException {
        return api.tweets().tweetsFullarchiveSearch(
                query,
                since,
                until,
                sinceId,
                untilId,
                maxResults,
                sortOrder,
                nextToken,
                paginationToken,
                expansions,
                tweetFields,
                userFields,
                mediaFields,
                placeFields,
                pollFields);
    }
}
