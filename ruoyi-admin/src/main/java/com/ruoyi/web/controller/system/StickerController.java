package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.vo.LiveVO;
import com.ruoyi.system.service.IStickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sticker")
public class StickerController {
    @Autowired
    private IStickerService stickerService;

    @PostMapping("/getStickerInfoList")
    public AjaxResult getStickerInfoList(@RequestBody LiveVO liveVO){
        return stickerService.getStickerInfoList(liveVO);
    }
    @PostMapping("/delStickerInfo")
    public AjaxResult delStickerInfo(@RequestBody LiveVO liveVO){
        return stickerService.delStickerInfo(liveVO);
    }
    @PostMapping("/addStickerInfo")
    public AjaxResult addStickerInfo(@RequestBody LiveVO liveVO){
        return stickerService.addStickerInfo(liveVO);
    }
}
