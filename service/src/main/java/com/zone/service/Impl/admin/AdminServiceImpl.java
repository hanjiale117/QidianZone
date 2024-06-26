package com.zone.service.Impl.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zone.context.BaseContext;
import com.zone.dto.AdminDTO;
import com.zone.dto.LoginDTO;
import com.zone.dto.PageBean;
import com.zone.entity.User;
import com.zone.mapper.AdminMapper;
import com.zone.mapper.UserMapper;
import com.zone.result.PageResult;
import com.zone.service.AdminService;
import com.zone.vo.UserManageVO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员服务实现
 */

@Service
@Slf4j
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    private final UserMapper userMapper;

    /**
     * 登录
     */
    @Override
    public Integer login(LoginDTO loginDTO) {


        // 2.进行查询管理员 是否存在
        Integer admin = adminMapper.Login(loginDTO);
        if (admin == null) {
            // 未查询到该管理员
            log.info("未查询到该管理员");
            return -1;
        }
        // 查询到该管理员
        return admin;
    }

    /**
     * 删除用户
     */
    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteByUserId(id);
    }

    /**
     * 修改管理员 账户/密码
     */
    @Override
    public void updateAdmin(AdminDTO adminDTO) {
        // 修改管理员账号
        // 获取ID
        adminDTO.setId(BaseContext.getCurrentId());
        adminMapper.updateAdmin(adminDTO);
    }

    /**
     * 查询用户
     */
    @Override
    public PageResult listUser(PageBean pageBean) {
        PageHelper.startPage(pageBean.getPageNum(),pageBean.getPageSize());
        // 1.获取用户数据集合
        Page<User> userList = userMapper.listUser();

        // 2.拷贝数据
        List<UserManageVO> userManageVOS = new ArrayList<>();
        userList.forEach(user -> {
            // 2.1创建VO对象
            UserManageVO userManageVO = new UserManageVO();
            BeanUtils.copyProperties(user, userManageVO);
            // 2.2存放在新的集合中
            userManageVOS.add(userManageVO);
        });

        // 3.返回集合
        return new PageResult(userList.getTotal(),userManageVOS);
    }
}
