package cn.landdt.service;

import cn.landdt.entity.UserPointAccountEntity;
import cn.landdt.service.impl.UserPointAccountWrapBatchService;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface UserPointAccountService extends IService<UserPointAccountEntity> {

    //合并单查请求
    Map<String, UserPointAccountEntity> findOneBatch(List<UserPointAccountWrapBatchService.Request> requests);
}
