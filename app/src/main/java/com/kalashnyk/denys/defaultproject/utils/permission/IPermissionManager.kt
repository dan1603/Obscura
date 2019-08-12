package com.kalashnyk.denys.defaultproject.utils.permission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.ArrayList


/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
interface IPermissionManager {

    /**
     * @param activity
     * @param permissions
     */
    fun hasPermission(activity: AppCompatActivity, permissions: Array<String>): Boolean

    /**
     * @param activity
     * @param requestCode
     * @param permissions
     */
    fun requestPermission(activity: AppCompatActivity, requestCode: Int, permissions: Array<String>)

    /**
     * @param activity
     * @param permissions
     */
    fun checkPermissionRationale(activity: AppCompatActivity, permissions: Array<String>): Boolean
}

/**
 *
 */
class PermissionManagerImpl : IPermissionManager {

    override fun hasPermission(
        activity: AppCompatActivity,
        permissions: Array<String>
    ): Boolean {
        val list = ArrayList<Boolean>()
        permissions.forEach {
            list.add(ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED)
        }
        return list.all { it }
    }

    override fun checkPermissionRationale(
        activity: AppCompatActivity,
        permissions: Array<String>
    ): Boolean {
        val list = ArrayList<Boolean>()
        permissions.forEach {
            list.add(ActivityCompat.shouldShowRequestPermissionRationale(activity, it))
        }
        return list.all { it }
    }

    override fun requestPermission(
        activity: AppCompatActivity,
        requestCode: Int,
        permissions: Array<String>
    ): Unit = ActivityCompat.requestPermissions(activity, permissions, requestCode)

}