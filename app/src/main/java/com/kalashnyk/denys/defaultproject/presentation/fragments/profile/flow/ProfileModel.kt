@file:JvmName("ProfileModelKt")

package com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow

import com.kalashnyk.denys.defaultproject.presentation.base.BaseChildModel
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.CategoryEntity
import com.kalashnyk.denys.defaultproject.usecases.repository.data_source.database.entity.UserEntity

/**
 *
 */
const val isFollowField: String="isFollowField"

/**
 *
 */
const val firstNameField: String="firstNameField"

/**
 *
 */
const val lastNameField: String="lastNameField"

/**
 *
 */
const val avatarPreviewField: String="avatarPreviewField"

/**
 *
 */
const val countryField: String="countryField"

/**
 *
 */
const val stateField: String="stateField"

/**
 *
 */
const val cityField: String="cityField"

/**
 *
 */
const val occupationField: String="occupationField"

/**
 *
 */
const val professionalCertificatesField: String="professionalCertificatesField"

/**
 *
 */
const val favoriteCategoryField: String="favoriteCategoryField"

/**
 *
 */
const val createdThemesField: String="createdThemesField"

/**
 *
 */
const val followedThemesField: String="followedThemesField"

/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 */
sealed class ProfileModel(private var user : UserEntity) : BaseChildModel() {


    /**
     * @field isFollowField
     */
    var profileIsFollow: Boolean? = user.isFollow
        set(value) {
            field=value
            setChangedAndNotify(isFollowField)
        }

    /**
     * @fields firstNameField, lastNameField
     */
    var profileFullName: String = fullNameToString()

    /**
     * firstNameField
     */
    var profileFirstName: String? = user.name
        set(value) {
            field=value
            setChangedAndNotify(firstNameField)
        }

    /**
     * lastNameField
     */
    var profileLastName: String? = user.surname
        set(value) {
            field=value
            setChangedAndNotify(firstNameField)
        }

    /**
     * @field avatarPreviewField
     */
    var profileAvatarPreview: String = user.avatarPreview
        set(value) {
            field=value
            setChangedAndNotify(avatarPreviewField)
        }

    /**
     * @fields countryField, stateField, cityField
     */
    var profileLocation: String = locationToString()

    /**
     * @field countryField
     * need for changing from edit personal data screen
     */
    var profileCountry: String? = user.location?.country
        set(value) {
            field=value
            setChangedAndNotify(countryField)
        }

    /**
     * @field stateField
     * need for changing from edit personal data screen
     */
    var profileState: String? = user.location?.state
        set(value) {
            field=value
            setChangedAndNotify(stateField)
        }

    /**
     * @field cityField
     * need for changing from edit personal data screen
     */
    var profileCity: String? = user.location?.city
        set(value) {
            field=value
            setChangedAndNotify(cityField)
        }

    /**
     * @field occupationField
     */
    var profileOccupation: String = ""
        set(value) {
            field=value
            setChangedAndNotify(occupationField)
        }

    /**
     * @field professionalCertificatesField
     */
    var profileProfessionalCertificates: Int = 0
        set(value) {
            field=value
            setChangedAndNotify(professionalCertificatesField)
        }

    //todo refactor when will doing feature Filter Main Screen and than need use object or type category for filter
    /**
     * @field favoriteCategoryField
     */
    var profileFavoriteCategories: List<CategoryEntity> = mutableListOf()
        set(value) {
            field=value
            setChangedAndNotify(favoriteCategoryField)
        }

    /**
     * @field createdThemesField
     */
    var profileCreatedThemes: Int = 0
        set(value) {
            field=value
            setChangedAndNotify(createdThemesField)
        }

    /**
     * @field followedThemesField
     */
    var profileFollowedThemes: Int = 0
        set(value) {
            field=value
            setChangedAndNotify(followedThemesField)
        }

    private fun fullNameToString(): String {
        val textBody=StringBuilder()
        profileFirstName?.takeIf { !it.isNullOrEmpty() }?.let { textBody.append("$it, ") }
        profileLastName.takeIf { !it.isNullOrEmpty() }?.let { textBody.append(it) }
        return textBody.toString()
    }

    private fun locationToString(): String {
        val textBody=StringBuilder()
        profileCountry?.takeIf { !it.isNullOrEmpty() }?.let { textBody.append("$it, ") }
        profileState?.takeIf { !it.isNullOrEmpty() }?.let { textBody.append("$it, ") }
        profileCity?.takeIf { !it.isNullOrEmpty() }?.let { textBody.append(it) }
        return textBody.toString()
    }
}