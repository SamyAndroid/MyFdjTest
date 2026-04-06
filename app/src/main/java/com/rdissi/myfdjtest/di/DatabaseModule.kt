package com.rdissi.myfdjtest.di

import android.content.Context
import androidx.room.Room
import com.rdissi.myfdjtest.data.local.TournamentDatabase
import com.rdissi.myfdjtest.data.local.dao.TournamentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): TournamentDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TournamentDatabase::class.java,
            "tournament.db",
        ).build()
    }

    @Provides
    fun provideLogDao(database: TournamentDatabase): TournamentDao {
        return database.tournamentDao()
    }
}
