package com.wyxx.android.xuexi.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流
 */
public class IOUtil {

    public static void close(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
