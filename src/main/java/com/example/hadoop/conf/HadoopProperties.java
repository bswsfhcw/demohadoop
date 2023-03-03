package com.example.hadoop.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Ruison
 * on 2019/7/5 - 13:54
 */
@Data
@Component
public class HadoopProperties {
    /** fs uri地址 */
    @Value("${hdfs.hdfsPath}")
    private String hdfsPath;
    @Value("${hdfs.directoryPath}")
    private String directoryPath;

    @Value("${hdfs.outPutPath}")
    private String outPutPath;

    public String getDirectoryPath() {
        StringBuilder sb = new StringBuilder(directoryPath);
        if (!(directoryPath.indexOf("/") == directoryPath.length())) {
            sb.append("/");
        }
        return sb.toString();
    }

    public String getPath() {
        return this.hdfsPath + this.directoryPath;
    }
}
