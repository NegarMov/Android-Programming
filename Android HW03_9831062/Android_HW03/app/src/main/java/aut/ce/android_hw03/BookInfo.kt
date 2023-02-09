package aut.ce.android_hw03

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class BookInfo (
    @StringRes val titleResourceId: Int,
    @StringRes val authorResourceId: Int,
    @StringRes val summaryResourceId: Int,
    @StringRes val dateResourceId: Int,
    @DrawableRes val ImageResourceId: Int
    )