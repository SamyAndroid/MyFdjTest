package com.rdissi.myfdjtest.di

import com.rdissi.myfdjtest.data.repository.TeamsRepositoryImpl
import com.rdissi.myfdjtest.data.repository.LeaguesRepositoryImpl
import com.rdissi.myfdjtest.domain.repository.TeamsRepository
import com.rdissi.myfdjtest.domain.repository.LeaguesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindLeaguesRepository(repository: LeaguesRepositoryImpl): LeaguesRepository

    @Singleton
    @Binds
    abstract fun bindTeamsRepository(repository: TeamsRepositoryImpl): TeamsRepository
}