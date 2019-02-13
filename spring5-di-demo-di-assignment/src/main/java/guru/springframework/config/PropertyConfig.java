package guru.springframework.config;

import guru.springframework.examplebeans.FakeDataSource;
import guru.springframework.examplebeans.FakeJmsBroker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
//@PropertySource({"classpath:datasource.properties", "classpath:jms.properties"})    JEDNA OPCJA
@PropertySources({
        @PropertySource("classpath:datasource.properties"),                 // Druga opcja
        @PropertySource("classpath:jms.properties")
})
public class PropertyConfig {

    @Autowired
    Environment envy;           // Do zmieniania tak jakby ( Jak chce uruchomić to rozwijam to i klikam edit configuration i tam jest enviroment variables

    @Value("${guru.username}")
    String name;

    @Value("${guru.password}")
    String password;

    @Value("${guru.dburl}")
    String url;

    @Value("${guru.jms.username}")
    String jmsName;

    @Value("${guru.jms.password}")
    String jmsPassword;

    @Value("${guru.jms.url}")
    String jmsUrl;

    @Bean
    public FakeDataSource fakeDataSource(){
        FakeDataSource fakeDataSource=new FakeDataSource();
        fakeDataSource.setName(name);
        fakeDataSource.setPassword(envy.getProperty("PASSWORD"));
        fakeDataSource.setUrl(url);
        return fakeDataSource;
    }
    @Bean
    public FakeJmsBroker fakeJmsBroker(){
        FakeJmsBroker fakeJmsBroker=new FakeJmsBroker();
        fakeJmsBroker.setUsername(jmsName);
        fakeJmsBroker.setPassword(jmsPassword);
        fakeJmsBroker.setUrl(jmsUrl);
        return fakeJmsBroker;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer=new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }


}
