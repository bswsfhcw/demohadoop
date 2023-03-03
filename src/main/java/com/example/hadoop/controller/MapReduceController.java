package com.example.hadoop.controller;


import com.example.hadoop.entity.BaseResponse;
import com.example.hadoop.entity.User;
import com.example.hadoop.servie.UserService;
import com.example.hadoop.servie.WordService;
import com.example.hadoop.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(tags = "mapReduce")
@RestController
@RequestMapping("/mapReduce")
public class MapReduceController {
    @Autowired
    WordService wordService;
    /**
     * http://localhost:8980/hadoop/mapReduce/count
     * test 是 jobName
     * hdfs dfs -cat /outPut/test/part-r-00000
     */
    @ApiOperation(httpMethod = "POST", value = "count")
    @RequestMapping(value = "/count", method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BaseResponse<?> count(@RequestParam String jobName ,@RequestParam String dirOrFile) throws IOException, InterruptedException, ClassNotFoundException {
        wordService.count(jobName,dirOrFile);
        return BaseResponse.ok();
    }
}
