package com.mastercard.config;

import org.junit.rules.ExternalResource;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;

import java.io.IOException;

public class EmbeddedRedisServer extends ExternalResource {

	private static final int DEFAULT_PORT = 6379;
	private RedisServer server;
	private int port = DEFAULT_PORT;
	private boolean suppressExceptions = false;

	public EmbeddedRedisServer() {

	}

	protected EmbeddedRedisServer(int port) {
		this.port = port;
	}

	public static EmbeddedRedisServer runningAt(Integer port) {
		return new EmbeddedRedisServer(port != null ? port : DEFAULT_PORT);
	}

	public EmbeddedRedisServer suppressExceptions() {
		this.suppressExceptions = true;
		return this;
	}

	@Override
	protected void before() throws IOException {
		try {
			this.server = new RedisServerBuilder().setting("maxheap 512Mb").port(this.port).build();
			server.start();
		} catch (Exception e) {
			if (!suppressExceptions) {
				throw e;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.junit.rules.ExternalResource#after()
	 */
	@Override
	protected void after() {

		try {
			this.server.stop();
		} catch (Exception e) {
			if (!suppressExceptions) {
				throw e;
			}
		}
	}
}
