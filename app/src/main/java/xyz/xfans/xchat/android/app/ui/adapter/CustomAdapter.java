/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package xyz.xfans.xchat.android.app.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import xyz.xfans.xchat.android.app.R;
import xyz.xfans.xchat.android.app.entity.UserInfo;

import java.util.List;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private List<UserInfo> mDataSet;
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

    public CustomAdapter(List<UserInfo> dataSet) {
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
        UserInfo userInfo = mDataSet.get(position);
        viewHolder.getTextView().setText(userInfo.getName());
        viewHolder.getCircleImageView().setImageResource(R.drawable.avatar_empty);
        // Define click listener for the ViewHolder's View.
        viewHolder.getRelativeLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Element " + position + " clicked.");
                onClickListener.OnItemClickListener(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
