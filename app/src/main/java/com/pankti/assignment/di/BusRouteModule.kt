package com.pankti.assignment.di

import com.pankti.assignment.MyApplication
import com.pankti.assignment.data.db.BusRouteDatabase
import com.pankti.assignment.data.network.Network
import com.pankti.assignment.data.network.RetrofitAPI
import com.pankti.assignment.data.repo.BusRouteRepositoryImpl
import com.pankti.assignment.domain.BusRouteDao
import com.pankti.assignment.domain.BusRouteRepository
import com.pankti.assignment.domain.BusRouteViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class BusRouteModule {

    @Provides
    @ViewModelScoped
    fun provideRetrofitAPI() : RetrofitAPI {
        return Network.init().apiService
    }

    @Provides
    @ViewModelScoped
    fun provideBusRouteDao() : BusRouteDao{
        return BusRouteDatabase.getDatabase(MyApplication.instance).busRouteDao()
    }

    @Provides
    @ViewModelScoped
    fun provideBusRouteRepository(api: RetrofitAPI,dao: BusRouteDao): BusRouteRepository = BusRouteRepositoryImpl(api,dao)


    @Provides
    @ViewModelScoped
    fun provideBusRouteViewModel(repository: BusRouteRepository): BusRouteViewModel = BusRouteViewModel(repository)

}