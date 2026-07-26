package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Job
import com.kormosathi.app.repository.JobRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class JobUiState(
    val isLoading: Boolean = false,
    val jobs: List<Job> = emptyList(),
    val selectedJob: Job? = null,
    val errorMessage: String = "",
    val searchTitle: String = "",
    val searchCategory: String = "",
    val searchDistrict: String = ""
)

class JobViewModel : ViewModel() {

    private val repository = JobRepository()

    private val _uiState = MutableStateFlow(JobUiState())
    val uiState: StateFlow<JobUiState> = _uiState.asStateFlow()

    init {
        loadAllJobs()
    }

    private fun loadAllJobs() {
        viewModelScope.launch {
            _uiState.value = JobUiState(isLoading = true)
            val jobs = repository.getAllJobs()
            _uiState.value = JobUiState(isLoading = false, jobs = jobs)
        }
    }

    fun searchJobs(title: String, category: String, district: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                searchTitle = title,
                searchCategory = category,
                searchDistrict = district
            )

            val jobs = repository.searchJobs(title, category, district)
            _uiState.value = JobUiState(
                isLoading = false,
                jobs = jobs,
                searchTitle = title,
                searchCategory = category,
                searchDistrict = district
            )
        }
    }

    fun filterByDistrict(district: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, searchDistrict = district)

            val jobs = repository.filterByDistrict(district)
            _uiState.value = JobUiState(
                isLoading = false,
                jobs = jobs,
                searchDistrict = district
            )
        }
    }

    fun filterByCategory(category: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, searchCategory = category)

            val jobs = repository.filterByCategory(category)
            _uiState.value = JobUiState(
                isLoading = false,
                jobs = jobs,
                searchCategory = category
            )
        }
    }

    fun getJobDetails(jobId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val job = repository.getJobById(jobId)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                selectedJob = job,
                errorMessage = if (job == null) "Job not found" else ""
            )
        }
    }

    fun resetSearch() {
        loadAllJobs()
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = "")
    }
}
