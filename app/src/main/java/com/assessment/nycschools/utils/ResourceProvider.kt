package com.assessment.nycschools.utils

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/*Get the string value by resource ID*/
@Singleton
class ResourceProvider @Inject constructor(@ApplicationContext private val context: Context) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }
}