package com.newcore.easyrecyclergenerator

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.newcore.easy_recycler_generator.RvListFactory

class RvViewModel {

    companion object {
        private var formViewModel2 = HashMap<Class<*>, RvViewModel>()

        fun getInstance(refactorClass: Class<*>): RvViewModel {
            return if (formViewModel2.containsKey(refactorClass))
                formViewModel2[refactorClass]!!
            else
                RvViewModel().also {
                    formViewModel2[refactorClass] = it
                }
        }

        fun removeInstance(refactorClass: Class<*>) {
            if (formViewModel2.containsKey(refactorClass))
                formViewModel2.remove(refactorClass)
        }
    }


    var myForm: RvListFactory? = null

}


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

fun Activity.destroyRvList() {
    RvViewModel.removeInstance(this.javaClass)
}

fun Activity.rvList(
    recyclerView: RecyclerView,
    layoutManager: RecyclerView.LayoutManager? = null,

    rvListFactory: RvListFactory.() -> Unit,
): RvListFactory {
    val vm = RvViewModel.getInstance(this.javaClass)

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

fun Fragment.destroyRvList() {
    RvViewModel.removeInstance(this.javaClass)
}