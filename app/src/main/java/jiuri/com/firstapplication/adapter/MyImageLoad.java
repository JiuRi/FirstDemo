package jiuri.com.firstapplication.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * Created by user103 on 2017/7/24.
 */

public class MyImageLoad extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(Integer.parseInt(path.toString()));

    }
}
