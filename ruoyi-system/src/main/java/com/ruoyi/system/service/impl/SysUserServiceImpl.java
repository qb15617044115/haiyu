package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysWalletLog;
import com.ruoyi.system.domain.vo.CloseLiveVO;
import com.ruoyi.system.domain.vo.GetLiveVO;
import com.ruoyi.system.domain.vo.LiveVO;
import com.ruoyi.system.domain.vo.OpenLiveVO;
import com.ruoyi.system.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 用户 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysUserServiceImpl implements ISysUserService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private ISysConfigService configService;
    @Autowired
    private SysWalletLogMapper sysWalletLogMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Value("${live.authCode}")
    private String authCode;
    @Value("${live.liveUrl}")
    private String liveUserUrl;

    /**
     * 根据条件分页查询用户列表
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SysUser> selectUserList(SysUser user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName)
    {
        return userMapper.selectUserByUserName(userName);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId)
    {
        return userMapper.selectUserById(userId);
    }

    /**
     * 查询用户所属角色组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(String userName)
    {
        List<SysRole> list = roleMapper.selectRolesByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list)
        {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属岗位组
     *
     * @param userName 用户名
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(String userName)
    {
        List<SysPost> list = postMapper.selectPostsByUserName(userName);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list)
        {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param userName 用户名称
     * @return 结果
     */
    @Override
    public String checkUserNameUnique(String userName)
    {
        int count = userMapper.checkUserNameUnique(userName);
        if (count > 0)
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user)
    {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user)
    {
        if (StringUtils.isNotNull(user.getUserId()) && user.isAdmin())
        {
            throw new CustomException("不允许操作超级管理员用户");
        }
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUser(SysUser user)
    {
        // 保证一个机构只能有一个用户
        int count = userMapper.selectByDeptId(user.getDeptId());
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户岗位关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        return rows;
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateUser(SysUser user)
    {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与岗位管理
        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户状态
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserStatus(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户基本信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserProfile(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户头像
     *
     * @param userName 用户名
     * @param avatar 头像地址
     * @return 结果
     */
    @Override
    public boolean updateUserAvatar(String userName, String avatar)
    {
        return userMapper.updateUserAvatar(userName, avatar) > 0;
    }

    /**
     * 重置用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetPwd(SysUser user)
    {
        return userMapper.updateUser(user);
    }

    /**
     * 重置用户密码
     *
     * @param userName 用户名
     * @param password 密码
     * @return 结果
     */
    @Override
    public int resetUserPwd(String userName, String password)
    {
        return userMapper.resetUserPwd(userName, password);
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user)
    {
        Long[] roles = user.getRoleIds();
        if (StringUtils.isNotNull(roles))
        {
            // 新增用户与角色管理
            List<SysUserRole> list = new ArrayList<SysUserRole>();
            for (Long roleId : roles)
            {
                SysUserRole ur = new SysUserRole();
                ur.setUserId(user.getUserId());
                ur.setRoleId(roleId);
                list.add(ur);
            }
            if (list.size() > 0)
            {
                userRoleMapper.batchUserRole(list);
            }
        }
    }

    /**
     * 新增用户岗位信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user)
    {
        Long[] posts = user.getPostIds();
        if (StringUtils.isNotNull(posts))
        {
            // 新增用户与岗位管理
            List<SysUserPost> list = new ArrayList<SysUserPost>();
            for (Long postId : posts)
            {
                SysUserPost up = new SysUserPost();
                up.setUserId(user.getUserId());
                up.setPostId(postId);
                list.add(up);
            }
            if (list.size() > 0)
            {
                userPostMapper.batchUserPost(list);
            }
        }
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteUserById(Long userId)
    {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与岗位表
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteUserByIds(Long[] userIds)
    {
        for (Long userId : userIds)
        {
            checkUserAllowed(new SysUser(userId));
        }
        // 删除用户与角色关联
        userRoleMapper.deleteUserRole(userIds);
        // 删除用户与岗位关联
        userPostMapper.deleteUserPost(userIds);
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 导入用户数据
     *
     * @param userList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysUser> userList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(userList) || userList.size() == 0)
        {
            throw new CustomException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (SysUser user : userList)
        {
            try
            {
                // 验证是否存在这个用户
                SysUser u = userMapper.selectUserByUserName(user.getUserName());
                if (StringUtils.isNull(u))
                {
                    user.setPassword(SecurityUtils.encryptPassword(password));
                    user.setCreateBy(operName);
                    this.insertUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    user.setUpdateBy(operName);
                    this.updateUser(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + user.getUserName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + user.getUserName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + user.getUserName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Value("${live.url}")
    private String liveUrl;
    @Override
    public AjaxResult addUserLivePermission(SysUser user) throws IOException {
        // 判断 userid 是否为空
        if(null == user.getUserId()){
            return AjaxResult.error();
        }
        // 查询出用户 id 对应的
        SysUser sysUser = userMapper.selectUserById(user.getUserId());
        System.out.println(sysUser.toString());
        if(StringUtil.isNotEmpty(sysUser.getAuthCode())){
            return AjaxResult.error();
        }
        // 发送 http 请求获取到授权码
        JSONObject json = new JSONObject();
        json.put("uinfo_userid",sysUser.getUserId()+"");
        json.put("uinfo_nickname",sysUser.getNickName());
        json.put("uinfo_headurl",sysUser.getAvatar());
        json.put("uinfo_sex",sysUser.getSex());
        json.put("sys_auth_code","zhixin2021@gmy");
        String post = HttpUtil.createPost(liveUrl + "/xlive/xaddliveuser").body(JSON.toJSONString(json)).execute().body();
        JSONObject jsonObject = JSON.parseObject(post);
        if(!jsonObject.get("status").equals(200)){
            return AjaxResult.error(jsonObject.get("msg").toString());
        }
        String data = jsonObject.get("data").toString();
        if (StringUtil.isEmpty(data)){
            return AjaxResult.error();
        }
        user.setAuthCode(data);
        userMapper.updateAutoCodeByUserId(user);
        return AjaxResult.success();
    }

    @Override
    public AjaxResult openLive(OpenLiveVO openLiveVO,SysUser user) {
        // 获取到当前用户的授权码
        if (openLiveVO.getUserId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        if(!openLiveVO.getUserId().equals(user.getUserId().toString())){
            return AjaxResult.error("只能对自己进行操作!");
        }
        SysUser sysUser = userMapper.selectUserById(user.getUserId());
        if(sysUser == null){
            return AjaxResult.error("用户不存在");
        }
        if (StringUtils.isBlank(sysUser.getAuthCode())){
            return AjaxResult.error("当前未拥有直播权限");
        }
        // 如果拥有状态码,将数据封装起来发送一条 http 请求到直播模块开启直播
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("room_name",openLiveVO.getRoomName());
        jsonObject.put("logo_url",openLiveVO.getLogoUrl());
        jsonObject.put("hostname",openLiveVO.getHostName());
        jsonObject.put("expire_time",openLiveVO.getExpireTime());
        jsonObject.put("auth_code",sysUser.getAuthCode());
        // 获取到当前机构的所有子机构
        if(!openLiveVO.getDeptIds().isEmpty()){
            String join = StringUtils.join(openLiveVO.getDeptIds(), ",");
            jsonObject.put("org_id",join);
        }
        jsonObject.put("uinfo_userid",sysUser.getUserId().toString());
        String body = HttpUtil.createPost(liveUrl + "/xlive/xaddlive").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result);
    }

    @Override
    public AjaxResult getLiveList(GetLiveVO getLiveVO,SysUser user) {
        // 判断 userid 是否为空
        if (getLiveVO.getUserId() == null){
            return AjaxResult.error("用户id不能为空");
        }
        // 查询出用户 id 对应的
        SysUser sysUser = userMapper.selectUserById(getLiveVO.getUserId());
        if(sysUser == null){
            return AjaxResult.error("用户不存在");
        }
        if (StringUtils.isBlank(sysUser.getAuthCode())){
            return AjaxResult.error("当前未拥有直播权限");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("page",getLiveVO.getPage());
        jsonObject.put("size",getLiveVO.getSize());
        jsonObject.put("uid",sysUser.getUserId());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(getLiveVO.getEndDate() != null){
            jsonObject.put("cEndTime",simpleDateFormat.format(getLiveVO.getEndDate()));
        }
        if(getLiveVO.getStartDate() != null){
            jsonObject.put("cBeginTime",simpleDateFormat.format(getLiveVO.getStartDate()));
        }
        jsonObject.put("auth_code",sysUser.getAuthCode());
        String body = HttpUtil.createPost(liveUrl + "/xlive/xgetlivelistbyuser").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result);
    }

    @Override
    public AjaxResult closeLive(CloseLiveVO closeLiveVO, SysUser user) {
        // 判断 userid 是否为空
        if(!closeLiveVO.getLiveUserId().toString().equals(user.getUserId().toString())){
            return AjaxResult.error("只能对自己进行操作!");
        }
        // 查询出用户 id 对应的
        SysUser sysUser = userMapper.selectUserById(user.getUserId());
        System.out.println(sysUser.toString());
        if(StringUtils.isBlank(sysUser.getAuthCode())){
            return AjaxResult.error("当前未拥有直播权限");
        }
        JSONObject jsonObject = new JSONObject();
        if(closeLiveVO.getRoomId() == null){
            jsonObject.put("room_id","");
        }else{
            jsonObject.put("room_id",closeLiveVO.getRoomId().toString());
        }
        jsonObject.put("live_user_id",sysUser.getUserId().toString());
        jsonObject.put("user_auth_code",sysUser.getAuthCode().toCharArray());
        jsonObject.put("auth_code",authCode);
        String body = HttpUtil.createPost(liveUserUrl + "/api/auth/live/xcloselive").body(JSON.toJSONString(jsonObject)).execute().body();
        JSONObject result = JSON.parseObject(body);
        if(!result.get("status").equals(200)){
            return AjaxResult.error(result.get("msg").toString());
        }
        return AjaxResult.success(result);
    }

    @Override
    public String getDeptCode(SysUser user) {
        return sysDeptMapper.selectDeptCodeByDeptId(user.getDept().getDeptId());
    }

    @Override
    @Transactional
    public AjaxResult updateUserMoney(SysUser user, SysUser currentUser) {
        // 判断参数是否正确
        if(user.getMoney() == null){
            return AjaxResult.error("操作金额不能为空");
        }
        if(!user.getType().equals(1) && !user.getType().equals(2)){
            return AjaxResult.error("操作类型参数错误");
        }
        // 获取到被操作用户的数据
        SysUser beforeUser = userMapper.selectUserById(user.getUserId());
        if(beforeUser == null){
            return AjaxResult.error("用户不存在!");
        }
        // 修改用户金额
        int count = userMapper.updateUserMoney(user);
        if(!(count > 0)){
            return AjaxResult.error("修改失败咯");
        }
        // 创建钱包记录对象
        SysWalletLog sysWalletLog = new SysWalletLog();
        if (user.getType().equals(1)){
            sysWalletLog.setAfterMoney(beforeUser.getMoney().add(user.getMoney()));
        }
        if (user.getType().equals(2)){
            sysWalletLog.setAfterMoney(beforeUser.getMoney().subtract(user.getMoney()));
        }
        sysWalletLog.setId(getGenerateId());
        sysWalletLog.setBeforeMoney(beforeUser.getMoney());
        sysWalletLog.setOperationMoney(user.getMoney());
        sysWalletLog.setLiveUserId(beforeUser.getUserId());
        sysWalletLog.setOperationId(currentUser.getUserId());
        sysWalletLog.setType(user.getType());
        sysWalletLog.setId(getGenerateId());
        // 添加钱包记录
        sysWalletLogMapper.insert(sysWalletLog);
        return AjaxResult.success();
    }

    public static synchronized long getGenerateId(){
        int random = (int) ((Math.random() * 9 + 1) * 100);
        String itm = String.valueOf(System.currentTimeMillis()) + String.valueOf(random);
        return Long.valueOf(itm);
    }
}
