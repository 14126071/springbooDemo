package lovin.utils;

import lovin.vo.RedisServerInfo;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;

/**
 * Created by bixin on 2017/12/19.
 */
public class RedisUtil {
    private static final int MAX_IDLE = 4;
    private static final int MAX_TOTAL = 8;
    private static final int DB_NUM = 16;

    private static JedisPool mp = null;
    private static ArrayList<JedisPool> mps = null;
    private static final int MAX_WAIT_MILLIS = 3000;
    private static final int TIMEOUT = 300;

    public static Jedis getJedisResource() {
        int index = RedisRandom.get();
        Jedis jedis = getJedisPool(index).getResource();
        return jedis;
    }

    public static void returnResource(Jedis jedis) {
        int index = RedisRandom.get();
        getJedisPool(index).returnResource(jedis);
    }

    private static JedisPool getJedisPool(int x) {
        if (mps == null) {
            synchronized (RedisUtil.class) {
                if (mps == null) {
                    mps = new ArrayList<JedisPool>();
                    JedisPoolConfig config = getJedisPoolConfig();
                    ArrayList<RedisServerInfo> serverList = RedisRandom.getRedisServerList();
                    for (RedisServerInfo info : serverList) {
                        try {
                            mps.add(new JedisPool(config, info.getIp(), info.getPort(), TIMEOUT, info.getPass(), info.getDb()));
                        } catch (Exception ignored) {
                        }
                    }
                }
            }
        }
        if (mps == null || x >= mps.size() || mps.get(x) == null) {
            return getJedisPool();
        }
        return mps.get(x);
    }


    private static JedisPool getJedisPool() {
        if (mp == null) {
            synchronized (RedisUtil.class) {
                if (mp == null) {
                    JedisPoolConfig config = getJedisPoolConfig();
                    mp = new JedisPool(config, "r-2zea88ff353d5954.redis.rds.aliyuncs.com", 6379, TIMEOUT, "Bi1747xin", 0);
                }
            }
        }
        return mp;
    }

    /**
     * 获取redis的连接池
     *
     * @return
     */
    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(MAX_TOTAL);
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(MAX_IDLE);
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(MAX_WAIT_MILLIS);
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；因为使用了Twemproxy，不支持ping操作，所以直接关掉这个功能
        config.setTestOnBorrow(true);
        config.setTestOnReturn(false);
        return config;
    }
}
