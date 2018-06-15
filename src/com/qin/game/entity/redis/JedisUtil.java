package com.qin.game.entity.redis;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class JedisUtil {
	// jedis
	protected static JedisFactory jedisFactory = JedisFactory.getInstance();

	/** set Object */
	public static void set(String key, Object object) {
		jedisFactory.getJedis().set(key.getBytes(), SerializeUtil.serialize(object));

	}
	
	/**
	 * @date 2017年1月14日上午11:11:58
	 * @param key
	 * @param object
	 * @param seconds void
	 * @Des:带过期时间的保存
	 */
	public static void setExpire(String key, Object object,int seconds) {
		jedisFactory.getJedis().setExpire(key.getBytes(), SerializeUtil.serialize(object),seconds);
		
	}
	
	

	/** get Object */
	public static Object get(String key) {
		byte[] value = jedisFactory.getJedis().get(key.getBytes());
		return SerializeUtil.unserialize(value);

	}

	/** delete a key **/
	public static void del(String key) {
		jedisFactory.getJedis().del(key.getBytes());
	}
	
	/**
	 * 
	 * 模糊删除key
	 * 
	 * */
	@SuppressWarnings("unused")
	public static void batchDel(String key){  
        Set<String> set = jedisFactory.getJedis().keys(key+"*");  
        Iterator<String> it = set.iterator();  
        while(it.hasNext()){  
            String keyStr = it.next();  
            System.out.println(keyStr);  
            jedisFactory.getJedis().del(keyStr);  
        }  
    }  
	
	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>更新一条数据</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public static Object update(String key,Object valObject){
		byte[] value = jedisFactory.getJedis().update(key.getBytes(), SerializeUtil.serialize(valObject));
		return SerializeUtil.unserialize(value);
	}
	
	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>检查key是否存在</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public static boolean exists(String key){
		return jedisFactory.getJedis().exists(key.getBytes());
	}
}
