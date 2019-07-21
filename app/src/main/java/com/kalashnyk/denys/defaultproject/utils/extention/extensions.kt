package com.kalashnyk.denys.defaultproject.utils.extention

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.kalashnyk.denys.defaultproject.R

// todo check functions for exist
inline fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}

fun Activity.hideKeyboardEx() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.apply { imm.hideSoftInputFromWindow(windowToken, 0) }
}

fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    addToBackStack: Boolean,
    needAnimate: Boolean
) {
    var ft = this.beginTransaction()
    val fragmentName = fragment.javaClass.simpleName
    if (addToBackStack) ft = ft.addToBackStack(fragmentName)
    if (needAnimate) ft.setCustomAnimations(
        R.animator.slide_in_left,
        R.animator.slide_out_right,
        R.animator.pop_out_right,
        R.animator.pop_in_left
    )
    ft.replace(containerViewId, fragment, fragmentName).commit()
}

fun Activity.showToast(text: Any) = Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
fun Activity.showSnack(text: String) =
    Snackbar.make(this.findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG).show()

fun View.snack(message: String, length: Int = Snackbar.LENGTH_SHORT) = Snackbar.make(this, message, length).show()
inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_SHORT, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(color) }
}