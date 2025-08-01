package org.vinhduyle.ownerpetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
//@EnableMongoRepositories // Not strictly necessary if repos are in subpackage of main app, but good practice
public class OwnerpetapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(OwnerpetapiApplication.class, args);
    }

}
