package com.kalashnyk.denys.defaultproject.presentation.activities.auth.flow

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import com.kalashnyk.denys.defaultproject.BR
import java.util.*

class AuthChildViewModel(private var authChild: AuthChild,
                         private var listener: IAuthFlow.IAuthListener) : Observer, BaseObservable() {

    init {
        authChild.addObserver(this)
    }

    val typeChild: IAuthFlow.AuthType?
        @Bindable get() {
            return authChild.typeChild
        }

    var error: AuthFlowErrorModel? = null
        @Bindable get() {
            return authChild.error
        }

    override fun update(o: Observable?, arg: Any?) {
        if (arg is AuthFlowErrorModel) {
            error = o as AuthFlowErrorModel
            notifyPropertyChanged(BR.error)
        }
    }
//view: View,
    fun onOpenScreen(typeNavigate: IAuthFlow.NavigationType) =  listener.openScreen(typeNavigate)

//    @BindingAdapter("bind:authError")
//    fun loadImage(view: ImageView, url: String) {
//
//    }
}