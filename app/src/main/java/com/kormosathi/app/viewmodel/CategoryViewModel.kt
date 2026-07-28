package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Category
import com.kormosathi.app.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class CategoryUiState(
    val isLoading: Boolean = false,
    val categories: List<Category> = emptyList(),
    val error: String? = null
)

class CategoryViewModel : ViewModel() {

    private val repository = CategoryRepository()

    private val _uiState = MutableStateFlow(CategoryUiState())
    val uiState: StateFlow<CategoryUiState> = _uiState.asStateFlow()

    init {
        loadCategories()
    }

    fun loadCategories() {

        viewModelScope.launch {

            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {

                val categories = repository.getCategories()

                _uiState.value = CategoryUiState(
                    isLoading = false,
                    categories = categories
                )

            } catch (e: Exception) {

                _uiState.value = CategoryUiState(
                    isLoading = false,
                    error = e.message
                )

            }

        }

    }

}