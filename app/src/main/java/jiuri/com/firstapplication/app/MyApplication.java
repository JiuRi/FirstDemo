package jiuri.com.firstapplication.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by user103 on 2017/7/14.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //这个回调可用于检测应用内所有activity的生命周期!
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
