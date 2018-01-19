package accessibility.cwdt.com.accessibility;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.content.Intent;
import android.os.Message;
import android.os.Parcelable;
import android.view.accessibility.AccessibilityEvent;


public class AccessibilityLogService extends AccessibilityService {


    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            Parcelable data = event.getParcelableData();
            String PackName = (String) event.getPackageName();
            Notification notification = (Notification) data;
            if (notification != null) {
                if (notification.tickerText != null) {
                    Accessibility_Handler Handler = new Accessibility_Handler(getApplicationContext());
                    Message msg = Handler.obtainMessage();
                    msg.obj = notification.tickerText.toString();
                    Handler.sendMessage(msg);
                }


            }
        }


    }


    /**
     * 必须重写的方法：系统要中断此service返回的响应时会调用。在整个生命周期会被调用多次。
     */
    @Override
    public void onInterrupt() {

    }

    /**
     * 服务开始连接
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    /**
     * 服务断开
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}