package com.assessment.nycschools.data.datasources

import com.assessment.nycschools.data.models.School
import com.assessment.nycschools.data.models.SchoolDetail
import com.assessment.nycschools.data.services.WebService
import com.assessment.nycschools.utils.Constants
import com.assessment.nycschools.utils.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/*Call the remote source - API - Countries*/
class RemoteDataSource @Inject constructor(private val api: WebService) {
    fun getSchools(): Flow<ResponseHandler<List<School>>> =
        flow {
            try {
                emit(ResponseHandler.Loading())
                val response = api.getSchools()
                if (response.isSuccessful) {
                    emit(ResponseHandler.Success(response.body()))
                } else {
                    emit(ResponseHandler.Failure(response.message()))
                }
            } catch (e: Exception) {
                emit(ResponseHandler.Failure(e.message.toString()))
            }
        }.flowOn((Dispatchers.IO))

    fun getSchool(): Flow<ResponseHandler<List<SchoolDetail>>> =
        flow {
            try {
                emit(ResponseHandler.Loading())
                val response = api.getSchoolDetail(Constants.SCHOOL_DBN)
                if (response.isSuccessful) {
                    emit(ResponseHandler.Success(response.body()))
                } else {
                    emit(ResponseHandler.Failure(response.message()))
                }
            } catch (e: Exception) {
                emit(ResponseHandler.Failure(e.message.toString()))
            }
        }.flowOn((Dispatchers.IO))
}