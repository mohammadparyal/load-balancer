package com.example.loadbalancer.module;

import com.example.loadbalancer.exception.BackendHostsMaxAllowedException;
import com.example.loadbalancer.exception.BackendHostsNotFoundException;
import com.example.loadbalancer.exception.RemoveHostsExceptionException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BackendHostsPool {

    /**
     * Host name : IsActive
     */
    private static final Map<String, Boolean> BACKEND_HOSTS_POOL_MAP;
    public static final int                         MAX_HOSTS_CAPACITY_ALLOWED = 10;

    static {
        BACKEND_HOSTS_POOL_MAP = new ConcurrentHashMap<>(MAX_HOSTS_CAPACITY_ALLOWED);
        BACKEND_HOSTS_POOL_MAP.put("https://www.google.com/api/v1/public", true);
        BACKEND_HOSTS_POOL_MAP.put("https://www.google.com/api/v2/internal", true);
    }


    public static Map<String, Boolean> getBackendHostsMap() {
        return BACKEND_HOSTS_POOL_MAP;
    }

    /**
     * return only active hosts
     * @return
     */
    public static List<String> getBackendHostsList() {
        return BACKEND_HOSTS_POOL_MAP.entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * Remove backend host from pool.
     * May return {@link RemoveHostsExceptionException}
     *
     * @param backedHostName ip.
     */
    public static void remove(String backedHostName) {
        Boolean val = BackendHostsPool.getBackendHostsMap().remove(backedHostName);
        if (val == null) {
            throw new RemoveHostsExceptionException(backedHostName);
        }
    }

    /**
     * disable backend host from pool.
     * May return {@link BackendHostsNotFoundException}
     *
     * @param backedHostName ip.
     */
    public static void disable(String backedHostName) {
        var backendHost = BACKEND_HOSTS_POOL_MAP.getOrDefault(backedHostName, null);
        if (backendHost == null) {
            throw new BackendHostsNotFoundException(backedHostName);
        }

        BACKEND_HOSTS_POOL_MAP.computeIfPresent(backedHostName, (key, oldValue) -> false);
    }

    /**
     * enable backend host from pool.
     * May return {@link BackendHostsNotFoundException}
     *
     * @param backedHostName ip.
     */
    public static void enable(String backedHostName) {
        var backendHost = BACKEND_HOSTS_POOL_MAP.getOrDefault(backedHostName, null);
        if (backendHost == null) {
            throw new BackendHostsNotFoundException(backedHostName);
        }

        BACKEND_HOSTS_POOL_MAP.computeIfPresent(backedHostName, (key, oldValue) -> true);
    }

    /**
     * Add new backend host to the pool.
     * May return {@link BackendHostsMaxAllowedException} if max capacity is reached.
     *
     * @param backedHostName ip.
     */
    public static void add(String backedHostName) {
        if (BACKEND_HOSTS_POOL_MAP.size() < MAX_HOSTS_CAPACITY_ALLOWED) {
            BACKEND_HOSTS_POOL_MAP
                    .putIfAbsent(backedHostName, true);
        } else {
            throw new BackendHostsMaxAllowedException(backedHostName);
        }
    }
}