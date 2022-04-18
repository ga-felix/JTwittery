package org.monitordigital.jtwittery.service.twitter.token;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

@Slf4j
@Service
public class BearerTokenProviderImp implements BearerTokenProvider {

    private final List<BearerToken> bearerTokens;
    private final Long refreshWindow = 15L * 60L * 1000L;

    public BearerTokenProviderImp(@Value("${twitter.bearerTokens}") String[] environmentTokens) {
        bearerTokens = Arrays.stream(environmentTokens)
                .map(
                        token -> new BearerToken(token, null))
                .collect(Collectors.toList());
        log.info(format("%d bearer tokens were found.", bearerTokens.size()));
    }

    public synchronized BearerToken getValidToken() {
        if(bearerTokens.isEmpty()) {
            try {
                log.info(format("%s is waiting for tokens.", currentThread().getId()));
                this.wait();
            } catch (InterruptedException e) {
                log.info(format("%s was released and will get a new token.", currentThread().getId()));
                return getToken();
            }
        }
        return getToken();
    }

    private BearerToken getToken() {
        var bearerToken = bearerTokens.get(0);
        bearerTokens.remove(0);
        log.info(format("%d bearer token was requested.", bearerToken.hashCode()));
        return bearerToken;
    }

    public synchronized void returnExpiredToken(BearerToken token) {
        log.info(format("Waiting %d bearer token to refresh.", token.hashCode()));
        waitUntilRefresh(token);
        bearerTokens.add(token);
        this.notify();
        log.info(format("Bearer token %d was returned.", token.hashCode()));
    }

    private void waitUntilRefresh(BearerToken token) {
        try {
            var milis = Duration.between(token.getFirstCall(), Instant.now()).toMillis();
            log.info(format("Sleeping for %d ms so the token can refresh.", refreshWindow - milis));
            sleep(refreshWindow - milis);
        } catch (InterruptedException exception) {
            var timeLeft = Duration.between(token.getFirstCall(), Instant.now()).toMillis();
            log.warn(format("Bearer token refreshing interrupted with %d ms left.",
                    refreshWindow - timeLeft));
        }
        log.info(format("%d bearer token is available.", token.hashCode()));
    }

}