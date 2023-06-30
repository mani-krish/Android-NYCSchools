package com.assessment.nycschools.domain.usecase

import com.assessment.nycschools.data.model.School
import com.assessment.nycschools.data.model.SchoolDetail
import com.assessment.nycschools.domain.repository.SchoolsRepository
import com.assessment.nycschools.utils.ResponseHandler
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*Use-case driven architecture - invoke the repository interface*/
class GetSchoolsUseCase @Inject constructor(private val repository: SchoolsRepository) {

    suspend fun executeSchools(): Flow<ResponseHandler<List<School>>> {
        return repository.getSchools()
    }

    suspend fun executeSchool(): Flow<ResponseHandler<List<SchoolDetail>>> {
        return repository.getSchool()
    }
}