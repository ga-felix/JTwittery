package org.monitordigital.jtwittery.service.twitter.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Getter
@Data
public class BearerToken {

    private String token;
    @Setter
    private OffsetDateTime firstCall;

}