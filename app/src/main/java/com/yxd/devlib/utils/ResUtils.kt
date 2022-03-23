package com.yxd.devlib.utils

import android.content.Context

/**
 * 反射获取res资源工具类
 */
object ResUtils {

    /**
     * 获取资源文件的id
     *
     * @param resName
     * @return
     */
    fun getId(context:Context, resName: String): Int {
        return context.resources.getIdentifier(resName, "id", context.packageName)
    }

    /**
     * 获取资源文件中string的id
     *
     * @param resName
     * @return
     */
    fun getStringId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "string", context.packageName)
    }


    /**
     * 获取资源文件drawable的id
     *
     * @param resName
     * @return
     */
    fun getDrawableId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "drawable", context.packageName)
    }

    /**
     * 获取资源文件mipmap的id
     */
    fun getMipmapId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "mipmap", context.packageName)
    }

    fun getRawId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "raw", context.packageName)
    }


    /**
     * 获取资源文件layout的id
     *
     * @param resName
     * @return
     */
    fun getLayoutId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "layout", context.packageName)
    }


    /**
     * 获取资源文件style的id
     *
     * @param resName
     * @return
     */
    fun getStyleId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "style", context.packageName)
    }

    /**
     * 获取资源文件color的id
     *
     * @param resName
     * @return
     */
    fun getColorId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "color", context.packageName)
    }

    /**
     * 获取资源文件dimen的id
     *
     * @param resName
     * @return
     */
    fun getDimenId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "dimen", context.packageName)
    }

    /**
     * 获取资源文件anim的id
     *
     * @param context
     * @param resName
     * @return
     */
    fun getAnimId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "anim", context.packageName)
    }

    /**
     * 获取资源文件menu的id
     */
    fun getMenuId(context:Context, resName: String): Int {
        
        return context.resources.getIdentifier(resName, "menu", context.packageName)
    }


}
