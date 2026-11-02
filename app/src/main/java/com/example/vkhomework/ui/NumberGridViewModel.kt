package com.example.vkhomework.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow


class NumberGridViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val KEY_ITEM_COUNT = "item_count"
        private const val DEFAULT_ITEM_COUNT = 0
    }

    val itemCount: StateFlow<Int> = savedStateHandle.getStateFlow(KEY_ITEM_COUNT, DEFAULT_ITEM_COUNT)

    fun addItem() {
        val currentCount = itemCount.value
        savedStateHandle[KEY_ITEM_COUNT] = currentCount + 1
    }
}
