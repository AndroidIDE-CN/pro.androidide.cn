package com.api.aidepro.service;

import com.api.aidepro.console.MD5Encoder;
import org.springframework.stereotype.Service;
import com.api.aidepro.console.TimeFormatUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public TokenService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void removeUserToken(long uid) {
        String cachedToken = redisTemplate.opsForValue().get("aide-userToken:" + uid);
        if (cachedToken != null) redisTemplate.delete("aide-userToken:" + uid);
    }

    public String generateUserToken(long uid, String username, String password) {
        String formatTime = new TimeFormatUtils().formatTime("yyyy-MM-dd HH:mm:ss");
        String enCodeString = UUID.randomUUID().toString() + System.currentTimeMillis();
        String token = new MD5Encoder().toMD5(uid + username + enCodeString + formatTime + password);
        redisTemplate.opsForValue().set("aide-userToken:" + uid, token, 30, TimeUnit.DAYS);
        return token;
    }

    public Boolean validUserToken(long uid, String oldToken) {
        String cachedToken = redisTemplate.opsForValue().get("aide-userToken:" + uid);
        if (cachedToken != null)  {
            return cachedToken.equals(oldToken);
        } else return false;
    }
}
