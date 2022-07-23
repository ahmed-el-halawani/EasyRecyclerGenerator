package com.newcore.easyrecyclergenerator

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.newcore.core.ViewContainer
import com.newcore.easyrecyclergenerator.databinding.ActivityFormWithValidatorsBinding
import com.newcore.myformvalidation.validators.emailValidator
import com.newcore.myformvalidation.validators.emptyValidator
import com.newcore.myformvalidation.vmForm

class FormWithValidatorsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()
        setContentView(binding.root)
        onViewCreated()
    }

    private fun onViewCreated() {


        rvList(binding.rvList, isLazyLoading = false) {


            val container = object : ViewContainer {
                override fun <T : View> findViewById(id: Int): T {
                    return this@rvList.findViewById(id) as T
                }
            }

            vmForm {

                addTextView("ahmed")
                addEditText("ahmed", "hint") { v, d ->
                    inputField(
                        v.editText,
                        layoutView = rvListLayoutView(v.editText)
                    ) {
                        emptyValidator()
                    }
                }


                addEditText("ahmed", "email") { v, i ->
                    inputField(
                        v.editText,
                        layoutView = rvListLayoutView(v.editText),
                        isOptional = true
                    ) {
                        emptyValidator()
                        emailValidator()
                    }
                }



                submitButton(binding.btnSub)

                onValidForm {
                    Toast.makeText(this@FormWithValidatorsActivity,
                        "valid form",
                        Toast.LENGTH_SHORT).show()
                }

                onInvalidForm {
                    Toast.makeText(this@FormWithValidatorsActivity,
                        "in valid form",
                        Toast.LENGTH_SHORT).show()
                }

                //
                //                val z = addTextView("ahmed")
                //                addTextView("ahmed")
                //                addEditText("ahmed", "hint")
                //                addEditText("ahmed", "hint")
                //                addEditText("ahmed", "hint")

                //                try {
                //                    Log.e(TAG, "onViewCreated: " + z.view?.id)
                //                    val y = findViewById<View>(z.view!!.id)
                //                    Log.e(TAG, "onViewCreated: " + y.id)
                //
                //
                //                } catch (e: Exception) {
                //                    Log.e(TAG, "onViewCreated: " + e.localizedMessage)
                //                }
            }
            //            addItem(
            //                AddItemTextViewBinding::inflate,
            //                "ahmed"
            //            ) { v, data ->
            //                v.textView.text = data
            //            }


        }

    }

    private val TAG = "FormWithValidatorsActiv"

    private fun initDependencies() {
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityFormWithValidatorsBinding.inflate(layoutInflater)
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityFormWithValidatorsBinding
}