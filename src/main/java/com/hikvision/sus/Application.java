package com.hikvision.sus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.system.ApplicationHome;

@SpringBootApplication
public class Application {

    public final static String applicationHome = new ApplicationHome(Application.class).getSource().getParentFile().getPath();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
