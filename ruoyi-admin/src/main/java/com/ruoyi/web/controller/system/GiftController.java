package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.vo.GiftVO;
import com.ruoyi.system.service.IGiftServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gift")
public class GiftController {
    @Autowired
    private IGiftServie giftServie;
    /** 获取礼物列表 */
    @PostMapping("/getGiftInfoList")
    public AjaxResult getGiftInfoList(@RequestBody GiftVO giftVO){
        return giftServie.getGiftInfoList(giftVO);
    }
    /** 添加礼物 */
    @PostMapping("/addGiftInfo")
    public AjaxResult addGiftInfo(@RequestBody GiftVO giftVO){
        return giftServie.addGiftInfo(giftVO);
    }
    /** 删除礼物 */
    @PostMapping("/delGiftInfo")
    public AjaxResult delGIftInfo(@RequestBody GiftVO giftVO){
        return giftServie.delGIftInfo(giftVO);
    }
    /** 更新礼物 */
    @PostMapping("/updateGiftInfo")
    public AjaxResult updateGiftInfo(@RequestBody GiftVO giftVO){
        return giftServie.updateGiftInfo(giftVO);
    }
    /** 获取房间送礼列表 */
    @PostMapping("/getGiftList")
    public AjaxResult getGiftList(@RequestBody GiftVO giftVO){
        return giftServie.getGiftList(giftVO);
    }
}
