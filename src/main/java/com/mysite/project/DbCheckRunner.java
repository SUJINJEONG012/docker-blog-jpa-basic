package com.mysite.project;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DbCheckRunner implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DB 연결 시도중...");
        
        System.out.println("DB_URL: " + System.getenv("DB_URL"));
        System.out.println("DB_USERNAME: " + System.getenv("DB_USERNAME"));
        System.out.println("DB_PASSWORD: " + System.getenv("DB_PASSWORD"));
        
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("DB 연결 성공: " + conn.getMetaData().getURL());
        } catch (Exception e) {
            System.out.println("DB 연결 실패: " + e.getMessage());
        }
    }
}