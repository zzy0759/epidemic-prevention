package com.example.epidemicprevention;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.epidemicprevention.module.news.entity.News;
import com.example.epidemicprevention.module.news.mapper.NewsMapper;
import com.example.epidemicprevention.velocity.VelocityCodeGen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.util.List;
import java.util.Random;

@SpringBootTest
class EpidemicPreventionApplicationTests {

    @Autowired
    private VelocityCodeGen velocityCodeGen;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private NewsMapper newsMapper;

    @Test
    void contextLoads() throws IOException {
        velocityCodeGen.generateCode();
    }
    @Test
    void password(){
//        System.out.println(passwordEncoder.encode("123456"));
//        System.out.println(passwordEncoder.matches("12345abc", "$10$w/YxaQWKbIOzONhf2x6/rug0RbBhrSc5eSspwkpjSKoXkP.RyzkAW"));
        System.out.println(passwordEncoder.encode("abc123456"));
    }

    @Test
    void test1(){
        News news = newsMapper.lastNew();
        System.out.println(news);
    }

    public static void main(String[] args) {
        Random random=new Random();
        for (int i = 0; i < 1000; i++) {
            System.out.println(random.nextInt(101));
        }
    }
    @Test
    void test2(){
        QueryWrapper<News> queryWrapper=new QueryWrapper<>();
        List<News> news = newsMapper.selectList(queryWrapper);
        Random random=new Random();
        news.forEach(n->{
            n.setCredible(random.nextInt(101));
            newsMapper.updateById(n);
        });
    }

}
