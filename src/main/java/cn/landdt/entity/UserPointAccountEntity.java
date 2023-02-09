package cn.landdt.entity;

import cn.landdt.config.LocalDateTimeTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user_point_account")
public class UserPointAccountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String phone;

    private Long userId;

    private Integer userType;

//    private Long pointTypeId;

    private BigDecimal integral;

    @TableField(typeHandler = LocalDateTimeTypeHandler.class)
    private LocalDateTime createTime;
    @TableField(typeHandler = LocalDateTimeTypeHandler.class)
    private LocalDateTime updateTime;

}
