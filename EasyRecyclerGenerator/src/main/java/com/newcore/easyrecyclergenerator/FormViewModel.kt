package com.newcore.easyrecyclergenerator

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.newcore.easy_recycler_generator.RvListFactory


class RvViewModel {

    companion object {
        private const val TAG = "RvViewModel"

        private var formViewModel2 = HashMap<Class<*>, RvViewModel>()

        fun getInstance(refactorClass: Class<*>): RvViewModel {
            Log.e(TAG, "refactorClass: $refactorClass")
            Log.e(TAG, "formViewModel2: $formViewModel2")
            Log.e(TAG, "formViewModel2: ${formViewModel2[refactorClass]}")
            return formViewModel2[refactorClass] ?: RvViewModel().also {
                formViewModel2[refactorClass] = it
                Log.e(TAG, "formViewModel2: $it")
                Log.e(TAG, "formViewModel2[refactorClass]: ${formViewModel2[refactorClass]}")

            }
        }

        fun removeInstance(refactorClass: Class<*>) {
            if (formViewModel2.containsKey(refactorClass))
                formViewModel2.remove(refactorClass)
        }
    }


    var myForm: RvListFactory? = null


}

class MLifeCycleObserver(val activity: Activity, private val mClass: Class<*>) :
    DefaultLifecycleObserver {
    private val TAG = "FormViewModel"

    override fun onDestroy(owner: LifecycleOwner) {
        if (activity.isFinishing) {
            RvViewModel.removeInstance(mClass)
            (activity as LifecycleOwner).lifecycle.removeObserver(this)
        }

        Log.e(TAG, "onDestroy: ")
    }
    //
    //    private fun checkAndSetOrientationInfo() {
    //        val currentOrientation: Int = activity.resources.configuration.orientation
    //        debugDescribeOrientations(currentOrientation);
    //        if (previousOrientation != Configuration.ORIENTATION_UNDEFINED // starts undefined
    //            && previousOrientation != currentOrientation
    //        ) rotating = true
    //        previousOrientation = currentOrientation
    //    }
    //
    //    private fun getOrientationAsString(orientation: Int): String {
    //        return if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
    //            "Landscape"
    //        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
    //            "Portrait"
    //        } else "Undefined"
    //    }
    //
    //    private fun debugDescribeOrientations(currentOrientation: Int) {
    //        Log.v("Orientation", "previousOrientation: " + getOrientationAsString(previousOrientation))
    //        Log.v("Orientation", "currentOrientation: " + getOrientationAsString(currentOrientation))
    //    }
    //
    //    private var rotating = false
    //
    //    private var previousOrientation: Int = Configuration.ORIENTATION_UNDEFINED

}

//class MLifeCycleObserverFragment(val fragment: Fragment, private val mClass: Class<*>) :
//    FragmentManager.FragmentLifecycleCallbacks() {
//    private val TAG = "FormViewModel"
//    override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
//        super.onFragmentDetached(fm, f)
//        if (fragment.isDetached) {
//            RvViewModel.removeInstance(mClass)
//            fragment.lifecycle.removeObserver(this)
//        }
//    }
//    //
//    //    override fun onDestroy(owner: LifecycleOwner) {
//    //
//    //        if (fragment.isDetached) {
//    //            RvViewModel.removeInstance(mClass)
//    //            fragment.lifecycle.removeObserver(this)
//    //        }
//    //
//    //        Log.e(TAG, "onDestroy: ")
//    //        Log.e(TAG, "fragment.isDetached: " + fragment.isDetached)
//    //        Log.e(TAG, "fragment.isDetached: " + fragment.isRemoving)
//    //        Log.e(TAG, "fragment.isDetached: " + fragment.isHidden)
//    //    }
//    //
//    //    private fun checkAndSetOrientationInfo() {
//    //        val currentOrientation: Int = activity.resources.configuration.orientation
//    //        debugDescribeOrientations(currentOrientation);
//    //        if (previousOrientation != Configuration.ORIENTATION_UNDEFINED // starts undefined
//    //            && previousOrientation != currentOrientation
//    //        ) rotating = true
//    //        previousOrientation = currentOrientation
//    //    }
//    //
//    //    private fun getOrientationAsString(orientation: Int): String {
//    //        return if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//    //            "Landscape"
//    //        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//    //            "Portrait"
//    //        } else "Undefined"
//    //    }
//    //
//    //    private fun debugDescribeOrientations(currentOrientation: Int) {
//    //        Log.v("Orientation", "previousOrientation: " + getOrientationAsString(previousOrientation))
//    //        Log.v("Orientation", "currentOrientation: " + getOrientationAsString(currentOrientation))
//    //    }
//    //
//    //    private var rotating = false
//    //
//    //    private var previousOrientation: Int = Configuration.ORIENTATION_UNDEFINED
//
//}

private const val TAG = "FormViewModel"


fun Fragment.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    val vm = RvViewModel.getInstance(this.javaClass)

    if (vm.myForm == null) {
        val rvList = RvListFactory(requireContext().applicationContext)
        rvListFactory(rvList)
        rvList.start(recyclerView, layoutManager)
        vm.myForm = rvList
        return rvList
    } else
        vm.myForm!!.start(recyclerView, layoutManager)

    return vm.myForm!!
}


fun Activity.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,
    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    val vm = RvViewModel.getInstance(this.javaClass)

    (this as LifecycleOwner).lifecycle
        .addObserver(MLifeCycleObserver(this, this::class.java))

    if (vm.myForm == null) {
        val rvList = RvListFactory(applicationContext)
        rvListFactory(rvList)
        rvList.start(recyclerView, layoutManager)
        vm.myForm = rvList
        return rvList
    } else
        vm.myForm!!.start(recyclerView, layoutManager)



    return vm.myForm!!
}


