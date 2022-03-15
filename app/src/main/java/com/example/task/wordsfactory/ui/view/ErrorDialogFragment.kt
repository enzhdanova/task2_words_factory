package com.example.task.wordsfactory.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class ErrorDialogFragment : DialogFragment() {

    companion object {
        private const val ARG_OBJECT_ERROR_MESSAGE = "error_message"
        const val ERROR_TAG = "error_tag"
        const val TEXT_POSITIVE_BUTTON = "ок"

        fun getErrorDialog(errorMessage: String): ErrorDialogFragment {
            val errorDialogFragment = ErrorDialogFragment()
            errorDialogFragment.arguments = bundleOf(
                ARG_OBJECT_ERROR_MESSAGE to errorMessage
            )
            return errorDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val mssg = arguments?.getString(ARG_OBJECT_ERROR_MESSAGE) ?: ""
            val builder = AlertDialog.Builder(it)
            builder.setTitle(mssg)
                .setPositiveButton(TEXT_POSITIVE_BUTTON) { dialog, _ ->
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}