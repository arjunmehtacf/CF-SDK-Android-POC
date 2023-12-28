package com.example.cf_sdk.logic.sdk_module.singleton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import com.example.cf_sdk.BuildConfig;
import com.example.cf_sdk.logic.changebanksdk.model.Session;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 *
 * Gets information related to the current device.
 */

public class DeviceUtil {
    /**
     * Get device finger print for safety check purpose.
     *
     * @param context - Context
     * @return - Device finger print id.
     */
    @SuppressLint("HardwareIds") // This id is used to prevent fraud.
    public static String getDeviceFingerPrintID(Context context) {
        if (context != null) {
            String androidId = Settings.Secure.getString(
                    context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);

            androidId = Build.MANUFACTURER.toUpperCase() + "-" +
                    Build.MODEL + "-" +
                    androidId;

            return androidId;
        }
        return "";
    }





    public static String getOsVersion() {
        return "android-" + Build.VERSION.RELEASE;
    }

    /**
     * Get IP address from first non-localhost interface
     *
     * @param useIpv4 - boolean
     * @return - String
     */
    public static String getIpAddress(boolean useIpv4) {
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            List<NetworkInterface> interfaces = Collections.list(enumeration);
            for (NetworkInterface networkInterface : interfaces) {
                Enumeration<InetAddress> iNetAddressEnum = networkInterface.getInetAddresses();
                List<InetAddress> iNetAddList = Collections.list(iNetAddressEnum);
                for (InetAddress iNetAdd : iNetAddList) {
                    if (!iNetAdd.isLoopbackAddress()) {
                        String hostAdd = iNetAdd.getHostAddress().toUpperCase();
                        boolean isIpv4 = iNetAdd instanceof Inet4Address;
                        if (useIpv4) {
                            if (isIpv4)
                                return hostAdd;
                        } else {
                            if (!isIpv4) {
                                int delimiter = hostAdd.indexOf('%'); // drop ip6 port suffix
                                return delimiter < 0 ? hostAdd : hostAdd.substring(0, delimiter);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getOriginHeader() {

        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("debug")) {
            return "test.changefinancialapp.com";
        }

        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("qa")) {
            return "qa.changefinancialapp.com";
        }

        if (BuildConfig.BUILD_TYPE.equalsIgnoreCase("release")) {
            return "production.changefinancialapp.com";
        }

        return "*.changefinancialapp.com";
    }

  public static String programCode = "QU";
}
