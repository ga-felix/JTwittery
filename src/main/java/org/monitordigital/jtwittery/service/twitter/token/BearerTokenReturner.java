package org.monitordigital.jtwittery.service.twitter.token;

import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@AllArgsConstructor
public class BearerTokenReturner {

    private BearerTokenProvider bearerTokenProvider;

    @Pointcut("execution(* org.monitordigital.jtwittery.service.twitter.downloader.TwitterDownloader.*(..))")
    public void twitterDownloaders() {}

    @AfterThrowing(value = "twitterDownloaders()", throwing = "exception")
    public void refreshToken(JoinPoint joinPoint, Exception exception) {
        // TODO Fazer a devoluçao do token
    }
}