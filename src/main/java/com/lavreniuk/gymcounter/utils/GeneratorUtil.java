package com.lavreniuk.gymcounter.utils;

import java.util.UUID;

/**
 * @author taras
 * @date 03.06.18.
 */
public class GeneratorUtil {

    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
