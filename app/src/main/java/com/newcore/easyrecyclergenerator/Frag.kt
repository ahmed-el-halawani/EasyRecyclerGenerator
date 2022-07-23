package com.newcore.easyrecyclergenerator

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.newcore.myformvalidation.vmForm

class Frag:Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmForm {

        }
    }
}