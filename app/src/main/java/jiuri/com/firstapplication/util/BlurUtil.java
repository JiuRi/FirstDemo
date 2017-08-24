package jiuri.com.firstapplication.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.annotation.RequiresApi;
import android.view.View;

/**
 * 用于处理图片 把图片 进行高斯模糊 操作
 * Created by user103 on 2017/8/24.
 */

public class BlurUtil {
    private View  mView ;
    public BlurUtil(View view) {
        this.mView=view;
        initView();
    }

    private void initView() {
        mView.destroyDrawingCache();// 清除自之前的 缓存
        mView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(mView.getDrawingCache());
        //上面 获取的是某一个View 的 镜像 ；
        /**
         * 当我们需要对 这个 bitmaP 进行 莫一部风 图片的需求是 需要对图片 进行 切割
         *Bitmap.createBitmap(bitmap, x, y, width, height);
         * 上面几个 参数分别是 切割的 bitmap 对象   切割的起始坐标 x , y ; width 表示切割的宽度 ，height 表示切割的高度
         * 这四个参数 决定了 我们 将要 切割 多大的范围。
         */
          //   Bitmap bitmap1 = Bitmap.createBitmap(bitmap, x, y, width, height);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // blur 操作 传递一个  bitmap 对象 然后 在传递一个 模糊程度 系数
            Bitmap blur = blur(bitmap, 18f);
            //将 bitmap 对象 转化为 drawable 对象。
            BitmapDrawable bitmapDrawable = new BitmapDrawable(blur);

        }

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private Bitmap blur(Bitmap bitmap, float radius) {
        Bitmap output = Bitmap.createBitmap(bitmap); // 创建输出图片
        RenderScript rs = RenderScript.create(mView.getContext()); // 构建一个RenderScript对象
        ScriptIntrinsicBlur gaussianBlue = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs)); // 创建高斯模糊脚本
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap); // 创建用于输入的脚本类型
        Allocation allOut = Allocation.createFromBitmap(rs, output); // 创建用于输出的脚本类型
        gaussianBlue.setRadius(radius); // 设置模糊半径，范围0f<radius<=25f
        gaussianBlue.setInput(allIn); // 设置输入脚本类型
        gaussianBlue.forEach(allOut); // 执行高斯模糊算法，并将结果填入输出脚本类型中
        allOut.copyTo(output); // 将输出内存编码为Bitmap，图片大小必须注意
        rs.destroy(); // 关闭RenderScript对象，API>=23则使用rs.releaseAllContexts()
        return output;
    }
}
