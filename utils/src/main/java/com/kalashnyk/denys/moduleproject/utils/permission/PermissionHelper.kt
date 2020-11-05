package com.kalashnyk.denys.moduleproject.utils.permission

import android.Manifest
import android.app.Activity
import com.kalashnyk.denys.moduleproject.utils.logger.Logger
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import io.reactivex.functions.Consumer

object PermissionHelper {
    const val REQUEST_CODE = 9876

    private fun checkPermission(
        activity: Activity,
        needShowManuallyDialog: Boolean,
        permissionsGranted: Consumer<Boolean?>?,
        vararg permissions: String
    ) {
        Dexter.withActivity(activity)
            .withPermissions(permissions.asList())
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    if (report.areAllPermissionsGranted()) callConsumer(
                        permissionsGranted,
                        true
                    ) else {
                        callConsumer(permissionsGranted, false)
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

            }).withErrorListener({ error: DexterError -> Logger.e(this::class, error.name) })
            .onSameThread().check()
    }

    private fun callConsumer(
        permissionsGranted: Consumer<Boolean?>?,
        isGranted: Boolean
    ) {
        if (permissionsGranted != null) {
            try {
                permissionsGranted.accept(isGranted)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun checkDeviceDataPermission(
        activity: Activity,
        permissionsGranted: Consumer<Boolean?>?
    ) {
        checkPermission(activity, true, permissionsGranted, Manifest.permission.READ_PHONE_STATE)
    }

    fun checkAccountPermission(
        activity: Activity,
        permissionsGranted: Consumer<Boolean?>?
    ) {
        checkPermission(activity, true, permissionsGranted, Manifest.permission.ACCOUNT_MANAGER)
    }

    fun checkLocationPermission(
        activity: Activity,
        permissionsGranted: Consumer<Boolean?>?
    ) {
        checkPermission(
            activity,
            true,
            permissionsGranted,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    fun checkPhonePermission(
        activity: Activity,
        permissionsGranted: Consumer<Boolean?>?
    ) {
        checkPermission(activity, true, permissionsGranted, Manifest.permission.CALL_PHONE)
    }
}