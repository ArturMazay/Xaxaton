package com.crimsoftltd.xaxaton.di

import androidx.lifecycle.SavedStateHandle
import com.crimsoftltd.xaxaton.domain.ILoadData
import com.crimsoftltd.xaxaton.domain.ModelDomain
import com.crimsoftltd.xaxaton.domain.NetworkRepository
import com.crimsoftltd.xaxaton.domain.PlacesItemDomain
import com.crimsoftltd.xaxaton.map.DetailsViewModel
import com.crimsoftltd.xaxaton.network.ILoadDataFromNetwork
import com.crimsoftltd.xaxaton.ui.theme.FitnessViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModel = module {
    viewModel { FitnessViewModel(iLoadData = get()) }
   // viewModel { DetailsViewModel(destinationsRepository = get(),savedStateHandle = get()) }
    single { SavedStateHandle() }
  //  single { MapsActivity() }
    //factory { PlacesItemDomain(get(),get(),get(),get(),get(),get(),get(),get(),get(),get(),get()) }

}

val domain = module {
    single <ILoadData>{ NetworkRepository(iLoadDataFromNetwork = get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideApi(get()) }
    single { provideRetrofit(get()) }
}


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("http://resivalex.com:5001/").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient().newBuilder().addInterceptor(logger).build()
}

fun provideApi(retrofit: Retrofit): ILoadDataFromNetwork = retrofit.create(ILoadDataFromNetwork::class.java)
