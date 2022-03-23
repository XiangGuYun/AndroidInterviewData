package com.yxd.devlib.utils;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;

/**
 * 剪贴板相关工具类
 *
 * copyText             : 复制文本到剪贴板
 * getText              : 获取剪贴板的文本
 * copyUri              : 复制 uri 到剪贴板
 * getUri               : 获取剪贴板的 uri
 * copyIntent           : 复制意图到剪贴板
 * getIntent            : 获取剪贴板的意图
 * addChangedListener   : 增加剪贴板监听器
 * removeChangedListener: 移除剪贴板监听器
 */
public final class ClipboardUtils {

    private ClipboardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Copy the text to clipboard.
     * <p>The label equals name of package.</p>
     *
     * @param text The text.
     */
    public static void copyText(Context context, final CharSequence text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newPlainText(context.getPackageName(), text));
    }

    /**
     * Copy the text to clipboard.
     *
     * @param label The label.
     * @param text  The text.
     */
    public static void copyText(Context context, final CharSequence label, final CharSequence text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newPlainText(label, text));
    }

    /**
     * Clear the clipboard.
     */
    public static void clear(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.setPrimaryClip(ClipData.newPlainText(null, ""));
    }

    /**
     * Return the label for clipboard.
     *
     * @return the label for clipboard
     */
    public static CharSequence getLabel(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipDescription des = cm.getPrimaryClipDescription();
        if (des == null) {
            return "";
        }
        CharSequence label = des.getLabel();
        if (label == null) {
            return "";
        }
        return label;
    }

    /**
     * Return the text for clipboard.
     *
     * @return the text for clipboard
     */
    public static CharSequence getText(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        ClipData clip = cm.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            CharSequence text = clip.getItemAt(0).coerceToText(context);
            if (text != null) {
                return text;
            }
        }
        return "";
    }

    /**
     * Add the clipboard changed listener.
     */
    public static void addChangedListener(Context context, final ClipboardManager.OnPrimaryClipChangedListener listener) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.addPrimaryClipChangedListener(listener);
    }

    /**
     * Remove the clipboard changed listener.
     */
    public static void removeChangedListener(Context context, final ClipboardManager.OnPrimaryClipChangedListener listener) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //noinspection ConstantConditions
        cm.removePrimaryClipChangedListener(listener);
    }
}
