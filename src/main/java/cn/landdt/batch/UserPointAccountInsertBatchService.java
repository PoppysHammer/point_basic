package cn.landdt.batch;

import cn.landdt.entity.UserPointAccountEntity;
import cn.landdt.service.UserPointAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserPointAccountInsertBatchService extends BaseBatchService<Boolean, UserPointAccountEntity> {

    @Resource
    private UserPointAccountService userPointAccountService;

    @Override
    public Map<UserPointAccountEntity, List<Boolean>> batchProcess(List<BaseBatchService<Boolean, UserPointAccountEntity>.Request> requests) {
        //所有账户
        List<UserPointAccountEntity> userPointAccountEntities = requests.stream().map(Request::getRequestParam).toList();
        //批量查询
        Boolean result = userPointAccountService.saveBatch(userPointAccountEntities);
        return userPointAccountEntities.stream().collect(Collectors.toMap(a -> a, a -> List.of(result)));

    }

    @Override
    public String getKey() {
        return "批量插入请求";
    }
}
