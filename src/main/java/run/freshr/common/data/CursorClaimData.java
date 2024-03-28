package run.freshr.common.data;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cursor 데이터 모델
 *
 * @author FreshR
 * @apiNote Cursor 데이터 모델<br>
 *          토큰에 저장될 Cursor 데이터
 * @since 2024. 3. 28. 오전 9:53:14
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CursorClaimData {

  /**
   * 조회할 페이지 수
   *
   * @apiNote 조회할 페이지 수
   * @since 2024. 3. 28. 오전 9:53:50
   */
  private Integer page;
  /**
   * 조회할 데이터 수
   *
   * @apiNote 조회할 데이터 수
   * @since 2024. 3. 28. 오전 9:53:53
   */
  private Integer size;
  /**
   * 조회 조건 목록
   *
   * @apiNote 조회 조건 목록
   * @since 2024. 3. 28. 오전 9:53:55
   */
  private List<Predicate> predicates;
  /**
   * 정렬 목록
   *
   * @apiNote 정렬 목록
   * @since 2024. 3. 28. 오전 9:53:56
   */
  private List<OrderSpecifier<?>> orders;

}
