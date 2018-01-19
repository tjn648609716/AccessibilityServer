package accessibility.cwdt.com.accessibility;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class Accessibility_Handler extends Handler {
    private Context MyContext;

    public Accessibility_Handler(Context context) {
        this.MyContext = context;
    }

    @Override
    public void handleMessage(Message msg) {

        Toast.makeText(MyContext, msg.obj.toString(), Toast.LENGTH_LONG).show();
    }


}