package cn.landdt.service.impl;

import cn.landdt.entity.UserPointGetEntity;
import cn.landdt.mapper.UserPointGetMapper;
import cn.landdt.service.UserPointGetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserPointGetServiceImpl extends ServiceImpl<UserPointGetMapper, UserPointGetEntity> implements UserPointGetService {
}
