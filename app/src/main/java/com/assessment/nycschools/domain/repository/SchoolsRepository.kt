package com.assessment.nycschools.domain.repository

import com.assessment.nycschools.data.model.School
import com.assessment.nycschools.data.model.SchoolDetail
import com.assessment.nycschools.utils.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface SchoolsRepository {
    suspend fun getSchools(): Flow<ResponseHandler<List<School>>>
    suspend fun getSchool(): Flow<ResponseHandler<List<SchoolDetail>>>
}