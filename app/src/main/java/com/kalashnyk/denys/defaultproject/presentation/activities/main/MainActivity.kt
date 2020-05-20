package com.kalashnyk.denys.defaultproject.presentation.activities.main

import android.app.Activity
import android.content.Intent
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.MainDataBinding
import com.kalashnyk.denys.defaultproject.di.component.ViewModelComponent
import com.kalashnyk.denys.defaultproject.domain.AllUsersViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseActivity
import com.kalashnyk.denys.defaultproject.presentation.navigation.BottomNavigationHandler
import com.kalashnyk.denys.defaultproject.utils.BUNDLE_LOCATION_LAT
import com.kalashnyk.denys.defaultproject.utils.BUNDLE_LOCATION_LNG
import com.kalashnyk.denys.defaultproject.utils.REQUEST_CODE_LOCATION
import com.kalashnyk.denys.defaultproject.utils.extention.showSnack
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class MainActivity : BaseActivity<MainDataBinding>() {

    var viewModel: AllUsersViewModel? = null @Inject set

    /**
     * @param component
     */
    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }
    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.activity_main

    /**
     * @param binder
     */
    override fun setupViewLogic(binder: MainDataBinding) {
        BottomNavigationHandler(
            binder.bottomNavigation,
            binder.mainFragmentContainer,
            supportActionBar,
            supportFragmentManager
        )
        binder.fabMap.setOnClickListener {
            navigator.openLocationChooser()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_LOCATION -> {
                if (resultCode == Activity.RESULT_OK) {
                    showSnack(getString(R.string.location_chosen_text) + " " + data?.extras?.getDouble(BUNDLE_LOCATION_LAT) + ", " + data?.extras?.getDouble(BUNDLE_LOCATION_LNG))
                } else {
                    showSnack(R.string.location_chosen_err)
                }
            }
        }
    }
}
