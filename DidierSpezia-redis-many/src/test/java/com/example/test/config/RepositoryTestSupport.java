package com.example.test.config;

import org.junit.ClassRule;
import org.junit.rules.RuleChain;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = { TestRedisConfigs.class })
@Slf4j
public class RepositoryTestSupport {
	public static @ClassRule RuleChain rules = RuleChain.outerRule(EmbeddedRedisServer.runningAt(5000));
}