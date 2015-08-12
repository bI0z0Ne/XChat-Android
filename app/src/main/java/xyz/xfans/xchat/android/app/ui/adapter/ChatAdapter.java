package xyz.xfans.xchat.android.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import xyz.xfans.xchat.android.app.R;
import xyz.xfans.xchat.android.app.entity.ChatInfo;
import xyz.xfans.xchat.android.app.entity.UserInfo;

import java.util.List;

/**
 * Created by zhu on 2015/8/12.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<ChatInfo> mDataSet;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener{
        void OnItemClickListener(View v,int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final CircleImageView circleImageView;
        private final RelativeLayout relativeLayout;

        public ViewHolder(View v) {
            super(v);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.rl);
            textView = (TextView) v.findViewById(R.id.title);
            circleImageView = (CircleImageView) v.findViewById(R.id.avatar);
        }

        public RelativeLayout getRelativeLayout() {
            return relativeLayout;
        }

        public TextView getTextView() {
            return textView;
        }

        public CircleImageView getCircleImageView() {
            return circleImageView;
        }
    }

    public ChatAdapter(List<ChatInfo> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
