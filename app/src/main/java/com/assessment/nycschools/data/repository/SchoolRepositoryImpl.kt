package com.assessment.nycschools.data.repository

import com.assessment.nycschools.data.source.RemoteDataSource
import com.assessment.nycschools.domain.repository.SchoolsRepository
import com.assessment.nycschools.utils.ResponseHandler
import com.assessment.nycschools.data.model.School
import com.assessment.nycschools.data.model.SchoolDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/*Repository impl - Decide the remote or local data source*/
class SchoolRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    SchoolsRepository {
    override suspend fun getSchools(): Flow<ResponseHandler<List<School>>> {
        return remoteDataSource.getSchools()
    }

    override suspend fun getSchool(): Flow<ResponseHandler<List<SchoolDetail>>> {
        return remoteDataSource.getSchool()
    }
}
