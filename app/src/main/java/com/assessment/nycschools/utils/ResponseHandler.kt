package com.assessment.nycschools.utils

/*API response handler with Success, Failure, Loading*/
sealed class ResponseHandler<T>(
    val status: Status,
    val data: T?,
    val message: String?
) {
    class Success<T>(data: T?) : ResponseHandler<T>(Status.SUCCESS, data, null)
    class Error<T>(errorMessage: String?) :
        ResponseHandler<T>(Status.ERROR, null, errorMessage)

    class Loading<T> : ResponseHandler<T>(Status.LOADING, null, null)
}
