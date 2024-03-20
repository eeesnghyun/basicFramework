package com.app.basic.common.config;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;
@Slf4j
@Profile("dev")
@Component
public class EmbeddedRedis {

	@Value("${spring.redis.port}")
	private int redisPort;

	private RedisServer redisServer;

	@PostConstruct
	public void startRedis() throws IOException {
		redisServer = RedisServer.builder()
						.port(redisPort)
						.setting("maxmemory 128M")
						.build();

		try {
			redisServer.start();

			log.info("===============================================");
			log.info(":: Local Redis Start(port={}) ::", redisPort);
			log.info("===============================================");
		} catch (Exception e) {
			log.error("", e);
		}
	}

	@PreDestroy
	public void stopRedis() {
		if (redisServer != null) {
            redisServer.stop();
        }
	}
}
