package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.SubCategory
import com.kormosathi.app.repository.SubCategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SubCategoryUiState(
    val isLoading: Boolean = false,
    val subCategories: List<SubCategory> = emptyList(),
    val error: String? = null
)

class SubCategoryViewModel : ViewModel() {

    private val repository = SubCategoryRepository()

    private val _uiState = MutableStateFlow(SubCategoryUiState())
    val uiState: StateFlow<SubCategoryUiState> = _uiState.asStateFlow()

    fun load(categoryId: String) {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(isLoading = true)

            val data = repository.getSubCategories(categoryId)

            _uiState.value = SubCategoryUiState(
                isLoading = false,
                subCategories = data
            )

        }

    }

}