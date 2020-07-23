package cn.com.seu.tonguetip;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.com.seu.tonguetip.*.mapper")
public class TonguetipApplication {

    public static void main(String[] args) {
        SpringApplication.run(TonguetipApplication.class, args);
    }

}
