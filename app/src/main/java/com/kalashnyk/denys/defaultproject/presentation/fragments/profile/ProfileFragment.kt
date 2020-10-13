package com.kalashnyk.denys.defaultproject.presentation.fragments.profile

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.kalashnyk.denys.defaultproject.App
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.databinding.ProfileDataBinding
import com.kalashnyk.denys.defaultproject.domain.SingleProfileViewModel
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow.ProfileFlow
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow.ProfileModel
import com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow.ProfileModelBinding
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.CategoryEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.LocationEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity
import javax.inject.Inject

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
class ProfileFragment : BaseFragment<ProfileDataBinding>(), ProfileFlow.ProfileListener {

    /**
     *
     */
    var viewModel: SingleProfileViewModel? = null
        @Inject set

    /**
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.apply { (application as App).getViewModelComponent().inject(this@ProfileFragment) }
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
            val id = arguments?.getInt(USER_ID, -1) ?: -1
            if(id == -1){
                binding.isOwner = true
                binding.bindingModel = ProfileModelBinding(ProfileModel(getUser()), it)
            }
            else {
                binding.isOwner = false
                viewModel?.initUser(id)
                viewModel?.userModel?.observe(this, Observer {profileModel ->
                    binding.bindingModel = ProfileModelBinding(profileModel, it)
                })
            }
            binding.clickHandler = this
        }
    }

    private fun getUser() : UserEntity{
        val user = UserEntity(509)
        user.surname = "Kalashnyk"
        user.name = "Denys"
        user.avatarPreview = "https://home.caresolace.com/wp-content/uploads/2019/04/adult-beach-casual-736716.jpg"
        user.favoriteCategories = mutableListOf(CategoryEntity(1, "ART"), CategoryEntity(2, "Android"))
        user.location = LocationEntity(1, "Ukraine", "Dnepropetrovska", "Dnipro")
        user.occupation = "Android Developer"
        return user
    }

    companion object {

        const val USER_ID = "user_id"

        @JvmStatic
        fun newInstance(userId: Int? = null): ProfileFragment {
            return ProfileFragment().apply {
                userId?.let {
                    arguments = Bundle().apply { putInt(USER_ID, userId) }
                }
            }
        }
    }

    override fun onLogout() {
        Toast.makeText(context, "action logout in progress", Toast.LENGTH_LONG).show()
    }

    override fun openScreen(page: Pages) {
        when(page){
            Pages.EDIT_PERSONAL_DATA -> {
                Toast.makeText(context, "action open edit personal data in progress", Toast.LENGTH_SHORT).show()
            }
            Pages.EDIT_PROFESSIONAL_DATA -> {
                Toast.makeText(context, "action open edit professional data in progress", Toast.LENGTH_SHORT).show()
            }
            Pages.THEMES_CALENDAR -> {
                Toast.makeText(context, "action open themes calendar in progress", Toast.LENGTH_SHORT).show()
            }
            Pages.CREATED_THEMES, Pages.FOLLOWED_THEMES -> {
                getBaseActivity().goToDetailListActivity(page)
            }
            Pages.APPLICATION_SETTINGS -> {
                Toast.makeText(context, "action open application settings in progress", Toast.LENGTH_SHORT).show()
            }
        }
//        getBaseActivity().goToPage(PageNavigationItem(page), TransitionBundle(TransitionAnimation.ENTER_FROM_RIGHT))
    }
}
