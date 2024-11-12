package com.hyeonho.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class PostgreSQLConfig implements ApplicationRunner {

    @Autowired
    private final DataSource dataSource;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public PostgreSQLConfig(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try(Connection conn = dataSource.getConnection()) {
            System.out.println(dataSource.getClass());
            System.out.println(conn.getMetaData().getURL());
            System.out.println(conn.getMetaData().getUserName());
        }

    }
}
