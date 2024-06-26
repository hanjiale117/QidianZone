package com.zone.controller.user;

import com.zone.constant.JwtClaimsConstant;
import com.zone.context.BaseContext;
import com.zone.dto.CommentDTO;
import com.zone.dto.LoginDTO;
import com.zone.entity.User;
import com.zone.properties.JwtProperties;
import com.zone.result.Result;
import com.zone.service.ArticleService;
import com.zone.service.CommentService;
import com.zone.service.UserService;
import com.zone.utils.JwtUtil;
import com.zone.vo.ArticleInfoVO;
import com.zone.vo.LoginVO;
import com.zone.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JwtProperties jwtProperties;

    @GetMapping("/login")
    public Result<Object> login(@RequestBody LoginDTO loginDTO) {
        // 1.登录
        log.info("登录:{}", loginDTO);
        //TODO 输入的密码进行MD5加密

        // 2.用户登录
        // 获得用户id
        Integer id = userService.login(loginDTO);
        if (id == null || id == -1) {
            return Result.error("登录失败");
        }
        // 登录成功
        log.info("用户:{}登录成功", loginDTO.getAccount());

        // 3.生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, id);
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(), claims); // 生成JWT令牌

        // 4.封装VO
        LoginVO userLoginVO = LoginVO.builder()
                .id(id)
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result<Object> updatePassword(@RequestParam("password") String password) {
        log.info("修改密码:{}", password);
        // TODO 需要对修改的密码进行MD5加密
        userService.updatePassword(password);

        return Result.success("修改密码成功");
    }

    /**
     * 修改昵称
     */

    @PutMapping("/updateNickname")
    public Result<Object> updateNickname(@RequestParam("nickName") String nickName) {
        log.info("修改昵称:{}", nickName);
        // 传入DTO对象
        userService.updateNickName(nickName);

        return Result.success("修改昵称成功");
    }

    /**
     * 修改头像
     */

    @PutMapping("/updateAvatar")
    public Result<Object> updateAvatar(@RequestParam("avatarUrl") String avatarUrl) {
        log.info("修改头像:{}", avatarUrl);
        // 传入DTO对象
        userService.updateAvatar(avatarUrl);

        return Result.success("修改头像成功");
    }


    /**
     * 获取用户最基本信息
     */

    @GetMapping("/getUserInfo")
    public Result<UserVO> getUserInfo() {
        log.info("获取{}用户信息",BaseContext.getCurrentId());
        // 1.利用LocalThread获取用户信息
        User user = userService.getUserInfo(BaseContext.getCurrentId());

        // 2.将获取的User 对象 拷贝到 UserVO
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        // 3.返回用户基本信息
        log.info("获取用户信息成功");
        return Result.success(userVO);
    }


}
