package org.geektimes.utils;

import org.eclipse.microprofile.config.Config;

/**
 * threa local
 *
 * @author ViJay
 * @date 2021/3/21 17:00
 */
public final class ThreadConfigHolder {
    private static final ThreadLocal<Config> LOCAL = new ThreadLocal<>();

    public static Config getConfig() {
        return LOCAL.get();
    }

    public static void setConfig(Config config) {
        LOCAL.set(config);
    }

    public static void release() {
        LOCAL.remove();
    }
}
