package com.assessment.nycschools.utils

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.assessment.nycschools.R
import com.google.android.material.snackbar.Snackbar

fun Fragment.showDialog() {
    AlertDialog.Builder(this.requireContext()).setMessage(getString(R.string.message_app_exit))
        .setPositiveButton(R.string.label_yes) { dialog, id ->
            dialog.cancel()
            this.requireActivity().finish()
        }.setNegativeButton(R.string.label_no) { dialog, id ->
            dialog.cancel()
        }.create().show()
}

/*Extension method to show the snackBar*/
fun View.showSnackBar(text: String) {
    Snackbar.make(this, text, Snackbar.LENGTH_SHORT).show()
}

/*Extension method to make the view gone*/
fun View.gone() {
    visibility = View.GONE
}

/*Extension method to make the view visible*/
fun View.visible() {
    visibility = View.VISIBLE
}
