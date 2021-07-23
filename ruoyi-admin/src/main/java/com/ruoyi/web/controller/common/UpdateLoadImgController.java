package com.ruoyi.web.controller.common;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.AliyunOSSUtil;
import com.ruoyi.common.utils.ConstantProperties;
import com.ruoyi.common.utils.MultipartFileToFileUtil;
import com.ruoyi.web.controller.tool.CommonFileUtil;
import com.ruoyi.web.core.config.FdfsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;


@RestController
@RequestMapping("/common")
public class UpdateLoadImgController {
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    @Autowired
    private ConstantProperties constantProperties;
    @Autowired
    private CommonFileUtil commonFileUtil;
    @Autowired
    private FdfsConfig fdfsConfig;

    @PostMapping("/uploadImg")
    public AjaxResult uploadImg(@RequestParam MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return AjaxResult.error("请上传文件");
        }
        try{
            String s = commonFileUtil.uploadFile(file);
            return AjaxResult.success(fdfsConfig.getResHost() + s);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("上传文件出错");
        }
    }
}
