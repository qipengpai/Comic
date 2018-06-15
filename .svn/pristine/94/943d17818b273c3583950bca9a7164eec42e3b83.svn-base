package com.qin.game.entity.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;


public class JedisAdpater {

	private Jedis jedis;

	public JedisAdpater(Jedis jedis) {
		this.jedis = jedis;
	}

	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>清空所有缓存</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public void flushDB(){
		try {
			jedis.flushDB();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
		
	}
	
	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>存储值</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public void set(final byte[] key, final byte[] value) {
		if(value==null){
			return ;
		}
		try {
			jedis.set(key, value);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
	}
	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>存储带过期时间的值</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public void setExpire(final byte[] key, final byte[] value,int seconds) {
		if(value==null){
			return ;
		}
		try {
			if(seconds>0){
				jedis.set(key, value);
				jedis.expire(key, seconds);
			}else{
				jedis.set(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
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
	public boolean exists(final byte[] key){
		try {
			  return jedis.exists(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
		return false;
		
	}

	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>获取值</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public byte[] get(final byte[] key) {
		try {
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
		return null;
	}


	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>更具key删除value</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public void del(final byte[] key) {
		try {
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
	}
	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>更具key删除value</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public void del(String key) {
		try {
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
	}

	public Set<String> hkeys(final String key) {
		try {
			return jedis.hkeys(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
		return null;
	}
	/***
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>设置key的过期时间</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public void expire(final String key, final int seconds) {
		try {
			jedis.expire(key, seconds);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
	}

	/**
	 * 
	 * @author 覃金林
	 * @date 2018年1月24日
	 * @Remarks
	 * @Tile={<h1>对key进行更新</h1>}
	 * @describe={<p>...</p>}
	 *
	 */
	public byte[] update(final byte[] key, final byte[] value){
		try {
			jedis.getSet(key, value);
			return jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
		return null;
	}

	public Set<String> keys(String string) {
		// TODO Auto-generated method stub
		try {
			return	jedis.keys(string);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JedisFactory.returnResource(jedis);
		}
		return null;
	}

}
