package org.monitordigital.jtwittery.service.twitter.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.*;
import static java.lang.Thread.sleep;

@Slf4j
@Service
public class BearerTokenProviderImp implements BearerTokenProvider {

    private List<BearerToken> tokens = new LinkedList<>();
    private final long refreshWindow = 15l; // Twitter api token refreshing window is 15 min

    public BearerTokenProviderImp() {
        var stringTokens = System.getenv("TWITTER_BEARER_TOKENS").split(",");
        for (String stringToken : stringTokens) {
            tokens.add(new BearerToken(stringToken, null));
        }
        log.info(format("%d bearer tokens were found.", tokens.size()));
    }

    private BearerToken pop(int index) {
        var token = tokens.get(index);
        tokens.remove(index);
        log.info(format("%d bearer token was requested.", token.hashCode()));
        return token;
    }

    public BearerToken getValidToken() {
        return pop(0);
    }

    private boolean isRefreshed(BearerToken token) {
        return Duration.between(token.getFirstCall(), OffsetDateTime.now()).toMinutes() >= refreshWindow;
    }

    private void waitUntilRefresh(BearerToken token) {
        if (!isRefreshed(token)) {
            var sleepDate = OffsetDateTime.now();
            try {
                var milis = Duration.between(token.getFirstCall(), sleepDate).toMillis();
                log.info(format("Sleeping for %d ms so the token can refresh.", milis));
                sleep(milis);
            } catch (InterruptedException exception) {
                var timeLeft = Duration.between(sleepDate, OffsetDateTime.now()).toMillis();
                log.warn(format("Bearer token refreshing interrupted with %d ms left.", timeLeft));
            }
            log.info(format("%d bearer token is available.", token.hashCode()));
        }
    }

    public void returnToken(BearerToken token) {
        log.info(format("Waiting %d bearer token to refresh.", token.hashCode()));
        waitUntilRefresh(token);
        tokens.add(token);
        log.info(format("Bearer token %d was returned.", token.hashCode()));
    }

}