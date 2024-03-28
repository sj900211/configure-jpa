package run.freshr.common.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("**.**.domain.**.repository.jpa")
public class JpaConfiguration {

}
