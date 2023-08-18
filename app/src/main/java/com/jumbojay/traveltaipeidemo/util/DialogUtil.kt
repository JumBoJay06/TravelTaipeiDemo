package com.jumbojay.traveltaipeidemo.util

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.widget.NumberPicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.jumbojay.traveltaipeidemo.R
import com.jumbojay.traveltaipeidemo.databinding.PickerViewBinding

object DialogUtil {

    fun <T> createPickDialog(
        context: Context,
        title: String?,
        selectionList: Array<T>,
        defaultIndex: Int,
        positiveText: String? = null,
        negativeText: String? = null,
        itemFormat: ((T) -> String),
        picked: ((T, Int) -> Unit),
        cancelable: Boolean = false
    ): DialogFragment {
        val pickerView = DataBindingUtil.inflate<PickerViewBinding>(
            LayoutInflater.from(context),
            R.layout.picker_view,
            null,
            false
        )

        pickerView.picker.apply {
            wrapSelectorWheel = true
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            displayedValues = selectionList.map {
                itemFormat.invoke(it)
            }.toTypedArray()
            minValue = 0
            maxValue = selectionList.size - 1
            value = defaultIndex
        }

        val positiveListener = DialogInterface.OnClickListener { _, _ ->
            val value = selectionList[pickerView.picker.value]
            picked.invoke(value, pickerView.picker.value)
        }

        return CustomDialogFragment(
            customView = pickerView.root,
            title = title,
            positiveText = positiveText,
            positiveListener = positiveListener,
            negativeText = negativeText,
            negativeListener = null,
            cancelable = cancelable
        )
    }
}