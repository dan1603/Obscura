package com.kalashnyk.denys.defaultproject.presentation.fragments.profile.flow

import android.content.Context
import android.graphics.Typeface
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.utils.binding.TextSpanModel
import java.util.*

/**
 *
 */
class ProfileModelBinding(
    private var profileModel: ProfileModel,
    private val listener : ProfileFlow.ProfileListener
    ) : Observer, BaseObservable() {

    private var context: Context

    init {
        profileModel.addObserver(this)
        context=listener as Context
    }

    /**
     * @field isFollow
     */
    var isFollow: Boolean? = false
        set(value) {
            field= value
        }
        @Bindable get() {
            return field
        }

    /**
     * @field profileFullName
     */
    var profileFullName: String? = ""
        set(value) {
            field= value
        }
        @Bindable get() {
            return field
        }

    /**
     * @field signInTextSpanModel
     */
    val signInTextSpanModel: TextSpanModel
        @Bindable get() {
            return TextSpanModel(
                context.resources.getString(R.string.sign_in),
                context.resources.getString(R.string.text_have_an_account),
                TextSpanModel.SpanTextPosition.LAST,
                R.dimen.txt_size_16,
                R.dimen.txt_size_14,
                R.color.colorWhite,
                R.color.dark_blue,
                Typeface.NORMAL
            )
        }

    override fun update(o: Observable?, arg: Any?) {
        if (o is ProfileModel?) {
            if (arg == isFollow) isFollow=profileModel.profileIsFollow
        }
    }
}