package org.monitordigital.jtwittery.service.twitter.token;

import org.springframework.stereotype.Service;

@Service
public interface BearerTokenProvider {

    BearerToken getValidToken();
    void returnExpiredToken(BearerToken token);

}