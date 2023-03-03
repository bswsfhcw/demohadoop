package com.example.hadoop.conf;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * hadoop hdfs 参数配置
 * @author zzg
 *
 */
@Configuration
public class HadoopHDFSConfig {

    // 日志记录
    private static final Logger logger = LoggerFactory.getLogger(HadoopHDFSConfig.class);

    /**
     * hadoop hdfs 配置参数对象
     * @return
     */
    @Bean
    public org.apache.hadoop.conf.Configuration  getConfiguration(HadoopProperties hadoopProperties){
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("fs.defaultFS", hadoopProperties.getHdfsPath());
        return configuration;
    }
    /**
     * hadoop filesystem 文件系统
     * @return
     */
    @Bean
    public FileSystem getFileSystem(HadoopProperties hadoopProperties){
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(new URI(hadoopProperties.getDirectoryPath()), this.getConfiguration(hadoopProperties));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            logger.error(e.getMessage());
        }
        return fileSystem;
    }
}
