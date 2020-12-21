package com.mieszko.mybmicalculator.presentation.statistics

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.mieszko.mybmicalculator.R


@BindingAdapter("entries")
fun setEntries(
    viewGroup: ViewGroup,
    entries: Map<String, String>
) {
    viewGroup.removeAllViews()
    if (entries.isNotEmpty()) {
        val inflater = viewGroup.context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for (i in entries.keys) {
            val binding = DataBindingUtil
                .inflate<ViewDataBinding>(inflater, R.layout.calculations_item, viewGroup, true)
            binding.setVariable(BR.key, i)
            binding.setVariable(BR.value, entries[i])
        }
    }
}