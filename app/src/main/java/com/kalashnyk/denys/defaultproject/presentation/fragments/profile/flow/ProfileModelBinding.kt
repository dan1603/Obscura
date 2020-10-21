package com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow

import android.content.Context
import android.graphics.Typeface
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.utils.binding.TextBindingModel
import java.util.*



/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */

class ProfileModelBinding(
    private var profileModel: ProfileModel,
    private val context: Context
) : Observer, BaseObservable() {

    init {
        profileModel.addObserver(this)
    }

    /**
     * @field profileIsFollowBinding
     */
    var profileIsFollowBinding: Boolean?=profileModel.profileIsFollow
        @Bindable get

    /**
     * @field profileFullNameBinding
     */
    var profileFullNameBinding: TextBindingModel?=prepareContentForFullName()
        @Bindable get

    /**
     * TODO: refactor to more suitable solution
     * @field profileAvatarBinding
     */
    var profileAvatarBinding: String?= profileModel.profileAvatarPreview
        @Bindable get

    /**
     * @field profileLocationBinding
     */
    var profileLocationBinding: TextBindingModel?=prepareContentForLocation()
        @Bindable get

    /**
     * @field profileOccupationBinding
     */
    var profileOccupationBinding: TextBindingModel?=prepareContentForOccupation()
        @Bindable get

    /**
     * @field profileProfessionalCertificatesBinding
     */
    var profileProfessionalCertificatesBinding: TextBindingModel?=
        prepareContentForProfessionalCertificates()
        @Bindable get

    /**
     * @field profileFavoriteCategoriesBinding
     */
    var profileFavoriteCategoriesBinding: TextBindingModel?=prepareContentFavoriteCategory()
        @Bindable get

    /**
     * @field profileCreatedThemesBinding
     */
    var profileCreatedThemesBinding: TextBindingModel?=prepareContentForCreatedThemes()
        @Bindable get

    /**
     * @field profileFollowedThemesBinding
     */
    var profileFollowedThemesBinding: TextBindingModel?=prepareContentForFollowedThemes()
        @Bindable get

    override fun update(model: Observable?, fieldName: Any?) {
        if (model is ProfileModel && fieldName is String) {
            prepareContentForField(fieldName)
        }
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

    private fun prepareContentForFullName(): TextBindingModel=
        if (profileModel.profileFullName.isNullOrEmpty())
            TextBindingModel.Default(
                context.resources.getString(R.string.profile_default_not_specified),
                R.dimen.txt_size_16,
                R.color.grey_brown
            ) else
            TextBindingModel.Default(
                profileModel.profileFullName,
                R.dimen.txt_size_16,
                R.color.grey_brown
            )

    private fun prepareContentForLocation(): TextBindingModel=
        if (profileModel.profileLocation.isNullOrEmpty())
            TextBindingModel.Default(
                context.resources.getString(R.string.profile_default_not_specified),
                R.dimen.txt_size_14,
                R.color.grey_brown
            ) else
            TextBindingModel.Default(
                profileModel.profileLocation,
                R.dimen.txt_size_14,
                R.color.soft_blue
            )

    private fun prepareContentForOccupation(): TextBindingModel=
        if (profileModel.profileOccupation.isNullOrEmpty())
            TextBindingModel.Default(
                context.resources.getString(R.string.profile_occupation_not_specified),
                R.dimen.txt_size_14,
                R.color.grey_brown
            ) else
            TextBindingModel.Default(
                profileModel.profileOccupation,
                R.dimen.txt_size_14,
                R.color.grey_brown
            )

    private fun prepareContentForProfessionalCertificates(): TextBindingModel {
        return TextBindingModel.CountFormat(
            profileModel.profileProfessionalCertificates,
            R.string.profile_certificates_format,
            R.string.profile_certificates_empty,
            R.color.soft_blue,
            R.color.grey_brown
        )
    }

    private fun prepareContentFavoriteCategory(): TextBindingModel {
        return if (profileModel.profileFavoriteCategories.isNotEmpty())
            TextBindingModel.Span(
                profileModel.profileFavoriteCategories.joinToString(separator=", ") { categoryEntity -> categoryEntity.name },
                context.resources.getString(R.string.profile_favorite_categories_format),
                TextBindingModel.Span.SpanTextPosition.LAST,
                R.dimen.txt_size_16,
                R.dimen.txt_size_14,
                R.color.soft_blue,
                R.color.grey_brown,
                Typeface.NORMAL
            )
        else TextBindingModel.Default(
            context.resources.getString(R.string.profile_default_not_specified),
            R.dimen.txt_size_14,
            R.color.grey_brown
        )
    }

    private fun prepareContentForCreatedThemes(): TextBindingModel {
        return TextBindingModel.CountFormat(
            profileModel.profileCreatedThemes,
            R.string.profile_created_themes_format,
            R.string.profile_created_themes_empty,
            R.color.soft_blue,
            R.color.soft_blue
        )
    }

    private fun prepareContentForFollowedThemes(): TextBindingModel {
        return TextBindingModel.CountFormat(
            profileModel.profileFollowedThemes,
            R.string.profile_followed_themes_format,
            R.string.profile_followed_themes_empty,
            R.color.soft_blue,
            R.color.grey_brown
        )
    }


}