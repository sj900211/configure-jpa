package run.freshr.common.utils;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.springframework.data.domain.PageRequest.of;
import static run.freshr.common.utils.StringUtil.uuidWithoutHyphen;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import io.jsonwebtoken.Claims;
import java.util.HashMap;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import run.freshr.common.data.CursorClaimData;
import run.freshr.common.data.CursorData;
import run.freshr.common.exceptions.ParameterException;
import run.freshr.common.extensions.enumerations.SearchEnumExtension;
import run.freshr.common.extensions.request.SearchExtension;
import run.freshr.common.functional.CursorPagingFunctional;
import run.freshr.common.functional.PagingFunctional;
import run.freshr.common.functional.SearchEnumFunctional;
import run.freshr.common.functional.SearchKeywordFunctional;

/**
 * QueryDsl 공통 기능
 *
 * @author FreshR
 * @apiNote QueryDsl 공통 기능 정의
 * @since 2024. 3. 28. 오전 9:41:09
 */
@Component
public class QueryUtilAware {

  /**
   * 자연어 검색
   *
   * @param word  검색어
   * @param paths 검색 대상의 QueryDsl Path 목록
   * @return boolean builder
   * @apiNote 자연어 검색
   * @author FreshR
   * @since 2024. 3. 28. 오전 9:41:09
   */
  public static BooleanBuilder searchKeyword(String word, List<StringPath> paths) {
    final SearchKeywordFunctional SEARCH_KEYWORD_FUNCTIONAL = (functionalWord, functionalPaths) -> {
      BooleanBuilder booleanBuilder = new BooleanBuilder();

      functionalPaths.forEach(path -> booleanBuilder.or(path.containsIgnoreCase(functionalWord)));

      return booleanBuilder;
    };

    return SEARCH_KEYWORD_FUNCTIONAL.search(word, paths);
  }

  /**
   * SearchEnumExtension 을 사용한 자연어 검색
   *
   * @param <E>         SearchEnumExtension 상속받은 검색 유형 Enum
   * @param word        검색어
   * @param enumeration SearchEnumExtension 상속받은 검색 유형 Enum
   * @return boolean builder
   * @apiNote SearchEnumExtension 을 사용한 자연어 검색
   * @author FreshR
   * @since 2024. 3. 28. 오전 9:41:09
   */
  public static <E extends SearchEnumExtension> BooleanBuilder searchEnum(
      String word, E enumeration) {
    final SearchEnumFunctional<E> SEARCH_ENUM_FUNCTIONAL =
        (functionalWord, functionalEnumeration) -> functionalEnumeration.search(functionalWord);

    return SEARCH_ENUM_FUNCTIONAL.search(word, enumeration);
  }

  /**
   * 페이징 처리
   *
   * @param <E>    Entity
   * @param <Q>    Entity 의 Path
   * @param <S>    SearchExtension 을 상속받은 Get Parameter VO
   * @param query  JPA Query
   * @param path   검색 대상의 QueryDsl Path
   * @param search SearchExtension 을 상속받은 Get Parameter VO
   * @return page
   * @apiNote 페이징 처리
   * @author FreshR
   * @since 2024. 3. 28. 오전 9:41:09
   */
  public static <E, Q extends EntityPathBase<E>, S extends SearchExtension<?>> Page<E> paging(
      JPAQuery<E> query, Q path, S search) {
    return paging(query, path, search, null);
  }

  /**
   * 페이징 처리
   *
   * @param <E>       Entity
   * @param <Q>       Entity 의 Path
   * @param <S>       SearchExtension 을 상속받은 Get Parameter VO
   * @param query     JPA Query
   * @param path      검색 대상의 QueryDsl Path
   * @param search    SearchExtension 을 상속받은 Get Parameter VO
   * @param orderList 정렬 정보 목록
   * @return page
   * @apiNote 페이징 처리
   * @author FreshR
   * @since 2024. 3. 28. 오전 9:41:09
   */
  public static <E, Q extends EntityPathBase<E>, S extends SearchExtension<?>> Page<E> paging(
      JPAQuery<E> query, Q path, S search, List<OrderSpecifier<?>> orderList) {
    final PagingFunctional<E, Q, S> PAGING_FUNCTIONAL = (functionalQuery, functionalPath,
        functionalSearch, functionalOrders) -> {
      PageRequest pageRequest = of(functionalSearch.getPage() - 1,
          functionalSearch.getSize());
      Long totalCount = functionalQuery.select(Wildcard.count).fetchOne();

      functionalQuery.select(functionalPath).offset(pageRequest.getOffset())
          .limit(pageRequest.getPageSize());

      if (!isNull(functionalOrders) && !functionalOrders.isEmpty()) {
        functionalQuery.orderBy(functionalOrders.toArray(new OrderSpecifier<?>[0]));
      }

      List<E> result = functionalQuery.fetch();

      return new PageImpl<>(result, pageRequest, ofNullable(totalCount).orElse(0L));
    };

    return PAGING_FUNCTIONAL.paging(query, path, search, orderList);
  }

  /**
   * Cursor 페이징 처리
   *
   * @param <E>        Entity
   * @param <Q>        Entity 의 Path
   * @param query      JPA Query
   * @param path       검색 대상의 QueryDsl Path
   * @param size       조회할 데이터 수
   * @param predicates 검색 조건 - Where
   * @param orders     정렬 조건 - Order by
   * @return cursor data
   * @apiNote Cursor 페이징 처리
   * @author FreshR
   * @since 2024. 3. 28. 오전 9:41:09
   */
  public static <E, Q extends EntityPathBase<E>> CursorData<E> cursor(JPAQuery<E> query,
      Q path, Integer size, List<Predicate> predicates, List<OrderSpecifier<?>> orders) {
    return cursor(query, path, 0, size, predicates, orders);
  }

  /**
   * Cursor 페이징 처리
   *
   * @param <E>       Entity
   * @param <Q>       Entity 의 Path
   * @param query     JPA Query
   * @param path      검색 대상의 QueryDsl Path
   * @param pageToken 페이지 토큰
   * @return cursor data
   * @apiNote Cursor 페이징 처리
   * @author FreshR
   * @since 2024. 3. 28. 오전 9:41:09
   */
  public static <E, Q extends EntityPathBase<E>> CursorData<E> cursor(JPAQuery<E> query,
      Q path, String pageToken) {
    if (!JwtUtil.validateExpired(pageToken)) {
      throw new ParameterException("wrong page token");
    }

    Claims claims = JwtUtil.get(pageToken);
    Object claimsData = claims.get("data");

    if (isNull(claimsData)) {
      throw new ParameterException("not found page token data");
    }

    CursorClaimData data = MapperUtil.map(claimsData, CursorClaimData.class);

    Integer page = data.getPage();
    Integer size = data.getSize();
    List<Predicate> predicates = data.getPredicates();
    List<OrderSpecifier<?>> orders = data.getOrders();

    return cursor(query, path, page, size, predicates, orders);
  }

  /**
   * Cursor 페이징 처리
   *
   * @param <E>        Entity
   * @param <Q>        Entity 의 Path
   * @param query      JPA Query
   * @param path       검색 대상의 QueryDsl Path
   * @param page       조회할 페이지 수
   * @param size       조회할 데이터 수
   * @param predicates 검색 조건 - Where
   * @param orders     정렬 조건 - Order by
   * @return cursor data
   * @apiNote Cursor 페이징 처리
   * @author FreshR
   * @since 2024. 3. 28. 오전 9:41:09
   */
  public static <E, Q extends EntityPathBase<E>> CursorData<E> cursor(JPAQuery<E> query,
      Q path, Integer page, Integer size, List<Predicate> predicates,
      List<OrderSpecifier<?>> orders) {

    final CursorPagingFunctional<E, Q> CURSOR_PAGING_FUNCTIONAL = (functionalQuery, functionalPath,
        functionalPage, functionalSize, functionalWheres, functionalOrders) -> {
      PageRequest pageRequest = of(functionalPage, functionalSize);

      functionalQuery.where(functionalWheres.toArray(new Predicate[0]));

      Long totalCount = functionalQuery.select(Wildcard.count).fetchOne();

      functionalQuery.select(functionalPath)
          .offset(pageRequest.getOffset()).limit(pageRequest.getPageSize())
          .orderBy(functionalOrders.toArray(new OrderSpecifier<?>[0]));

      List<E> result = functionalQuery.fetch();

      CursorClaimData nextData = CursorClaimData.builder()
          .page(functionalPage + 1)
          .size(functionalSize)
          .predicates(functionalWheres)
          .orders(functionalOrders)
          .build();
      String nextPageToken = JwtUtil.generate(uuidWithoutHyphen(), new HashMap<>() {{
        put("data", nextData);
      }});

      return new CursorData<>(result, pageRequest,
          ofNullable(totalCount).orElse(0L), nextPageToken);
    };

    return CURSOR_PAGING_FUNCTIONAL.paging(query, path, page, size, predicates, orders);
  }

}
