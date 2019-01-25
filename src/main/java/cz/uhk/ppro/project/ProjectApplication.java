package cz.uhk.ppro.project;

//import cz.uhk.ppro.project.services.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
/*
@EnableConfigurationProperties({
        FileStorageProperties.class
})
*/

public class ProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }
}

