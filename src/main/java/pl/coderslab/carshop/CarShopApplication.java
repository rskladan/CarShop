package pl.coderslab.carshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.validation.Validator;
import java.util.Locale;

@SpringBootApplication
public class CarShopApplication {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean(name="localeResolver")
    public LocaleContextResolver getLocaleContextResolver() {
        return new SessionLocaleResolver();
    }

    public static void main(String[] args) {
        SpringApplication.run(CarShopApplication.class, args);
    }

}
