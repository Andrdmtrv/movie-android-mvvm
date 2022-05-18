package my.movieproject.model.web

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import my.movieproject.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*

@InstallIn(SingletonComponent::class)
@Module
class WebModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson : GsonConverterFactory, client : OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kinopoiskapiunofficial.tech")
            .client(client)
            .addConverterFactory(gson)
            .build()
    }

    @Provides
    @Singleton
    fun provideGson() : GsonConverterFactory{
        return GsonConverterFactory.create(GsonBuilder().create())
    }


    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient {
        val trustAllCerts: Array<TrustManager> = arrayOf(
            object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {

                }

                override fun checkServerTrusted(
                    chain: Array<out X509Certificate>?,
                    authType: String?
                ) {

                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )

        // Install the all-trusting trust manager
        val sslContext: SSLContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)

        builder.hostnameVerifier(object : HostnameVerifier {
            override fun verify(hostname: String?, session: SSLSession?): Boolean {
                return true
            }
        })

        builder.connectTimeout(0, TimeUnit.MILLISECONDS)
        builder.readTimeout(0, TimeUnit.MILLISECONDS)
        builder.callTimeout(0, TimeUnit.MILLISECONDS)

        return builder.build()
    }

    @Singleton
    @Provides
    fun providesService(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }
}

/* Описание Api */
interface MovieApi {

    @GET("/api/v2.2/films/top")
    @Headers(
        "accept: application/json",
        "X-API-KEY: " + BuildConfig.API_KEY
    )
    suspend fun getMovieInfo(): MovieInfo
}