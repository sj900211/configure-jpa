package run.freshr.common.configurations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * QueryDsl 설정
 *
 * @author FreshR
 * @apiNote JPAQuery 를 프로젝트 전역에서 주입받을 수 있도록 설정
 * @since 2024. 3. 27. 오후 1:46:56
 */
@Getter
@Configuration
public class QueryDslConfiguration {

  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory jpaQueryFactory() {
    return new JPAQueryFactory(entityManager);
  }

}
