package cn.landdt.batch;

import cn.landdt.entity.UserPointAccountEntity;
import cn.landdt.service.UserPointAccountService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserPointAccountBatchService extends BaseBatchService<UserPointAccountEntity,Long> {

    @Resource
    private UserPointAccountService userPointAccountService;

    @Override
    public Map<Long,List<UserPointAccountEntity>> batchProcess(List<Request> requestList) {
        //所有账户id
        List<Long> accountIds = requestList.stream().map(Request::getRequestParam).toList();
        //批量查询
        List<UserPointAccountEntity> userPointAccountEntities = userPointAccountService.list(new LambdaQueryWrapper<UserPointAccountEntity>().in(UserPointAccountEntity::getId, accountIds));
        return userPointAccountEntities.stream().collect(Collectors.groupingBy(UserPointAccountEntity::getId));
    }

    @Override
    public String getKey() {
        return "积分账户单查";
    }
}
