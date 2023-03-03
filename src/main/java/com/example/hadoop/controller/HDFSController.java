package com.example.hadoop.controller;
import java.util.List;
import java.util.Map;

import com.example.hadoop.entity.BaseResponse;
import com.example.hadoop.servie.HDFSService;
import com.example.hadoop.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/hdfs")
@Api(value = "HDFS Controlle", tags = "HDFS 操作服务")
public class HDFSController {
    // 日志记录
    private static final Logger logger = LoggerFactory.getLogger(HDFSController.class);
    @Autowired
    private HDFSService service;

    /**
     * 创建的文件夹权限不够，需要设置权限问题
     * @return
     */

    @ApiOperation(httpMethod = "POST", value = "创建文件夹")
    @RequestMapping(value = "/mkdirFolder", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse<?> mkdirFolder(@RequestParam String folderPath) {
        boolean target = service.mkdirFolder(folderPath);
        return BaseResponse.ok(target);

    }
    @ApiOperation(httpMethod = "POST", value = "文件上传")
    @RequestMapping(value = "/upload", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody    public BaseResponse<?> upload(@RequestParam String uploadPath, MultipartFile file) {
        service.copyFromLocalFile(false, true, FileUtil.multipartFileToFile(file).getPath(), uploadPath);
        return BaseResponse.ok();
    }

    @ApiOperation(httpMethod = "POST", value = "文件拷贝")
    @RequestMapping(value = "/copyFromLocalFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse copyFromLocalFile(
            @RequestParam String folderPath, @RequestParam String uploadPath) {
        service.copyFromLocalFile(false,true,folderPath, uploadPath);
        return BaseResponse.ok();
    }

    @ApiOperation(httpMethod = "POST", value = "判断文件夹是否存在")
    @RequestMapping(value = "/existFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse existFile(
            @RequestParam String folderPath) {
        boolean target = service.existFile(folderPath);
        return BaseResponse.ok(target);
    }

    @ApiOperation(httpMethod = "POST", value = "读取目录")
    @RequestMapping(value = "/readCatalog", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse readCatalog(
            @RequestParam String folderPath) {
        List<Map<String, Object>> list = service.readCatalog(folderPath);
        return BaseResponse.ok(list);
    }

  /*  @ApiOperation(httpMethod = "POST", value = "新建文件")
    @RequestMapping(value = "/createFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse createFile(
            @RequestParam String folderPath) {
        FileInputStream inputStream = null;
        MultipartFile file = null;
        try {
            inputStream = new FileInputStream("C:\\data\\words.txt");
            file = new MUl("test.txt", inputStream);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }finally{
            try {
                inputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                logger.error(e.getMessage());
            }
        }
        service.createFile(folderPath,file);
        return BaseResponse.ok();
    }*/

    @ApiOperation(httpMethod = "POST", value = "读取文件内容")
    @RequestMapping(value = "/readFileContent", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse readFileContent(
            @RequestParam String folderPath) {
        String content = service.readFileContent(folderPath);
        return BaseResponse.ok(content);
    }

    @ApiOperation(httpMethod = "POST", value = "文件列表")
    @RequestMapping(value = "/listFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse listFile(
            @RequestParam String folderPath) {
        List<Map<String, String>> list = service.listFile(folderPath);
        return BaseResponse.ok(list);
    }

    @ApiOperation(httpMethod = "POST", value = "文件重命名")
    @RequestMapping(value = "/renameFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse renameFile(
            @RequestParam String oldName,@RequestParam String newName) {
        boolean target = service.renameFile(oldName,newName);
        return BaseResponse.ok(target);
    }

    /**
     * 指定文件位删除成功，需要寻找原因
     * @param
     * @return
     */
    @ApiOperation(httpMethod = "POST", value = "文件删除")
    @RequestMapping(value = "/deleteFile", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse deleteFile(
            @RequestParam String folderPath) {
        boolean target = service.deleteFile(folderPath);
        return BaseResponse.ok(target);
    }

}
