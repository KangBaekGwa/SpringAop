package baekgwa.springaop;

import baekgwa.springaop.global.aop.AspectV1;
import baekgwa.springaop.global.aop.AspectV2;
import baekgwa.springaop.global.aop.AspectV3;
import baekgwa.springaop.global.aop.AspectV4;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAopApplication.class, args);
    }

    //예제1
//    @Bean
//    public AspectV1 aspectV1() {
//        return new AspectV1();
//    }

    //예제2
//    @Bean
//    public AspectV2 aspectV2() {
//        return new AspectV2();
//    }

    //예제3
//    @Bean
//    public AspectV3.DoLog doLog() {
//        return new AspectV3.DoLog();
//    }
//    @Bean
//    public AspectV3.Transaction transaction() {
//        return new AspectV3.Transaction();
//    }

    //예제4
    @Bean
    public AspectV4.Transaction transaction() {
        return new AspectV4.Transaction();
    }
}
