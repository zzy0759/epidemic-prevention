package com.example.epidemicprevention;

import com.example.epidemicprevention.velocity.VelocityCodeGen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class EpidemicPreventionApplicationTests {

    @Autowired
    private VelocityCodeGen velocityCodeGen;

    @Test
    void contextLoads() throws IOException {
        velocityCodeGen.generateCode();
    }

}
