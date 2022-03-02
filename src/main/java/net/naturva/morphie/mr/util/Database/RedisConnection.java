package net.naturva.morphie.mr.util.Database;

import net.naturva.morphie.mr.MorphRedeem;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class RedisConnection {

    private MorphRedeem plugin;
    private static Jedis jedis;

    private final String redisKey;

    public RedisConnection(MorphRedeem plugin) {
        this.plugin = plugin;
        redisKey = plugin.getConfig().getString("Redis.KeyPrefix");
    }

    public String getData(UUID uuid, String data) {
        if(jedis == null) auth();

        String value = jedis.hget(redisKey + data, uuid.toString());
        return value == null ? "0" : value;
    }

    public void updateData(UUID uuid, int num, String column, String type) {
        if(jedis == null) auth();

        String key = redisKey + column;

        if (type.equalsIgnoreCase("set")) {
            jedis.hset(key, uuid.toString(), String.valueOf(num));
        } else if (type.equalsIgnoreCase("add") || type.equalsIgnoreCase("remove")) {
            jedis.hincrBy(key, uuid.toString(), num);
        }
    }

    public void shutdown() {
        if(jedis != null) {
            jedis.close();
            jedis = null;
        }
    }

    public void auth() {
        jedis = new Jedis(plugin.getConfig().getString("Redis.Hostname"), plugin.getConfig().getInt("Redis.Port"), plugin.getConfig().getBoolean("Redis.SSL"));
        jedis.auth(plugin.getConfig().getString("Redis.Password"));
    }
}
