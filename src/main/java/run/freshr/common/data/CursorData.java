package run.freshr.common.data;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.List;
import java.util.function.Function;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

/**
 * Cursor 기반의 response 모델 정의
 *
 * @param <T> Content 데이터 유형
 * @author FreshR
 * @apiNote Cursor 기능을 사용할 때 Page 객체에 Cursor 정보를 추가
 * @since 2024. 3. 27. 오후 1:46:56
 */
@Getter
public class CursorData<T> extends PageImpl<T> implements Page<T> {

  /**
   * Next Page token
   *
   * @apiNote 다음 페이지 조회 토큰<br>
   *          다음 페이지를 조회할 때 page 대신 사용<br>
   *          page 와 다르게 중복 데이터가 발생하지 않음
   * @since 2024. 3. 27. 오후 1:46:56
   */
  private final String nextPageToken;

  @JsonCreator
  public CursorData(List<T> content, Pageable pageable, long total, String nextPageToken) {
    super(content, pageable, total);

    if (!isLast() && !isEmpty()) {
      this.nextPageToken = nextPageToken;
    } else {
      this.nextPageToken = null;
    }
  }

  @Override
  public <U> CursorData<U> map(Function<? super T, ? extends U> converter) {
    return new CursorData<>(getConvertedContent(converter), getPageable(),
        getTotalElements(), nextPageToken);
  }

}
