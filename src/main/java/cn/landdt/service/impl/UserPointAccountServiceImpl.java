package cn.landdt.service.impl;

import cn.landdt.entity.UserPointAccountEntity;
import cn.landdt.mapper.UserPointAccountMapper;
import cn.landdt.service.UserPointAccountService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserPointAccountServiceImpl extends ServiceImpl<UserPointAccountMapper, UserPointAccountEntity> implements UserPointAccountService {

    @Resource
    private UserPointAccountMapper userPointAccountMapper;

    @Override
    public Map<String, UserPointAccountEntity> findOneBatch(List<UserPointAccountWrapBatchService.Request> requests) {
        //所有账户id
        List<Long> accountIds = requests.stream().map(UserPointAccountWrapBatchService.Request::getId).toList();
        //批量查询
        List<UserPointAccountEntity> userPointAccountEntities = userPointAccountMapper.selectList(new LambdaQueryWrapper<UserPointAccountEntity>().in(UserPointAccountEntity::getId, accountIds));
        Map<Long, List<UserPointAccountEntity>> userPointAccountEntityMap = userPointAccountEntities.stream().collect(Collectors.groupingBy(UserPointAccountEntity::getId));

        HashMap<String, UserPointAccountEntity> result = new HashMap<>();
        requests.forEach(request -> {
            List<UserPointAccountEntity> userPointAccountEntityList = userPointAccountEntityMap.get(request.getId());
            if (CollectionUtils.isEmpty(userPointAccountEntityList)) {
                result.put(request.getRequestId(), null);
            } else {
                result.put(request.getRequestId(), userPointAccountEntityList.get(0));
            }
        });

        return result;
    }
}
