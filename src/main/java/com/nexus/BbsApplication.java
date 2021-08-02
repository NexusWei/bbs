package com.nexus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;

@SpringBootApplication
// 添加对 Mapper 包扫描，这样不用在每个Mapper上添加@Mapper
@MapperScan("com.nexus.mapper")
public class BbsApplication {

    public static void main(String[] args) {
        // 启动颜色格式化
        // 这不是唯一启动颜色格式的方式，有兴趣的同学可以查看源码
        /**
         * 1. AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
         * 2. 在`src/main/resources`目录下新建文件`application.properties`,
         *    内容为：`spring.output.ansi.enabled=always`
         *
         * 重要：如果配置第二种方式，第一种方式就不会起作用
         */
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);

        new SpringApplicationBuilder(BbsApplication.class)//
                .main(SpringVersion.class) // 这个是为了可以加载 Spring 版本
                .bannerMode(Banner.Mode.LOG)// 控制台和日志文件都打印
                .run(args);
    }
}
