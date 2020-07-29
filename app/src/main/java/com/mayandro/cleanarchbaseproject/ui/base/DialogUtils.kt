package com.mayandro.cleanarchbaseproject.ui.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mayandro.cleanarchbaseproject.R

class DialogUtils {
    var bottomSheetDialog: BottomSheetDialog? = null

    fun showAlertMessage(
        context: Context,
        title: String,
        message: String,
        positiveButton: String,
        negativeButton: String = "",
        positiveButtonClickListener: ((View) -> Unit)? = null,
        negativeButtonClickListener: ((View) -> Unit)? = null,
        isCancellable: Boolean = true
    ) {

        dismissDialog()

        val view =
            LayoutInflater.from(context).inflate(R.layout.bottom_sheet_general_notification, null)

        view.findViewById<TextView>(R.id.textViewDialogTitle).text = title
        if (message.isNullOrBlank()) {
            view.findViewById<TextView>(R.id.textViewDialogMessage).visibility = View.GONE
        }
        view.findViewById<TextView>(R.id.textViewDialogMessage).text = message

        view.findViewById<TextView>(R.id.buttoPositive).apply {
            text = positiveButton
            if (positiveButton.isNotEmpty()) this.visibility = View.VISIBLE
            setOnClickListener {
                dismissDialog()
                positiveButtonClickListener?.invoke(it)
            }
        }

        view.findViewById<TextView>(R.id.buttonNegative).apply {
            text = negativeButton
            if (negativeButton.isNotEmpty()) this.visibility = View.VISIBLE
            setOnClickListener {
                dismissDialog()
                negativeButtonClickListener?.invoke(it)
            }
        }

        bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog?.setContentView(view)
        bottomSheetDialog?.setCanceledOnTouchOutside(false)
        bottomSheetDialog?.setCancelable(isCancellable)
        bottomSheetDialog?.show()
    }


    fun dismissDialog(): Boolean {
        bottomSheetDialog?.let {
            if (it.isShowing) {
                it.dismiss()
                return true
            }
        }
        return false
    }

}