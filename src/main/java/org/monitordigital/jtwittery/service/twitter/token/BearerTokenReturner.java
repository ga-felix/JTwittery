package org.monitordigital.jtwittery.service.twitter.token;

import com.twitter.clientlib.ApiException;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.monitordigital.jtwittery.service.twitter.downloader.TwitterDownloader;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class BearerTokenReturner {

    private BearerTokenProvider bearerTokenProvider;

    @Pointcut("execution(* org.monitordigital.jtwittery.service.twitter.downloader.TwitterDownloader.*(..))")
    public void twitterDownloader() {}

    @AfterThrowing(value = "twitterDownloader()", throwing = "exception")
    public void refreshToken(JoinPoint joinPoint, ApiException exception) {
        switch (exception.getCode()) {
            case 419 -> returnBearerToken(getDownloader(joinPoint));
        }
    }

    private TwitterDownloader getDownloader(JoinPoint joinPoint) {
        return (TwitterDownloader) joinPoint.getTarget();
    }

    private void returnBearerToken(TwitterDownloader downloader) {
        bearerTokenProvider.returnExpiredToken(downloader.getToken());
    }

}