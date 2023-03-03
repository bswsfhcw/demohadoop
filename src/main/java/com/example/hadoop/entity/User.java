package com.example.hadoop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;


@Data
@TableName("User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键
    @TableId(type = IdType.AUTO)
    private Long id;
    private String sfzh;
    private String xm;
    private int xb;
}
