package com.ruoyi.web.controller.system;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 个人信息 业务处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController
{
    @Autowired
    private ISysUserService userService;
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUrl;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user)
    {
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        if (userService.updateUserProfile(user) > 0)
        {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            // 更新缓存用户信息
            loginUser.getUser().setNickName(user.getNickName());
            loginUser.getUser().setPhonenumber(user.getPhonenumber());
            loginUser.getUser().setEmail(user.getEmail());
            loginUser.getUser().setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("auth_code",authCode);
            jsonObject.put("live_user_id",user.getUserId().toString());
            if(StringUtils.isNotBlank(user.getNickName())){
                jsonObject.put("nickname",user.getNickName());
            }
            System.err.println(loginUser.getUser().getAvatar());
            // 数据库拿到头像
            SysUser sysUser = userMapper.selectUserById(loginUser.getUser().getUserId());
            if(StringUtils.isNotBlank(loginUser.getUser().getAvatar())){
                jsonObject.put("head_img",sysUser.getAvatar());
            }
            System.out.println("1 : " + jsonObject);
            String body = HttpUtil.createPost(liveUrl + "/api/auth/live/updateliveuserinfo").body(JSON.toJSONString(jsonObject)).execute().body();
            return AjaxResult.success();
        }
        return AjaxResult.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password))
        {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password))
        {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatar") String avatar)
    {
        if (StringUtils.isNotBlank(avatar))
        {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            //String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar))
            {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("auth_code",authCode);
                jsonObject.put("live_user_id",loginUser.getUser().getUserId().toString());
                if(StringUtils.isNotBlank(loginUser.getUser().getNickName())){
                    jsonObject.put("nickname",loginUser.getUser().getNickName());
                }
                if(StringUtils.isNotBlank(loginUser.getUser().getAvatar())){
                    jsonObject.put("head_img",avatar);
                }
                String body = HttpUtil.createPost(liveUrl + "/api/auth/live/updateliveuserinfo").body(JSON.toJSONString(jsonObject)).execute().body();
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
}
