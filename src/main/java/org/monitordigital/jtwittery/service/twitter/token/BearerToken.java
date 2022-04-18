package org.monitordigital.jtwittery.service.twitter.token;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@Getter
@Data
public class BearerToken {

    private String token;
    @Setter
    private Instant firstCall;

}