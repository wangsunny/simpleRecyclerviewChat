package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import event.NetworkChangeEvent;
import util.NetUtils;

/**
 * Created by college on 2018/8/2.
 */

public class NetworkConnectChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //**判断当前的网络连接状态是否可用*/
        boolean isConnected = NetUtils.isConnected(context);
        Log.d("tag", "onReceive: 当前网络 " + isConnected);

        EventBus.getDefault().post(new NetworkChangeEvent(isConnected));
    }
}
