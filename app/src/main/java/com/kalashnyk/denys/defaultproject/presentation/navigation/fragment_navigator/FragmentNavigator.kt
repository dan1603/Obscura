package com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator

import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kalashnyk.denys.defaultproject.R
import com.kalashnyk.denys.defaultproject.presentation.base.BaseFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.recover_account.RecoverAccountFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.sign_in.SignInFragment
import com.kalashnyk.denys.defaultproject.presentation.fragments.sign_up.SignUpFragment
import com.kalashnyk.denys.defaultproject.presentation.navigation.fragment_navigator.model.*

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
class FragmentNavigatorImpl(private val fm: FragmentManager) :
    FragmentNavigator {
    private var pagesStack=ArrayList<Pages>()

    companion object {
        private const val CONTENT_ID=R.id.content_view_group
        private const val FRAGMENT_TAG="fragment_tag"
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
        val currentFragment=fm.findFragmentByTag(FRAGMENT_TAG)
        goToPage(page, transitionBundle, currentFragment as ResultListener)
    }

    /**
     *
     */
    override fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle) {
        goToPage(page, transitionBundle, null)
    }

    @Suppress("ComplexMethod")
    fun goToPage(page: PageNavigationItem, transitionBundle: TransitionBundle, resultListener: ResultListener?) {
        when (page.destination) {
            Pages.SIGN_IN -> handleRoutFragment(SignInFragment.newInstance(), page, transitionBundle, resultListener)
            Pages.SIGN_UP -> handleRoutFragment(SignUpFragment.newInstance(), page, transitionBundle, resultListener)
            Pages.RECOVER_ACCOUNT -> handleRoutFragment(RecoverAccountFragment.newInstance(), page, transitionBundle, resultListener)
            else -> back()
        }
    }

    override fun back(): Boolean {
        return if (pagesStack.size > 1) {
            fm.popBackStack()
            pagesStack.remove(pagesStack.last())
            true
        } else {
            false
        }
    }

    override fun reset() {
        while (pagesStack.size > 1) {
            fm.popBackStackImmediate()
        }
        (fm.fragments.last() as? BaseFragment<*>)?.reset()
    }

    private fun show(dialog: DialogFragment) {
        dialog.show(fm, "modal")
    }

    private fun handleRoutFragment(
        fragment: Fragment,
        page: PageNavigationItem,
        transitionBundle: TransitionBundle,
        resultListener: ResultListener?
    ) {
        if (checkPagesByPrevies(page)) {
            back()
        } else {
            pagesStack.add(page.destination)
            addOrReplaceFragment(fragment, transitionBundle)
        }
    }

    @Suppress("ComplexMethod")
    private fun addOrReplaceFragment(fragment: Fragment, transitionBundle: TransitionBundle) {
        val existingFragment=fm.findFragmentByTag(FRAGMENT_TAG)
        val transaction=fm.beginTransaction()

        if(pagesStack.size > 1) {
            addCustomAnimations(transaction, transitionBundle)
        }

        transitionBundle.views.forEach {
            val transitionName=ViewCompat.getTransitionName(it)
            transaction.addSharedElement(it, transitionName!!)
        }

        if (existingFragment == null) {
            transaction.add(
                CONTENT_ID, fragment,
                FRAGMENT_TAG
            )
        } else {
            transaction.replace(
                CONTENT_ID, fragment,
                FRAGMENT_TAG
            )
            transaction.addToBackStack(null)
        }
        transaction.commitAllowingStateLoss()
    }

    private fun addCustomAnimations(transaction: FragmentTransaction, transitionBundle : TransitionBundle){
        when (transitionBundle.animation) {
            TransitionAnimation.SLIDE_IN_FROM_RIGHT -> transaction.setCustomAnimations(
                R.animator.slide_in_left,
                R.animator.slide_out_right,
                R.animator.pop_out_right,
                R.animator.pop_in_left
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
                R.animator.pop_out_right,
                R.animator.pop_in_left,
                R.animator.slide_in_left,
                R.animator.slide_out_right
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
    }

    private fun checkPagesByPrevies(page : PageNavigationItem) : Boolean{
        return pagesStack.size >= 2 &&
                pagesStack.lastIndexOf(page.destination).inc() ==
                pagesStack.lastIndexOf(pagesStack.last())
    }
}