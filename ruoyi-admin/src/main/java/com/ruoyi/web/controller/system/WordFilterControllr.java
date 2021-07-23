package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.vo.LiveVO;
import com.ruoyi.system.service.ILiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wordFilter")
public class WordFilterControllr {
    @Autowired
    private ILiveService liveService;
    /** 获取敏感词列表 */
    @PostMapping("/getFilterWordList")
    public AjaxResult getFilterWordList(){
        return liveService.getFilterWordList();
    }
    /** 删除敏感词 */
    @PostMapping("/delFilterWord")
    public AjaxResult delFilterWord(@RequestBody LiveVO liveVO){
        return liveService.delFilterWord(liveVO);
    }
    /** 添加敏感词 */
    @PostMapping("/addFilterWord")
    public AjaxResult addFilterWord(@RequestBody LiveVO liveVO){
        return liveService.addFilterWord(liveVO);
    }
}
