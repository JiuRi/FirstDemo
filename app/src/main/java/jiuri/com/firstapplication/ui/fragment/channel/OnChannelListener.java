package jiuri.com.firstapplication.ui.fragment.channel;

/**
 * Created by user103 on 2017/8/2.
 */

public interface OnChannelListener {
    void onItemMove(int starPos, int endPos);
    void onMoveToMyChannel(int starPos, int endPos);
    void onMoveToOtherChannel(int starPos, int endPos);
}
