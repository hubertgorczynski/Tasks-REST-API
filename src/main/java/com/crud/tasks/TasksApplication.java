package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//public class TasksApplication extends SpringBootServletInitializer { - committed for Heroku
public class TasksApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }

/*  @Override - committed for Heroku
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TasksApplication.class);
    }
 */

}
