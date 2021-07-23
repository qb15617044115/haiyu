package com.ruoyi.web.controller.customer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.TxzhUser;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserUtil {
    public static TxzhUser getCurrentUser(HttpServletRequest request, StringRedisTemplate redisTemplate){
        // 获取token
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)) {
            return null;
        }
        String userString = redisTemplate.opsForValue().get(Constant.USER_INFO + JWTUtil.getUserId(token));
        if(StringUtils.isBlank(userString)){
            return null;
        }
        return JSON.parseObject(userString,TxzhUser.class);
    }

    // 更新用户缓存信息
    public static void setCurrentUser(HttpServletRequest request, StringRedisTemplate redisTemplate, TxzhUser user){
        // 获取token
        String token = request.getHeader("token");
        if(StringUtils.isNotBlank(token)) {
            // 清除原先缓存
            redisTemplate.delete(Constant.USER_INFO + JWTUtil.getUserId(token));
            redisTemplate.opsForValue().set(Constant.USER_INFO + JWTUtil.getUserId(token), JSONObject.toJSONString(user));
        }
    }
}

