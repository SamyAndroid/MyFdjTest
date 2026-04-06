package com.rdissi.myfdjtest.domain.usecase

import com.rdissi.myfdjtest.domain.model.LeaguesResponse
import com.rdissi.myfdjtest.domain.repository.LeaguesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.rdissi.myfdjtest.common.Result
import com.rdissi.myfdjtest.domain.model.League

class GetLeaguesUseCase @Inject constructor(
    private val leaguesRepository: LeaguesRepository
) {
    operator fun invoke(): Flow<Result<List<League>>> = flow {
        emit(Result.Loading)
        runCatching {
            val leaguesResponse: LeaguesResponse? = leaguesRepository.getLeagues()
            leaguesResponse?.leagues ?: throw Exception("leagues null")
        }.onSuccess { leagues ->
            emit(Result.Success(leagues))
        }.onFailure {
            emit(Result.Error("Error=$it"))
        }
    }
}
