package com.zone.controller.admin;


import com.zone.constant.JwtClaimsConstant;
import com.zone.dto.AdminDTO;
import com.zone.dto.LoginDTO;
import com.zone.dto.PageBean;
import com.zone.properties.JwtProperties;
import com.zone.result.PageResult;
import com.zone.result.Result;
import com.zone.service.AdminService;
import com.zone.utils.JwtUtil;
import com.zone.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员后台管理
 */


@Slf4j
@RestController
//@RequestMapping("/admin")
@RequiredArgsConstructor // 动态注入Bean注解
public class AdminBackManageController {

    private final AdminService adminService;

    private final JwtProperties jwtProperties;

    @GetMapping("/login")
    public Result<Object> login(@RequestBody LoginDTO loginDTO) {
        // 1.登录
        log.info("登录:{}", loginDTO);

        // 2.管理员登录
        Integer id = adminService.login(loginDTO);
        if (id == null || id == -1) {
            return Result.error("登录失败");
        }
        // 3.登录成功
        log.info("管理员:{}登录成功", loginDTO.getAccount());
        // 4.生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.ADMIN_ID, id); // 设置管理员id
        String token =
                JwtUtil.createJWT(jwtProperties.getUserSecretKey(), // 密钥
                jwtProperties.getUserTtl(), claims); // 设置过期时间

        // 5.封装VO
        LoginVO userLoginVO = LoginVO.builder()
                .id(id)
                .token(token)
                .build();
        log.info("userLoginVO:{}",userLoginVO);
        return Result.success(userLoginVO);
    }

    /**
     * 修改管理员账号/密码
     */
    @PutMapping("/updateAdmin")
    public Result<Object> updateAdmin(@RequestBody AdminDTO adminDTO) {
        log.info("修改 管理员账号/密码:{}", adminDTO.toString());
        adminService.updateAdmin(adminDTO);

        return Result.success("修改成功");
    }

    /**
     * 分页查询用户列表
     */
    @GetMapping("/list")
    public Result<PageResult> listUser(@RequestBody PageBean pageBean) {
        log.info("分页查询用户列表:{}", pageBean);
        PageResult users = adminService.listUser(pageBean);

        return Result.success(users);
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/delete")
    @Transactional // 事务管理
    public Result<Object> deleteById(@RequestParam("id") Integer id) {
        log.info("删除用户:{}",id);
        adminService.deleteById(id);

        return Result.success("删除成功");
    }
}
