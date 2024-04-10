package run.freshr.common.functional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import java.util.List;

/**
 * 자연어 검색 functional interface
 *
 * @author FreshR
 * @apiNote 자연어 검색 함수형 인터페이스 정의
 * @since 2024. 3. 27. 오후 1:46:56
 */
@FunctionalInterface
public interface SearchKeywordFunctional {

  /**
   * 자연어 검색
   *
   * @param word  검색어
   * @param paths 검색 대상의 QueryDsl Path 목록
   * @return boolean builder
   * @apiNote 자연어 검색
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  BooleanBuilder search(String word, List<StringPath> paths);

}
