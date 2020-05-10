@file:JvmName("ProfileModelKt")

package com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow

import com.kalashnyk.denys.defaultproject.presentation.base.BaseChildModel

/**
 *
 */
const val isFollowField: String="isFollowField"

/**
 *
 */
const val firstNameField: String="profileFirstName"

/**
 *
 */
const val lastNameField: String="profileLastName"

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
sealed class ProfileModel : BaseChildModel() {


    /**
     * @field isFollowField
     */
    var profileIsFollow: Boolean = false
        set(value) {
            field=value
            setChangedAndNotify(isFollowField)
        }

    /**
     * @field firstNameField
     */
    var profileFirstName: String = ""
        set(value) {
            field=value
            setChangedAndNotify(firstNameField)
        }

    /**
     * @field lastNameField
     */
    var profileLastName: String = ""
        set(value) {
            field=value
            setChangedAndNotify(lastNameField)
        }

    /**
     * @field avatarPreviewField
     */
    var profileAvatarPreview: String = ""
        set(value) {
            field=value
            setChangedAndNotify(avatarPreviewField)
        }

    /**
     * @field countryField
     */
    var profileCountry: String = ""
        set(value) {
            field=value
            setChangedAndNotify(countryField)
        }

    /**
     * @field stateField
     */
    var profileState: String = ""
        set(value) {
            field=value
            setChangedAndNotify(stateField)
        }

    /**
     * @field cityField
     */
    var profileCity: String = ""
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
    var profileFavoriteCategories: List<String> = mutableListOf()
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
}