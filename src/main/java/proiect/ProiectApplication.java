package proiect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"proiect.domain"})
public class ProiectApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProiectApplication.class,args);
    }
}
