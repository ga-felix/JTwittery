package org.monitordigital.jtwittery.service.twitter.token;

public interface BearerTokenProvider {

    BearerToken getValidToken();
    void returnExpiredToken(BearerToken token);

}