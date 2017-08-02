package jiuri.com.firstapplication.app;

import android.graphics.Canvas;

/**
 * @author shenxiaolei
 *
 */
public class Constants {

    /**
     * 记录播放位置
     */
    public static int playPosition=-1;

    private static  Canvas canvas;

    public static Canvas getCanvas() {
        return canvas;
    }

    public static void setCanvas(Canvas canvas) {
        Constants.canvas = canvas;
    }
    public static String KEY_MY_CHANNEL="mc";
    public static String KEY_OTHER_CHANNEL="oc";
    public static String KEY_PUSH="swish";

}
