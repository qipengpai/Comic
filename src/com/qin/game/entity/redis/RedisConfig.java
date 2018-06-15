package com.qin.game.entity.redis;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description : 读取redis 配置文件
 */
public class RedisConfig {
	private static Properties properties = new Properties();

	static {
		try {
			properties.load(RedisConfig.class.getResourceAsStream("redisconf.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description : 获取redis 主机
	 * @return :String
	 */
	public static String getHostName() {
		return properties.getProperty("HOSTNAME");
	}

	/**
	 * 
	 * @Description : 获取redis 端口
	 * @return :String
	 */
	public static Integer getPort() {
		String port = properties.getProperty("PORT");
		if (port != null) {
			return Integer.valueOf(port);
		}
		return null;
	}
	/**
	 * 
	 * @Description : 获取redis 密码
	 * @return :String
	 */
	public static String getPWD() {
		return properties.getProperty("PWD");
	}
}
