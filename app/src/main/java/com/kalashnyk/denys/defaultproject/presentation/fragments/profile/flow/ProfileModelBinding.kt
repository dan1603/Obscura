package com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow

import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.Pages
import com.kalashnyk.denys.defaultproject.utils.binding.BaseBindingModel
import com.kalashnyk.denys.defaultproject.utils.binding.SimpleTextModel
import com.kalashnyk.denys.defaultproject.utils.binding.TextCountModel
import com.kalashnyk.denys.defaultproject.utils.binding.TextSpanModel
import java.util.*



/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */

class ProfileModelBinding(
    private var profileModel: ProfileModel,
    private val listener: ProfileFlow.ProfileListener
) : Observer, BaseObservable() {

    private var context: Context

    init {
        profileModel.addObserver(this)
        context=listener as Context
    }

    /**
     * @field profileIsFollowBinding
     */
    var profileIsFollowBinding: Boolean?=profileModel.profileIsFollow
        @Bindable get

    /**
     * @field profileFullNameBinding
     */
    var profileFullNameBinding: BaseBindingModel?=prepareContentForFullName()
        @Bindable get

    /**
     * @field profileLocationBinding
     */
    var profileLocationBinding: BaseBindingModel?=prepareContentForLocation()
        @Bindable get

    /**
     * @field profileOccupationBinding
     */
    var profileOccupationBinding: BaseBindingModel?=prepareContentForOccupation()
        @Bindable get

    /**
     * @field profileProfessionalCertificatesBinding
     */
    var profileProfessionalCertificatesBinding: BaseBindingModel?=
        prepareContentForProfessionalCertificates()
        @Bindable get

    /**
     * @field profileFavoriteCategoriesBinding
     */
    var profileFavoriteCategoriesBinding: BaseBindingModel?=prepareContentFavoriteCategory()
        @Bindable get

    /**
     * @field profileCreatedThemesBinding
     */
    var profileCreatedThemesBinding: BaseBindingModel?=prepareContentForCreatedThemes()
        @Bindable get

    /**
     * @field profileFollowedThemesBinding
     */
    var profileFollowedThemesBinding: BaseBindingModel?=prepareContentForFollowedThemes()
        @Bindable get

    override fun update(model: Observable?, fieldName: Any?) {
        if (model is ProfileModel && fieldName is String) {
            prepareContentForField(fieldName)
        }
    }

    public fun onEditPersonalData(){
        listener.openScreen(Pages.EDIT_PERSONAL_DATA)
    }

    public fun onEditProfessionalData(){
        listener.openScreen(Pages.EDIT_PROFESSIONAL_DATA)
    }

    public fun onOpenThemesCalendar(){
        listener.openScreen(Pages.EDIT_PROFESSIONAL_DATA)
    }

    public fun onOpenCreatedThemes(){

    }

    public fun onOpenFollowedThemes(){

    }

    public fun onOpenApplicationsSettings(){

    }

    public fun onLogout(){

    }

    private fun prepareContentForField(fieldName: String) {
        when (fieldName) {
            isFollowField -> profileIsFollowBinding=profileModel.profileIsFollow
            firstNameField, lastNameField -> profileFullNameBinding=prepareContentForFullName()
            countryField, stateField, cityField -> profileLocationBinding=prepareContentForLocation()
            occupationField -> profileOccupationBinding=prepareContentForOccupation()
            professionalCertificatesField -> profileProfessionalCertificatesBinding = prepareContentForProfessionalCertificates()
            favoriteCategoryField -> profileFavoriteCategoriesBinding=prepareContentFavoriteCategory()
            createdThemesField -> profileCreatedThemesBinding = prepareContentForCreatedThemes()
            followedThemesField -> profileFollowedThemesBinding = prepareContentForFollowedThemes()
        }
    }

    private fun prepareContentForFullName(): BaseBindingModel=
        if (profileModel.profileFullName.isNullOrEmpty())
            SimpleTextModel(
                context.resources.getString(R.string.profile_default_not_specified),
                R.dimen.txt_size_16,
                R.color.grey_brown
            ) else
            SimpleTextModel(
                profileModel.profileFullName,
                R.dimen.txt_size_16,
                R.color.grey_brown
            )

    private fun prepareContentForLocation(): BaseBindingModel=
        if (profileModel.profileLocation.isNullOrEmpty())
            SimpleTextModel(
                context.resources.getString(R.string.profile_default_not_specified),
                R.dimen.txt_size_14,
                R.color.grey_brown
            ) else
            SimpleTextModel(
                profileModel.profileLocation,
                R.dimen.txt_size_14,
                R.color.soft_blue
            )

    private fun prepareContentForOccupation(): BaseBindingModel=
        if (profileModel.profileOccupation.isNullOrEmpty())
            SimpleTextModel(
                context.resources.getString(R.string.profile_occupation_not_specified),
                R.dimen.txt_size_14,
                R.color.grey_brown
            ) else
            SimpleTextModel(
                profileModel.profileOccupation,
                R.dimen.txt_size_14,
                R.color.grey_brown
            )

    private fun prepareContentForProfessionalCertificates(): BaseBindingModel {
        return TextCountModel(
            profileModel.profileProfessionalCertificates,
            R.string.profile_certificates_format,
            R.string.profile_certificates_empty,
            R.color.soft_blue,
            R.color.grey_brown
        )
    }

    private fun prepareContentFavoriteCategory(): BaseBindingModel {
        return if (profileModel.profileFavoriteCategories.isNotEmpty())
            TextSpanModel(
                profileModel.profileFavoriteCategories.joinToString(separator=", ") { categoryEntity -> categoryEntity.name },
                context.resources.getString(R.string.profile_favorite_categories_format),
                TextSpanModel.SpanTextPosition.LAST,
                R.dimen.txt_size_16,
                R.dimen.txt_size_14,
                R.color.soft_blue,
                R.color.grey_brown,
                Typeface.NORMAL
            )
        else SimpleTextModel(
            context.resources.getString(R.string.profile_default_not_specified),
            R.dimen.txt_size_14,
            R.color.grey_brown
        )
    }

    private fun prepareContentForCreatedThemes(): BaseBindingModel {
        return TextCountModel(
            profileModel.profileCreatedThemes,
            R.string.profile_created_themes_format,
            R.string.profile_created_themes_empty,
            R.color.soft_blue,
            R.color.soft_blue
        )
    }

    private fun prepareContentForFollowedThemes(): BaseBindingModel {
        return TextCountModel(
            profileModel.profileFollowedThemes,
            R.string.profile_followed_themes_format,
            R.string.profile_followed_themes_empty,
            R.color.soft_blue,
            R.color.grey_brown
        )
    }
}