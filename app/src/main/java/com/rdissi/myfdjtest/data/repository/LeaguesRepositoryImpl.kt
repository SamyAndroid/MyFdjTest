package com.rdissi.myfdjtest.data.repository

import com.rdissi.myfdjtest.data.source.LocalDataSource
import com.rdissi.myfdjtest.data.source.RemoteDataSource
import com.rdissi.myfdjtest.data.source.EntityConverter.toLeaguesResponse
import com.rdissi.myfdjtest.data.source.EntityConverter.toLeaguesResponseEntity
import com.rdissi.myfdjtest.data.source.EntityConverter.toLeague
import com.rdissi.myfdjtest.data.source.JsonConverter.toLeaguesResponse
import com.rdissi.myfdjtest.di.IoDispatcher
import com.rdissi.myfdjtest.domain.model.LeaguesResponse
import com.rdissi.myfdjtest.domain.repository.LeaguesRepository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LeaguesRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : LeaguesRepository {

    override suspend fun getLeagues() = withContext(dispatcher) {
        try {
            val leaguesResponse: LeaguesResponse? = remoteDataSource.fetchLeagues()?.toLeaguesResponse()
            if (leaguesResponse != null) {
                updateLeaguesDB(leaguesResponse)
                leaguesResponse
            } else {
                fallbackLeaguesFromDB()

            }
        } catch (exception: Exception) {
            fallbackLeaguesFromDB() ?: throw exception
        }
    }

    private suspend fun fallbackLeaguesFromDB(): LeaguesResponse? {
        return if (localDataSource.isLeaguesNotEmpty()) {
            localDataSource.getLeaguesResponseEntity().toLeaguesResponse()
        } else {
            null
        }
    }

    override suspend fun getLeagueById(leagueId: String) = withContext(dispatcher) {
        localDataSource.getLeagueEntityById(leagueId).toLeague()
    }

    private suspend fun updateLeaguesDB(leaguesResponse: LeaguesResponse) {
        localDataSource.updateLeaguesResponseEntity(leaguesResponse.toLeaguesResponseEntity())
    }
}
