package org.monitordigital.jtwittery.service.form;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateUserForm {

    @NotNull
    private Long id;
    @NotNull
    private String name;

}
