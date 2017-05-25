package org.siorven.services;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Service that keeps track of the user failed login attempts from the same IP to prevent brute force attacks
 */
@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    /**
     * Cache that keeps track of the IPs and their attempts
     */
    private final LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    /**
     * Deletes an IP from the {@link #attemptsCache} when the login is successful
     *
     * @param key The IP of the client
     */
    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    /**
     * Adds 1 to the {@link #attemptsCache} of the IP when the login fails
     *
     * @param key The IP of the client
     */
    public void loginFailed(String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    /**
     * Checks if an IP is blocked due to it's attemps exceeding the {@link #MAX_ATTEMPT} number
     *
     * @param key The IP of the client
     * @return Whether the IP is blocked or not
     */
    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}