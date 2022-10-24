package com.wealhome.infra.client.springboot;

import com.wealhome.businesslogic.models.DateProvider;
import com.wealhome.businesslogic.models.DeterministicDateProvider;
import com.wealhome.businesslogic.repositories.CallForFundsRepository;
import com.wealhome.businesslogic.repositories.CondominiumRepository;
import com.wealhome.infra.repositories.jpa.JpaCallForFundsRepository;
import com.wealhome.infra.repositories.jpa.JpaCondominiumRepository;
import com.wealhome.infra.repositories.jpa.SpringCallForFundsRepository;
import com.wealhome.infra.repositories.jpa.SpringCondominiumRepository;
import com.wealhome.businesslogic.usecases.LaunchCallForFundsCommandHandler;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "com.wealhome")
@EntityScan("com.wealhome.businesslogic.models")
@EnableJpaRepositories("com.wealhome.infra.repositories")
public class ServicesConfiguration {

    @Bean
    public LaunchCallForFundsCommandHandler launchCallForFundsCommandHandler(CallForFundsRepository callForFundsRepository,
                                                                             CondominiumRepository condominiumRepository,
                                                                             DateProvider dateProvider) {
        return new LaunchCallForFundsCommandHandler(condominiumRepository, callForFundsRepository, dateProvider);
    }

    @Bean
    public CondominiumRepository condominiumRepository(SpringCondominiumRepository springCondominiumRepository) {
        return new JpaCondominiumRepository(springCondominiumRepository);
    }

    @Bean
    public CallForFundsRepository callForFundsRepository(SpringCallForFundsRepository springCallForFundsRepository) {
        return new JpaCallForFundsRepository(springCallForFundsRepository);
    }

    @Bean
    public DateProvider dateProvider() {
        return new DeterministicDateProvider();
    }

}
