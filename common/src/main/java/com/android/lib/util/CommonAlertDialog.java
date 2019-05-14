package com.android.lib.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.common.R;

/**
 * date: 2019/1/30
 * desc: 公用的弹框
 */
public class CommonAlertDialog {
    public static class Builder {

        private Context mContext;
        private Dialog mDialog;
        private ViewHolder mViewHolder;

        private View mView;
        private boolean hasPos = false, hasNeg = false;

        public Builder(Activity context) {
            mContext = context;
            initView();
        }

        /**
         * 设置title
         *
         * @param title
         * @return builder
         */
        public Builder setTitle(CharSequence title) {
            mViewHolder.tvTitle.setText(title);
            return this;
        }

        /**
         * @param title 显示的文字
         * @param color 显示的文字的颜色
         * @return builder
         */
        public Builder setTitle(CharSequence title, int color) {
            mViewHolder.tvTitle.setText(title);
            mViewHolder.tvTitle.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        /**
         * 设置title
         *
         * @param resId 显示的文字id
         * @return builder
         */
        public Builder setTitle(int resId) {
            mViewHolder.tvTitle.setText(resId);
            return this;
        }

        /**
         * 设置title
         *
         * @param resId 显示的文字id
         * @param color 显示的文字的颜色
         * @return builder
         */
        public Builder setTitle(int resId, int color) {
            mViewHolder.tvTitle.setText(resId);
            mViewHolder.tvTitle.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        /**
         * 设置显示内容
         *
         * @param title 显示内容
         * @return builder
         */
        public Builder setMessage(CharSequence title) {
            mViewHolder.tvMessage.setText(title);
            return this;
        }

        /**
         * 设置显示内容
         *
         * @param title 显示内容
         * @param color 显示内容颜色
         * @return builder
         */
        public Builder setMessage(CharSequence title, int color) {
            mViewHolder.tvMessage.setText(title);
            mViewHolder.tvMessage.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        /**
         * 设置显示内容
         *
         * @param resId 显示内容id
         * @return builder
         */
        public Builder setMessage(int resId) {
            mViewHolder.tvMessage.setText(resId);
            return this;
        }

        /**
         * 设置显示内容
         *
         * @param resId 显示内容id
         * @param color 显示内容颜色
         * @return builder
         */
        public Builder setMessage(int resId, int color) {
            mViewHolder.tvMessage.setText(resId);
            mViewHolder.tvMessage.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        /**
         * 确定按钮
         *
         * @param listener 按钮监听
         * @return builder
         */
        public Builder setPositiveButton(final View.OnClickListener listener) {
            mViewHolder.tvCenterButton.setVisibility(View.GONE);
            mViewHolder.tvPositiveButton.setVisibility(View.VISIBLE);
            hasPos = true;
            mViewHolder.tvPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        /**
         * 确定按钮
         *
         * @param text     按钮文字
         * @param listener 按钮监听
         * @return builder
         */
        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.tvCenterButton.setVisibility(View.GONE);
            mViewHolder.tvPositiveButton.setVisibility(View.VISIBLE);
            hasPos = true;
            mViewHolder.tvPositiveButton.setText(text);
            mViewHolder.tvPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        /**
         * 确定按钮
         *
         * @param text     按钮文字
         * @param listener 按钮监听
         * @param color    按钮文字颜色
         * @return builder
         */
        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener, int color) {
            mViewHolder.tvCenterButton.setVisibility(View.GONE);
            mViewHolder.tvPositiveButton.setVisibility(View.VISIBLE);
            hasPos = true;
            mViewHolder.tvPositiveButton.setText(text);
            mViewHolder.tvPositiveButton.setTextColor(ContextCompat.getColor(mContext, color));
            mViewHolder.tvPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        /**
         * 取消按钮
         *
         * @param listener 按钮监听
         * @return builder
         */
        public Builder setNegativeButton(final View.OnClickListener listener) {
            mViewHolder.tvCenterButton.setVisibility(View.GONE);
            mViewHolder.tvNegativeButton.setVisibility(View.VISIBLE);
            hasNeg = true;
            mViewHolder.tvNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        /**
         * 取消按钮
         *
         * @param text     按钮文字
         * @param listener 按钮监听
         * @return builder
         */
        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.tvCenterButton.setVisibility(View.GONE);
            mViewHolder.tvNegativeButton.setVisibility(View.VISIBLE);
            hasNeg = true;
            mViewHolder.tvNegativeButton.setText(text);
            mViewHolder.tvNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        /**
         * 取消按钮
         *
         * @param text     按钮文字
         * @param listener 按钮监听
         * @param color    按钮文字颜色
         * @return builder
         */
        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener, int color) {
            mViewHolder.tvCenterButton.setVisibility(View.GONE);
            mViewHolder.tvNegativeButton.setVisibility(View.VISIBLE);
            hasNeg = true;
            mViewHolder.tvNegativeButton.setText(text);
            mViewHolder.tvNegativeButton.setTextColor(ContextCompat.getColor(mContext, color));
            mViewHolder.tvNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        /**
         * 显示一个按钮的弹窗
         *
         * @param listener 按钮监听
         * @return builder
         */
        public Builder setShowOneButton(final View.OnClickListener listener) {
            mViewHolder.tvNegativeButton.setVisibility(View.GONE);
            mViewHolder.tvPositiveButton.setVisibility(View.GONE);
            mViewHolder.line2.setVisibility(View.GONE);
            mViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
            mViewHolder.tvCenterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            });
            return this;
        }

        /**
         * 显示一个按钮的弹窗
         *
         * @param text     按钮文字
         * @param listener 按钮监听
         * @return builder
         */
        public Builder setShowOneButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.tvNegativeButton.setVisibility(View.GONE);
            mViewHolder.tvPositiveButton.setVisibility(View.GONE);
            mViewHolder.line2.setVisibility(View.GONE);
            mViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
            mViewHolder.tvCenterButton.setText(text);
            mViewHolder.tvCenterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            });
            return this;
        }

        /**
         * 显示一个按钮的弹窗
         *
         * @param text     按钮文字
         * @param listener 按钮监听
         * @param color    按钮文字颜色
         * @return builder
         */
        public Builder setShowOneButton(CharSequence text, final View.OnClickListener listener, int color) {
            mViewHolder.tvNegativeButton.setVisibility(View.GONE);
            mViewHolder.tvPositiveButton.setVisibility(View.GONE);
            mViewHolder.line2.setVisibility(View.GONE);
            mViewHolder.tvCenterButton.setVisibility(View.VISIBLE);
            mViewHolder.tvCenterButton.setText(text);
            mViewHolder.tvCenterButton.setTextColor(ContextCompat.getColor(mContext, color));
            mViewHolder.tvCenterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (listener != null) {
                        listener.onClick(v);
                    }
                }
            });
            return this;
        }

        /**
         * 返回键是否可取消
         *
         * @param flag
         * @return builder
         */
        public Builder setCancelable(boolean flag) {
            mDialog.setCancelable(flag);
            return this;
        }

        /**
         * 点击其它空白位置是否可取消
         *
         * @param flag
         * @return builder
         */
        public Builder setCanceledOnTouchOutside(boolean flag) {
            mDialog.setCanceledOnTouchOutside(flag);
            return this;
        }

        public Dialog create() {
            return mDialog;
        }

        /**
         * 显示dialog
         */
        public void show() {
            if (mDialog != null) {
                if (hasPos || hasNeg) {
                    mViewHolder.line1.setVisibility(View.VISIBLE);
                }
                if (hasPos && hasNeg) {
                    mViewHolder.line2.setVisibility(View.VISIBLE);
                }
                mDialog.show();
            }
        }

        /**
         * 取消dialog
         */
        public void dismiss() {
            if (mDialog != null) {
                mDialog.dismiss();
                mDialog = null;
            }
        }

        /**
         * 初始化dialog布局及控件
         */
        private void initView() {
            mDialog = new Dialog(mContext, R.style.common_alert_dialog_style);
            mView = LayoutInflater.from(mContext).inflate(R.layout.commo_alert_dialog_layout, null);
            mViewHolder = new ViewHolder(mView);
            mDialog.setContentView(mView);

            DisplayMetrics dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
            WindowManager.LayoutParams lp = mDialog.getWindow().getAttributes();
            lp.width = (int) (dm.widthPixels * 0.8);
            //lp.height = (int) (dm.widthPixels * 0.8);
            mDialog.getWindow().setAttributes(lp);
        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvMessage;
            TextView tvPositiveButton, tvNegativeButton, tvCenterButton;
            //            LinearLayout vgLayout;
            View line1, line2;

            public ViewHolder(View view) {
                tvTitle = (TextView) view.findViewById(R.id.dialog_title);
                tvMessage = (TextView) view.findViewById(R.id.dialog_message);
                tvPositiveButton = (TextView) view.findViewById(R.id.dialog_positive);
                tvNegativeButton = (TextView) view.findViewById(R.id.dialog_negative);
                tvCenterButton = (TextView) view.findViewById(R.id.tv_alert_one_confirm);
//                vgLayout = view.findViewById(R.id.dialog_layout);
                line1 = view.findViewById(R.id.dialog_line1);
                line2 = view.findViewById(R.id.dialog_line2);
            }

        }
    }
}
