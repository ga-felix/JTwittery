package org.monitordigital.jtwittery.service.form;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private Long id;
    private String name;

}