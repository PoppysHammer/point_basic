package cn.landdt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("user_point_get")
@AllArgsConstructor
@NoArgsConstructor
public class UserPointGetEntity implements Serializable {

    private Long id;

    private BigDecimal integral;

    private Long pointTypeId;

    private Long accountId;

    private BigDecimal integralRemain;

    private BigDecimal integralExpired;

    private Integer integralExpireStatus;

    private Long recordId;

    private LocalDateTime recordTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
