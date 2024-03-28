package run.freshr.common.functional;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import org.springframework.data.domain.Page;
import run.freshr.common.extensions.request.SearchExtension;

/**
 * 페이징 functional interface
 *
 * @param <E> Entity
 * @param <Q> type parameter
 * @param <S> SearchExtension 을 상속받은 Get Parameter VO
 * @author FreshR
 * @apiNote 페이징 함수형 인터페이스 정의
 * @since 2024. 3. 27. 오후 1:46:56
 */
@FunctionalInterface
public interface PagingFunctional<E, Q extends EntityPathBase<E>, S extends SearchExtension<?>> {

  /**
   * 페이징 처리
   *
   * @param query  JPA Query
   * @param path   path
   * @param search SearchExtension 을 상속받은 Get Parameter VO
   * @param orders orders
   * @return page
   * @apiNote 데이터 페이징 처리
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  Page<E> paging(JPAQuery<E> query, Q path, S search, List<OrderSpecifier<?>> orders);

}
