package org.monitordigital.jtwittery.service.twitter.api;

import com.twitter.clientlib.TwitterCredentialsBearer;
import com.twitter.clientlib.api.TwitterApi;
import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.service.twitter.token.BearerTokenProvider;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class ApiProviderImp implements ApiProvider {

    private BearerTokenProvider bearerTokenProvider;

    public TwitterApi getApi() {
        var token = bearerTokenProvider.getValidToken();
        token.setFirstCall(OffsetDateTime.now());
        var api = new TwitterApi();
        api.setTwitterCredentials(new TwitterCredentialsBearer(token.getToken()));
        return api;
    }

}