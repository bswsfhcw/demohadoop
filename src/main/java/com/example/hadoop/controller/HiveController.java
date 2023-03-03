package com.example.hadoop.controller;


import com.example.hadoop.entity.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hive")
public class HiveController {

    public static final Logger logger = LoggerFactory.getLogger(HiveController.class);

    @Autowired
    @Qualifier("hiveDruidDataSource")
    private DataSource druidDataSource;

    @Autowired
    @Qualifier("hiveDruidTemplate")
    private JdbcTemplate jdbcTemplate;

    /**
     * http://localhost:8980/hadoop/hive/show
     */
    @GetMapping(value ="/show")
    public BaseResponse<?> show(){
        List<String> list = new ArrayList<String>();
        Statement statement = null;
        try {
            statement = druidDataSource.getConnection().createStatement();
            String sql = "show tables";
            logger.info("Running: " + sql);
            ResultSet res = statement.executeQuery(sql);
            while (res.next()) {
                list.add(res.getString(1));
            }
            List list1 =  jdbcTemplate.queryForList(sql);
            logger.info("count: " + list1.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return BaseResponse.ok(list);
    }
}
