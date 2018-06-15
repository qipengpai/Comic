package com.qin.game.entity.redis;

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class TestRedis {
	public static void main(String[] args) throws IOException {
		JedisPoolConfig config = new JedisPoolConfig();
		//最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
		config.setMaxIdle(200);
		//最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
		config.setMaxTotal(300);
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		String host = "*.aliyuncs.com";
		String password = "密码";
		JedisPool pool = new JedisPool(config, host, 6379, 3000, password);
		Jedis jedis = null;
		try {
		    jedis = pool.getResource();
		    /// ... do stuff here ... for example
		    jedis.set("foo", "bar");
		    String foobar = jedis.get("foo");
		    jedis.zadd("sose", 0, "car");
		    jedis.zadd("sose", 0, "bike");
		    Set<String> sose = jedis.zrange("sose", 0, -1);
		} finally {
		    if (jedis != null) {
		        jedis.close();
		    }
		}
		/// ... when closing your application:
		pool.destroy();
		}

		
		
		
		
		
		
		//删除某个值
		private static void del(String key) {
			JedisUtil.del(key);
			String obj = (String) JedisUtil.get(key);
			System.err.println("obj:"+obj);
		}
		//查询
		private static void get(String key) {
			String obj = (String) JedisUtil.get(key);
			System.err.println("obj:"+obj);
			
		}
		private static void getExpire(String key) {
			String obj2 = (String) JedisUtil.get(key);
			System.err.println("obj2:"+obj2);
			
		}





	   

	    /**
	     * @date 2017年1月14日下午7:49:57 void
	     * @Des:不带时间期限的存储
	     */
		private static void set() {
			JedisUtil.set("test_key", "TestRedis数据库");
		}
		/**
		 * @date 2017年1月14日下午7:49:33
		 * @param seconds 秒
		 * @Des: 带时间期限的存储
		 */
		private static void setExpire(int seconds) {
			JedisUtil.setExpire("test_key_Expire", "TestRedisExpire数据库",seconds);
		}



		private static void testM1() throws IOException {
			Properties properties = new Properties();
			properties.load(RedisConfig.class.getResourceAsStream("config/redisconf.properties"));
			String property = properties.getProperty("redis.hostName");
			System.err.println("property:"+property);
			
			File file = new File("config/redisconf.properties");
	        String name = file.getName();
	        String path = file.getPath();
	        String parent = file.getParent();
	        String CanonicalPath="22";
			try {
				CanonicalPath = file.getCanonicalPath();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        System.err.println("name;"+name);
	        System.err.println("path;"+path);
	        System.err.println("parent;"+parent);
	        System.err.println("CanonicalPath;"+CanonicalPath);
		}

}
