package com.example.store.configuration;

import com.example.store.util.CommandAsker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreConfiguration {

    @Bean
    public CommandAsker commandAsker() {
        return new CommandAsker(System.in, System.out);
    }

}
