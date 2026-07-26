package com.kormosathi.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kormosathi.app.model.Employer
import com.kormosathi.app.model.Job
import com.kormosathi.app.model.JobApplication
import com.kormosathi.app.repository.EmployerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class EmployerUiState(
    val isLoading: Boolean = false,
    val employer: Employer? = null,
    val jobs: List<Job> = emptyList(),
    val applicants: List<JobApplication> = emptyList(),
    val isSuccess: Boolean = false,
    val errorMessage: String = ""
)

class EmployerViewModel : ViewModel() {

    private val repository = EmployerRepository()

    private val _uiState = MutableStateFlow(EmployerUiState())
    val uiState: StateFlow<EmployerUiState> = _uiState.asStateFlow()

    init {
        loadEmployerProfile()
    }

    private fun loadEmployerProfile() {
        viewModelScope.launch {
            _uiState.value = EmployerUiState(isLoading = true)

            val employer = repository.getEmployerProfile()
            if (employer != null) {
                _uiState.value = EmployerUiState(
                    isLoading = false,
                    employer = employer
                )
                loadEmployerJobs()
            } else {
                _uiState.value = EmployerUiState(
                    isLoading = false,
                    errorMessage = "Employer profile not found"
                )
            }
        }
    }

    fun createEmployerProfile(employer: Employer) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.createEmployerProfile(employer)
                .onSuccess {
                    _uiState.value = EmployerUiState(
                        isLoading = false,
                        isSuccess = true,
                        employer = employer
                    )
                    loadEmployerJobs()
                }
                .onFailure { exception ->
                    _uiState.value = EmployerUiState(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to create employer profile"
                    )
                }
        }
    }

    fun postJob(
        title: String,
        description: String,
        category: String,
        district: String,
        block: String,
        salary: Double
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val job = Job(
                jobId = "",
                title = title,
                description = description,
                category = category,
                district = district,
                block = block,
                salary = salary.toString(),
                employerUid = _uiState.value.employer?.uid ?: "",
                employerName = _uiState.value.employer?.companyName ?: "",
                phone = _uiState.value.employer?.phone ?: "",
                createdAt = System.currentTimeMillis(),
                status = "active"
            )

            repository.postJob(job)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadEmployerJobs()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to post job"
                    )
                }
        }
    }

    fun postJob(job: Job) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.postJob(job)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadEmployerJobs()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to post job"
                    )
                }
        }
    }

    fun updateJob(
        jobId: String,
        title: String,
        description: String,
        category: String,
        district: String,
        block: String,
        salary: Double
    ) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val existingJob = _uiState.value.jobs.find { it.jobId == jobId }
            val job = existingJob?.copy(
                title = title,
                description = description,
                category = category,
                district = district,
                block = block,
                salary = salary.toString()
            ) ?: Job(
                jobId = jobId,
                title = title,
                description = description,
                category = category,
                district = district,
                block = block,
                salary = salary.toString(),
                employerUid = _uiState.value.employer?.uid ?: "",
                employerName = _uiState.value.employer?.companyName ?: "",
                phone = _uiState.value.employer?.phone ?: "",
                createdAt = System.currentTimeMillis(),
                status = "active"
            )

            repository.editJob(job)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadEmployerJobs()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to edit job"
                    )
                }
        }
    }

    fun editJob(job: Job) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.editJob(job)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadEmployerJobs()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to edit job"
                    )
                }
        }
    }

    fun deleteJob(jobId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            repository.deleteJob(jobId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true, isLoading = false)
                    loadEmployerJobs()
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = exception.message ?: "Failed to delete job"
                    )
                }
        }
    }

    private fun loadEmployerJobs() {
        viewModelScope.launch {
            val jobs = repository.getEmployerJobs()
            _uiState.value = _uiState.value.copy(jobs = jobs)
        }
    }

    fun getApplicants(jobId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val applicants = repository.getJobApplicants(jobId)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                applicants = applicants
            )
        }
    }

    fun loadJobApplicants(jobId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)

            val applicants = repository.getJobApplicants(jobId)
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                applicants = applicants
            )
        }
    }

    fun acceptApplicant(jobId: String, applicationId: String) {
        viewModelScope.launch {
            repository.acceptApplicant(applicationId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    getApplicants(jobId)
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to accept applicant"
                    )
                }
        }
    }

    fun rejectApplicant(jobId: String, applicationId: String) {
        viewModelScope.launch {
            repository.rejectApplicant(applicationId)
                .onSuccess {
                    _uiState.value = _uiState.value.copy(isSuccess = true)
                    getApplicants(jobId)
                }
                .onFailure { exception ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = exception.message ?: "Failed to reject applicant"
                    )
                }
        }
    }

    fun clearSuccess() {
        _uiState.value = _uiState.value.copy(isSuccess = false)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = "")
    }
}
