package org.monitordigital.jtwittery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JTwitteryApplication {

    public static void main(String[] args) {
        SpringApplication.run(JTwitteryApplication.class, args);
    }

}
