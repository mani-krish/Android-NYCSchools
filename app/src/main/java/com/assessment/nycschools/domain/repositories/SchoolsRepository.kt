package com.assessment.nycschools.domain.repositories

import com.assessment.nycschools.data.models.School
import com.assessment.nycschools.data.models.SchoolDetail
import com.assessment.nycschools.utils.ResponseHandler
import kotlinx.coroutines.flow.Flow

interface SchoolsRepository {
    suspend fun getSchools(): Flow<ResponseHandler<List<School>>>
    suspend fun getSchool(): Flow<ResponseHandler<List<SchoolDetail>>>
}