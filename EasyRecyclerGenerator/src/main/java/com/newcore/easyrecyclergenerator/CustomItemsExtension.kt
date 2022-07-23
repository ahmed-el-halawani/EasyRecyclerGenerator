package com.newcore.easyrecyclergenerator

import android.graphics.Typeface
import com.newcore.easyrecyclergenerator.databinding.AddItemEditTextBinding
import com.newcore.easyrecyclergenerator.databinding.AddItemTextViewBinding


fun RvListFactory.addTextView(
    value: String,
    style: TextStyle? = null,
    generator: ((AddItemTextViewBinding, TextStyle) -> Unit)? = null,
): ViewGeneratorHolder<AddItemTextViewBinding, TextStyle> {

    val myGenerator: (AddItemTextViewBinding, TextStyle) -> Unit = { view, data ->
        view.textView.apply {
            typeface = data.fontFamily ?: Typeface.DEFAULT
            setTypeface(view.textView.typeface, data.fontStyle.typeFaces)
            text = value
            textSize = data.textSizeInSp
        }
        generator?.invoke(view, data)
    }

    return addItem(
        AddItemTextViewBinding::inflate,
        style ?: TextStyle.default(),
        myGenerator
    )
}

fun RvListFactory.addEditText(
    value: String? = null,
    hint: String? = null,
    style: TextStyle? = null,
    generator: ((AddItemEditTextBinding, TextStyle) -> Unit)? = null,
): ViewGeneratorHolder<AddItemEditTextBinding, TextStyle> {

    val myGenerator: (AddItemEditTextBinding, TextStyle) -> Unit = { view, data ->
        view.editText.apply {
            typeface = data.fontFamily ?: Typeface.DEFAULT
            setTypeface(view.editText.typeface, data.fontStyle.typeFaces)
            textSize = data.textSizeInSp
            setHint(hint)
            setText(value)
        }
        generator?.invoke(view, data)
    }

    return addItem(
        AddItemEditTextBinding::inflate,
        style ?: TextStyle.default(),
        myGenerator
    )
}



