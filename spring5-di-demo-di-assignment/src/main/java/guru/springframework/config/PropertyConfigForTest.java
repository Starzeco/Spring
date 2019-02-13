package guru.springframework.config;


import guru.springframework.examplebeans.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertyConfigForTest {

    @Value("${guru.pupka}")          // W application.properties
    String pupka;

    @Bean
    public Test test(){
        Test test=new Test();
        test.setPupka(pupka);
        return test;
    }
}
