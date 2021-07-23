package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Prize;
import com.ruoyi.system.service.IPrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lottery")
public class PrizeController {
    @Autowired
    private IPrizeService prizeService;
    /** 添加奖品 */
    @PostMapping("/addLottery")
    public AjaxResult addLottery(@RequestBody Prize prize){
        return prizeService.addLottery(prize);
    }
    /** 删除奖品 */
    @PostMapping("/delLottery")
    public AjaxResult delLottery(@RequestBody Prize prize){
        return prizeService.delLottery(prize);
    }
    /** 修改奖品 */
    @PostMapping("/editLottery")
    public AjaxResult editLottery(@RequestBody Prize prize){
        return prizeService.editLottery(prize);
    }
    /** 获取奖品列表 */
    @PostMapping("/getLotteryList")
    public AjaxResult getLotteryList(@RequestBody Prize prize){
        return prizeService.getLotteryList(prize);
    }
    /** 获取已中奖记录 */
    @PostMapping("/getPrizedList")
    public AjaxResult getPrizedList(@RequestBody Prize prize){
        return prizeService.getPrizedList(prize);
    }
    /** 添加抽奖活动 */
    @PostMapping("/addLotteryActive")
    public AjaxResult addLotteryActive(@RequestBody Prize prize){
        return prizeService.addLotteryActive(prize);
    }
    /** 获取抽奖活动记录 */
    @PostMapping("/getActiveList")
    public AjaxResult getActiveList(@RequestBody Prize prize){
        return prizeService.getActiveList(prize);
    }
}
