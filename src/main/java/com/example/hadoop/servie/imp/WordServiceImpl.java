package com.example.hadoop.servie.imp;


import com.example.hadoop.DemohadoopApplication;
import com.example.hadoop.conf.HadoopHDFSConfig;
import com.example.hadoop.conf.HadoopProperties;
import com.example.hadoop.mapReduce.WordMapper;
import com.example.hadoop.mapReduce.WordReduce;
import com.example.hadoop.servie.WordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class WordServiceImpl implements WordService {


    @Autowired
    Configuration configuration;
    @Autowired
    private HadoopProperties hadoopProperties;
    @Autowired
    private FileSystem fileSystem;
    @Override
    public void count(String jobName ,String dirOrFile) throws IOException, InterruptedException, ClassNotFoundException {
        // 创建job对象
//        HadoopHDFSConfig hadoopConfig = new HadoopHDFSConfig();
//        Job job = Job.getInstance(hadoopConfig.getConfiguration(hadoopProperties), jobName);
        Job job = Job.getInstance(configuration, jobName);
        // 指定程序的入口
        job.setJarByClass(DemohadoopApplication.class);

        // 指定自定义的Mapper阶段的任务处理类
        job.setMapperClass(WordMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);
        // 数据HDFS文件服务器读取数据路径
        FileInputFormat.setInputPaths(job, new Path(dirOrFile));

        // 指定自定义的Reducer阶段的任务处理类
        job.setReducerClass(WordReduce.class);
        // 设置最后输出结果的Key和Value的类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        // 将计算的结果上传到HDFS服务
        String outputPath = hadoopProperties.getOutPutPath() + "/" + jobName;
        rmdir(outputPath, null);
        FileOutputFormat.setOutputPath(job, new Path(outputPath));

        // 执行提交job方法，直到完成，参数true打印进度和详情
        job.waitForCompletion(true);
        System.out.println("== wordCountReduce Finished ==");
    }

    /**
     * 删除文件或者文件目录
     *
     * @param path     文件目录路径
     * @param fileName 文件名称
     */
    public void rmdir(String path, String fileName) {
        log.info("【删除文件】 开始删除, 删除文件目录的路径: {}, 文件目录: {}", path, fileName);
        try {
            // 返回FileSystem对象
            if (StringUtils.isNotBlank(fileName)) {
                path = path + "/" + fileName;
            }
            // 删除文件或者文件目录  delete(Path f) 此方法已经弃用
            fileSystem.delete(new Path(path), true);
        } catch (IllegalArgumentException | IOException e) {
            log.error("【删除文件失败】", e);
        }
    }
}
