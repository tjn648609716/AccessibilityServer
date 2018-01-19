package accessibility.cwdt.com.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.accessibility.AccessibilityManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AccessibilityManager.AccessibilityStateChangeListener {


    private AccessibilityManager accessibilityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //监听AccessibilityService 变化
        accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        accessibilityManager.addAccessibilityStateChangeListener(this);
    }

    @Override
    public void onAccessibilityStateChanged(boolean enabled) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isServiceEnabled()) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("提示")
                    .setMessage("请前往辅助模式开启辅助")
                    .setPositiveButton("我不想用了", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("去开启", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            openAccessibility();
                        }
                    }).show();
        }
    }

    public void openAccessibility() {
        try {
            Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(accessibleIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取 Service 是否启用状态
     *
     * @return
     */
    private boolean isServiceEnabled() {
        List<AccessibilityServiceInfo> accessibilityServices =
                accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_GENERIC);
        for (AccessibilityServiceInfo info : accessibilityServices) {
            if (info.getId().equals(getPackageName() + "/.AccessibilityLogService")) {
                return true;
            }
        }
        return false;
    }
}
