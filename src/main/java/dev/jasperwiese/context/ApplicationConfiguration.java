package dev.jasperwiese.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.jasperwiese.ApplicationLauncher;
import dev.jasperwiese.service.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@Configuration
@PropertySource("classpath:/application.properties")
@EnableWebMvc
public class ApplicationConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    public TransactionService transactionService(@Value("${bank.slogan}") String bankSlogan) {
        return new TransactionService(bankSlogan);
    }
}
