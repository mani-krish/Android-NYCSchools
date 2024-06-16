package com.assessment.nycschools.data.repositories

import com.assessment.nycschools.data.datasources.RemoteDataSource
import com.assessment.nycschools.domain.repositories.SchoolsRepository
import com.assessment.nycschools.utils.ResponseHandler
import com.assessment.nycschools.data.models.School
import com.assessment.nycschools.data.models.SchoolDetail
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
