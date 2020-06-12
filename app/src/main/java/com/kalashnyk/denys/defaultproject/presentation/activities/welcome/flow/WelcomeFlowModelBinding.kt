package com.kalashnyk.denys.defaultproject.presentation.activities.welcome.flow

import android.content.Context
import android.graphics.Typeface
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.utils.binding.TextBindingModel
import java.util.*

/**
 *
 */
class WelcomeFlowModelBinding(private var context: Context)
    : Observer, BaseObservable(){

    /**
     * @field signUpTextSpanModel
     */
    val signUpTextSpanModel: TextBindingModel.Span
        @Bindable get() {
            return TextBindingModel.Span(
                context.resources.getString(R.string.sign_up),
                context.resources.getString(R.string.welcome_text_not_with_us_yet),
                TextBindingModel.Span.SpanTextPosition.LAST,
                R.dimen.txt_size_18,
                R.dimen.txt_size_16,
                R.color.colorWhite,
                R.color.light_grey,
                Typeface.NORMAL
            )
        }

    /**
     * @field signInTextSpanModel
     */
    val signInTextSpanModel: TextBindingModel.Span
        @Bindable get() {
            return TextBindingModel.Span(
                context.resources.getString(R.string.sign_in),
                context.resources.getString(R.string.welcome_text_already_have_an_account),
                TextBindingModel.Span.SpanTextPosition.LAST,
                R.dimen.txt_size_18,
                R.dimen.txt_size_16,
                R.color.colorWhite,
                R.color.light_grey,
                Typeface.NORMAL
            )
        }

    /**
     *
     */
    override fun update(o: Observable?, arg: Any?) {

    }
}