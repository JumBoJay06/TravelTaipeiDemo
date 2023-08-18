package com.jumbojay.traveltaipeidemo.util

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.jumbojay.traveltaipeidemo.R

class CustomDialogFragment(
    private val title: String? = null,
    private val message: String? = null,
    private val positiveText: String? = null,
    private val positiveListener: DialogInterface.OnClickListener? = null,
    private val negativeText: String? = null,
    private val negativeListener: DialogInterface.OnClickListener? = null,
    private val neutralText: String? = null,
    private val neutralListener: DialogInterface.OnClickListener? = null,
    private val cancelable: Boolean = false
) : DialogFragment() {
    private var customView: View? = null

    constructor(
        customView: View? = null,
        title: String? = null,
        message: String? = null,
        positiveText: String? = null,
        positiveListener: DialogInterface.OnClickListener? = null,
        negativeText: String? = null,
        negativeListener: DialogInterface.OnClickListener? = null,
        neutralText: String? = null,
        neutralListener: DialogInterface.OnClickListener? = null,
        cancelable: Boolean = false
    ) : this(
        title,
        message,
        positiveText,
        positiveListener,
        negativeText,
        negativeListener,
        neutralText,
        neutralListener,
        cancelable
    ) {
        this.customView = customView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(
            requireContext(),
            R.style.MyDialog
        ).apply {
            if (!TextUtils.isEmpty(title)) {
                setTitle(title)
            }
            if (!TextUtils.isEmpty(positiveText)) {
                setPositiveButton(positiveText, positiveListener)
            }
            if (!TextUtils.isEmpty(negativeText)) {
                setNegativeButton(negativeText, negativeListener)
            }
            if (!TextUtils.isEmpty(neutralText)) {
                setNeutralButton(neutralText, neutralListener)
            }
            setOnKeyListener { _, keyCode, event ->
                return@setOnKeyListener if (keyCode == KeyEvent.KEYCODE_BACK) {
                    !cancelable
                } else {
                    true
                }
            }
        }

        customView?.let {
            builder.setView(it)
        } ?: run {
            if (!TextUtils.isEmpty(message)) {
                builder.setMessage(message)
            }
        }

        return builder.create().apply {
            setCanceledOnTouchOutside(cancelable)
            setCancelable(cancelable)
            setOnShowListener {
                getButton(DialogInterface.BUTTON_POSITIVE).isAllCaps = false
                getButton(DialogInterface.BUTTON_NEGATIVE).isAllCaps = false
                getButton(DialogInterface.BUTTON_NEUTRAL).isAllCaps = false
            }
        }
    }
}