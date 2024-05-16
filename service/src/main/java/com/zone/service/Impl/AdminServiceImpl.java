package com.zone.service.Impl;

import com.zone.dto.LoginDTO;
import com.zone.mapper.AdminMapper;
import com.zone.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;


    @Override
    public Integer login(LoginDTO loginDTO) {
        // 获取账号密码
        String password = loginDTO.getPassword();
        String name = loginDTO.getName();

        // 进行查询管理员 是否存在
        Integer admin = adminMapper.Login(password,name);

        if (admin == -1 ) {
            // 未查询到该管理员
            log.info("未查询到该管理员");
            return -1;
        }
        // 查询到该管理员
        return admin;
    }


    @Override
    public void deleteById(String id) {
        // TODO 如果要删除的用户的当前在线
        //  需要把他的JWT令牌给删除
        //  给用户强制下线
        //   然后再进行删除用户
        adminMapper.deleteById(id);
    }
}
