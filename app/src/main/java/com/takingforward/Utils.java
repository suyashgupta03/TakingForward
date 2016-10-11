package com.takingforward;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by suyashg on 11/10/16.
 */
public class Utils {

    public static boolean isConnected(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
