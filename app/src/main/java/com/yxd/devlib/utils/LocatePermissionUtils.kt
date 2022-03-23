package com.yxd.devlib.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment


object LocatePermissionUtils {

    private const val PERMISSION_REQUEST_CODE = 124
    private const val GPS_REQ_CODE = 123

    fun doInOnRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                // 权限被拒绝
            }
        }
    }

    fun checkPermissions(fragment:Fragment, onUnPassed: () -> Unit) {
        val permissions = mutableListOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        val mPermissionRejectedList = ArrayList<String>()
        for (i in permissions.indices) {
            if (ContextCompat.checkSelfPermission(
                    fragment.requireContext(),
                    permissions[i]
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                mPermissionRejectedList.add(permissions[i])
            }
        }
        if (mPermissionRejectedList.isEmpty()) {
            onUnPassed.invoke()
        } else {
            val permissions = mPermissionRejectedList.toTypedArray() // 将List转为数组
            fragment.requestPermissions(permissions, PERMISSION_REQUEST_CODE)
        }
    }

    fun isLocationEnabled(context: Context): Boolean {
        val locationMode = try {
            Settings.Secure.getInt(context.contentResolver, Settings.Secure.LOCATION_MODE)
        } catch (e: Settings.SettingNotFoundException) {
            e.printStackTrace()
            return false
        }
        return locationMode != Settings.Secure.LOCATION_MODE_OFF
    }

    /**
     * 打开设置的位置权限界面
     */
    fun goToOpenLocationPermission(fragment: Fragment, reqCode:Int = GPS_REQ_CODE){
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        //GPS_REQ_CODE是请求码，可以在onActivityResult中根据该请求码判断用户是否已经在设置页面打开位置服务
        fragment.startActivityForResult(intent, reqCode)
    }

    fun doInOnActivityResult(context: Context, requestCode: Int, onGpsOpened:()->Unit, onGpsClosed:()->Unit){
        if(requestCode == GPS_REQ_CODE){
            if(isLocationEnabled(context)){
                onGpsOpened.invoke()
            } else {
                onGpsClosed.invoke()
            }
        }
    }

}