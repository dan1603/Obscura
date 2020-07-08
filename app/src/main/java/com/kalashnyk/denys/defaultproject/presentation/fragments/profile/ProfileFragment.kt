package com.kalashnyk.denys.defaultproject.presentation.fragments.profile

import android.os.Bundle
import android.widget.Toast
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ProfileDataBinding
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow.ProfileFlow
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow.ProfileModel
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow.ProfileModelBinding
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.PageNavigationItem
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.TransitionAnimation
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.TransitionBundle
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.CategoryEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.LocationEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ProfileFragment : BaseFragment<ProfileDataBinding>(), ProfileFlow.ProfileListener {

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //ToDo get extras from bundle
        }
    }

    /**
     * @return
     */
    override fun getLayoutId(): Int = R.layout.fragment_profile

    /**
     * @param binding
     */
    override fun setupViewLogic(binding: ProfileDataBinding) {
        context?.let {
            binding.bindingModel = ProfileModelBinding(ProfileModel(getUser()), it)
            binding.clickHandler = this
        }
    }

    private fun getUser() : UserEntity{
        val user = UserEntity()
        user.surname = "Kalashnyk"
        user.name = "Denys"
        user.favoriteCategories = mutableListOf(CategoryEntity(1, "ART"), CategoryEntity(2, "Android"))
        user.location = LocationEntity(1, "Ukraine", "Dnepropetrovska", "Dnipro")
        user.occupation = "Android Developer"
        return UserEntity()
    }

    companion object {
        @JvmStatic
        fun newInstance(): ProfileFragment {
            return ProfileFragment()
        }
    }

    override fun onLogout() {
        Toast.makeText(context, "action logout in progress", Toast.LENGTH_LONG).show()
    }

    override fun openScreen(page: Pages) {
        when(page){
            Pages.EDIT_PERSONAL_DATA -> {
                Toast.makeText(context, "action open edit personal data in progress", Toast.LENGTH_LONG).show()
            }
            Pages.EDIT_PROFESSIONAL_DATA -> {
                Toast.makeText(context, "action open edit professional data in progress", Toast.LENGTH_LONG).show()
            }
            Pages.THEMES_CALENDAR -> {
                Toast.makeText(context, "action open themes calendar in progress", Toast.LENGTH_LONG).show()
            }
            Pages.CREATED_THEMES -> {
                Toast.makeText(context, "action open created themes in progress", Toast.LENGTH_LONG).show()
            }
            Pages.FOLLOWED_THEMES -> {
                Toast.makeText(context, "action open followed themes in progress", Toast.LENGTH_LONG).show()
            }
            Pages.APPLICATION_SETTINGS -> {
                Toast.makeText(context, "action open application settings in progress", Toast.LENGTH_LONG).show()
            }
        }
//        getBaseActivity().goToPage(PageNavigationItem(page), TransitionBundle(TransitionAnimation.ENTER_FROM_RIGHT))
    }
}
