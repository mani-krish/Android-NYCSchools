package com.assessment.nycschools.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assessment.nycschools.R
import com.assessment.nycschools.data.model.SchoolDetail
import com.assessment.nycschools.domain.usecase.GetSchoolsUseCase
import com.assessment.nycschools.utils.ResourceProvider
import com.assessment.nycschools.utils.ResponseHandler
import com.assessment.nycschools.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(
    private val useCase: GetSchoolsUseCase, private val resourceProvider: ResourceProvider
) : ViewModel() {
    // Backing property to avoid state updates from other classes
    private val _uiState: MutableStateFlow<ResponseHandler<List<SchoolDetail>>> =
        MutableStateFlow(ResponseHandler.Loading())

    // The UI collects from this StateFlow to get its state updates
    val uiState: StateFlow<ResponseHandler<List<SchoolDetail>>> = _uiState

    /*Load the countries*/
     fun fetchSchool() {
        viewModelScope.launch {
            try {
                useCase.executeSchool().onStart {
                    _uiState.value = ResponseHandler.Loading()
                }.catch {
                    _uiState.value = ResponseHandler.Error(it.message.toString())
                }.collect {
                    when (it.status) {
                        Status.SUCCESS -> {
                            _uiState.value = ResponseHandler.Success(it.data)
                        }

                        Status.LOADING -> {
                            _uiState.value = ResponseHandler.Loading()
                        }

                        else -> {
                            _uiState.value =
                                ResponseHandler.Error(resourceProvider.getString(R.string.message_no_data))
                        }
                    }
                }
            } catch (e: HttpException) {
                _uiState.value = ResponseHandler.Error(
                    e.message ?: resourceProvider.getString(R.string.message_general_error)
                )
            } catch (e: IOException) {
                _uiState.value =
                    ResponseHandler.Error(resourceProvider.getString(R.string.message_internet_error))
            } catch (e: Exception) {
                _uiState.value =
                    ResponseHandler.Error(resourceProvider.getString(R.string.message_general_error))
            }
        }
    }

}