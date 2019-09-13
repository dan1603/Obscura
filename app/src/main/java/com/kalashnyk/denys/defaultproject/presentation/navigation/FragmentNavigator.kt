package com.kalashnyk.denys.defaultproject.presentation.navigation

import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.sign_in.SignInFragment
import com.kalashnyk.denys.defaultproject.presentation.navigation.model.PageNavigationItem
import com.kalashnyk.denys.defaultproject.presentation.navigation.model.Pages
import com.kalashnyk.denys.defaultproject.presentation.navigation.model.TransitionAnimation
import com.kalashnyk.denys.defaultproject.presentation.navigation.model.TransitionBundle

import com.wego.core.base.components.navigation.model.*


/**
 * @author Kalashnyk Denys e-mail: kalashnyk.denys@gmail.com
 * navigation source for opening fragments screens
 */
interface FragmentNavigator {

    /**
     * @param page
     */
    fun goToPage(page: PageNavigationItem)

    /**
     * @param page
     * @param transitionBundle
     */
    fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle)

    /**
     * @param page
     * @param transitionBundle
     */
    fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle)

    /**
     * @return
     */
    fun back(): Boolean

    /**
     *
     */
    fun reset()

}
/**
 *
 */
class FragmentNavigatorImpl(private val fm: FragmentManager) : FragmentNavigator {

    companion object {
        private val CONTENT_ID = R.id.content_view_group
        private const val FRAGMENT_TAG = "fragment_tag"
    }

    /**
     *
     */
    override fun goToPage(page: PageNavigationItem) {
        this.goToPage(page, TransitionBundle(), null)
    }

    /**
     *
     */
    override fun goToPageForResult(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        val currentFragment = fm.findFragmentByTag(FRAGMENT_TAG)
        goToPage(page, transitionBundle, currentFragment as ResultListener)
    }

    /**
     *
     */
    override fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        goToPage(page, transitionBundle, null)
    }
// activity
//        val payload = Payload("123")
//        goToPage(PageNavigationItem(Pages.VOYAGE, payload))
//    }
//}
//fragment
// getNavigatorIfAvailable().goToPage(PageNavigationItem(Pages.ENTERTAINMENT,
//                            PayloadSearch(placeId!!, this.placeName, this.lang, this.currency)))
    @Suppress("ComplexMethod")
    fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle, resultListener: ResultListener?) {

        when (page.destination) {
            Pages.SIGN_IN -> addOrReplaceFragment(SignInFragment.newInstance(), transitionBundle)
            Pages.SIGN_UP -> addOrReplaceFragment(SignInFragment.newInstance(), transitionBundle)
            Pages.RECOVER_ACCOUNT -> addOrReplaceFragment(SignInFragment.newInstance(), transitionBundle)
        }
    }

    override fun back(): Boolean {
        return if (fm.backStackEntryCount >= 1) {
            fm.popBackStack()
            true
        } else {
            false
        }
    }

    override fun reset() {
        while (fm.backStackEntryCount > 0) {
            fm.popBackStackImmediate()
        }
        (fm.fragments.last() as? BaseFragment<*>)?.reset()
    }

    private fun show(dialog: DialogFragment) {
        dialog.show(fm, "modal")
    }

    @Suppress("ComplexMethod")
    private fun addOrReplaceFragment(fragment: Fragment, transitionBundle: TransitionBundle) {
        val existingFragment = fm.findFragmentByTag(FRAGMENT_TAG)
        val transaction = fm.beginTransaction()

        when (transitionBundle.animation) {
            TransitionAnimation.SLIDE_IN_FROM_RIGHT -> transaction.setCustomAnimations(
                R.anim.slide_in_right_chrome,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out_right_chrome
            )
            TransitionAnimation.SLIDE_UP_FROM_BOTTOM -> transaction.setCustomAnimations(
                R.anim.slide_up_bottom,
                R.anim.zoom_out,
                R.anim.fade_in,
                R.anim.slide_out_bottom
            )
            TransitionAnimation.ENTER_FROM_RIGHT -> transaction.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
            )
            TransitionAnimation.ENTER_FROM_LEFT -> transaction.setCustomAnimations(
                R.anim.enter_from_left,
                R.anim.exit_to_left
            )
            TransitionAnimation.FADE_IN -> transaction.setCustomAnimations(
                R.anim.fade_in,
                R.anim.fade_out
            )
            TransitionAnimation.NONE -> {
            }
            TransitionAnimation.SCALE_UP_FROM_VIEW -> {
            }
        }

        transitionBundle.views.forEach {
            val transitionName = ViewCompat.getTransitionName(it)
            transaction.addSharedElement(it, transitionName!!)
        }
        if (existingFragment == null) {
            transaction.add(CONTENT_ID, fragment, FRAGMENT_TAG)
        } else {
            transaction.replace(CONTENT_ID, fragment, FRAGMENT_TAG)
            transaction.addToBackStack(null)
        }

        transaction.commitAllowingStateLoss()
    }

}