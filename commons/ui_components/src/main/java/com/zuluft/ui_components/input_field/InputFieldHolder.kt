package com.zuluft.ui_components.input_field

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout

class InputFieldHolder : TextInputLayout {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, -1)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        isTransitionGroup = true
    }
}