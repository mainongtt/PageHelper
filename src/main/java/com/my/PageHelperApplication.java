package com.my;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEncryptableProperties
@SpringBootApplication
public class PageHelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(PageHelperApplication.class, args);
    }
}
