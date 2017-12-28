package lovin.utils;

import lovin.vo.RedisServerInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by bixin on 2017/12/19.
 */
public class RedisRandom {
    private static final String PASS = "TxwxBook@2015";
    private static ThreadLocal<Integer> redisIndexHolder = new ThreadLocal<Integer>();
    private static Random r = new Random();
    private static ArrayList<RedisServerInfo> list = new ArrayList<RedisServerInfo>();

    public static int get() {
        Integer x = redisIndexHolder.get();
        if (x == null) {
            x = r.nextInt(list.size());
            redisIndexHolder.set(x);
        }
        return x;
    }

    public static ArrayList<RedisServerInfo> getRedisServerList() {
        return list;
    }

    public static void init() {
        list.clear();
        String ips = PropertiesLoader.getString("HOST_MS");
        if (!StringUtil.isEmpty(ips)) {
            String[] tmp = ips.split(",");
            if (tmp.length > 0) {
                for (String x : tmp) {
                    try {
                        if (!StringUtil.isEmpty(x)) {
                            String[] ipport = x.split(":");
                            if (ipport.length == 2) {
                                list.add(new RedisServerInfo(ipport[0], PASS, 0, Integer.valueOf(ipport[1])));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        RedisUtil.getJedisResource();
    }

}

