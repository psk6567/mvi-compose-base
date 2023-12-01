package kr.co.psk.data.di

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kr.co.psk.common.BuildConfig
import kr.co.psk.data.datasource.retrofit2.Retrofit2DataSource
import kr.co.psk.data.datasource.retrofit2.Retrofit2Service
import kr.co.psk.data.mapper.Retrofit2Mapper
import kr.co.psk.data.repository.Retrofit2Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class Retrofit2Module {

    @Provides
    fun provideRetrofit2(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .client(OkHttpClient.Builder().run {
                //TODO : 차후에 삭제해야함.
                addInterceptor(HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)

                })
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
                connectTimeout(20, TimeUnit.SECONDS)
                build()
            })
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideRetrofit2Service(retrofit: Retrofit): Retrofit2Service =
        retrofit.create(Retrofit2Service::class.java)

    @Provides
    fun provideRetrofit2Mapper(
        retrofit2DataSource: Retrofit2DataSource
    ): Retrofit2Repository {
        return Retrofit2Mapper(retrofit2DataSource)
    }
}