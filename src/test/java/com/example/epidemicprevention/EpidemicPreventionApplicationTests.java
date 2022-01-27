package com.example.epidemicprevention;

import com.example.epidemicprevention.velocity.VelocityCodeGen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;

@SpringBootTest
class EpidemicPreventionApplicationTests {

    @Autowired
    private VelocityCodeGen velocityCodeGen;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

}
