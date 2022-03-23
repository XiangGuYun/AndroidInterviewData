package com.yxd.devlib.permission

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yxd.devlib.base.TestFragment
import com.yxd.devlib.utils.LocatePermissionUtils


class PermissionFragment : TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        addButton("Apply For Permissions") {
            LocatePermissionUtils.checkPermissions(this) {}
        }

        addButton("is Location Enabled"){
            toast(LocatePermissionUtils.isLocationEnabled(requireContext()).toString())
        }

        addButton("Enable GPS"){
            LocatePermissionUtils.goToOpenLocationPermission(this)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        LocatePermissionUtils.doInOnRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        LocatePermissionUtils.doInOnActivityResult(requireContext(), requestCode, {

        }){

        }
    }

}