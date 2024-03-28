package run.freshr.common.extensions.entity;

import static run.freshr.common.configurations.DefaultColumnConfiguration.FALSE;
import static run.freshr.common.configurations.DefaultColumnConfiguration.TRUE;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 논리 삭제 MappedSuperclass
 *
 * @author FreshR
 * @apiNote 논리 삭제 정책을 가진 MappedSuperclass
 * @since 2024. 3. 27. 오후 1:46:56
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class EntityLogicalExtension {

  @Transient
  @ColumnDefault(TRUE)
  @Comment("사용 여부")
  protected Boolean useFlag;

  @Transient
  @ColumnDefault(FALSE)
  @Comment("삭제 여부")
  protected Boolean deleteFlag;

  @Transient
  @CreatedDate
  @Comment("등록 날짜 시간")
  protected LocalDateTime createAt;

  @Transient
  @LastModifiedDate
  @Comment("마지막 수정 날짜 시간")
  protected LocalDateTime updateAt;

  /**
   * 삭제 처리
   *
   * @apiNote 삭제 처리
   * @author FreshR
   * @since 2024. 3. 27. 오후 1:46:56
   */
  protected void remove() {
    this.useFlag = false;
    this.deleteFlag = true;
  }

}
