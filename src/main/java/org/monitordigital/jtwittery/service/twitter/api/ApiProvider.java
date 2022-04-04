package org.monitordigital.jtwittery.service.twitter.api;

import com.twitter.clientlib.api.TwitterApi;
import org.springframework.stereotype.Service;

@Service
public interface ApiProvider {

    TwitterApi getApi();

}