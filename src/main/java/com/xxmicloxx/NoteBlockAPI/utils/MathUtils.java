package com.xxmicloxx.NoteBlockAPI.utils;

import org.bukkit.Location;

public class MathUtils {
    static double[] cos = new double[360];
    static double[] sin = new double[360];

    static {
        for (int deg = 0; deg < 360; deg++) {
            cos[deg] = Math.cos(Math.toRadians(deg));
            sin[deg] = Math.sin(Math.toRadians(deg));
        }
    }

    static double[] getCos() {
        return cos;
    }

    static double[] getSin() {
        return sin;
    }

    public static Location stereoSourceLeft(Location location, float distance) {
        int angle = getAngle(location.getYaw());
        return location.clone().add(-getCos()[angle] * distance, 0, -getSin()[angle] * distance);
    }

    public static Location stereoSourceRight(Location location, float distance) {
        int angle = getAngle(location.getYaw());
        return location.clone().add(getCos()[angle] * distance, 0, getSin()[angle] * distance);
    }

    /**
     * Calculate new location for stereo
     *
     * @param location origin location
     * @param distance negative for left side, positive for right side
     */
    public static Location stereoPan(Location location, float distance) {
        int angle = getAngle(location.getYaw());
        return location.clone().add(getCos()[angle] * distance, 0, getSin()[angle] * distance);
    }

    static int getAngle(float yaw) {
        int angle = (int) yaw;
        while (angle < 0) angle += 360;
        return angle % 360;
    }
}