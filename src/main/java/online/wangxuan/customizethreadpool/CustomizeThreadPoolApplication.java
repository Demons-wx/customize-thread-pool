package online.wangxuan.customizethreadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableConfigurationProperties({TaskThreadPoolConfig.class})
@SpringBootApplication
public class CustomizeThreadPoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomizeThreadPoolApplication.class, args);
	}
}
