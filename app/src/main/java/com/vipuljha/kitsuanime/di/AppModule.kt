package com.vipuljha.kitsuanime.di

import com.vipuljha.kitsuanime.core.network.RetrofitProvider
import com.vipuljha.kitsuanime.features.anime.data.datasources.network.KitsuApi
import com.vipuljha.kitsuanime.features.anime.data.repositories.KitsuRepositoryImpl
import com.vipuljha.kitsuanime.features.anime.domain.repositories.KitsuRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideKitsuApi(): KitsuApi {
        return RetrofitProvider.animeApi
    }

    @Provides
    @Singleton
    fun provideKitsuRepository(api: KitsuApi): KitsuRepository {
        return KitsuRepositoryImpl(api)
    }
}
