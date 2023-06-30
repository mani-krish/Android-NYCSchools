package com.assessment.nycschools.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/*Log the request and response using interceptor*/
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response: Response = chain.proceed(request)
        Log.d("LoggingInterceptor request-url", request.url.toString())
        Log.d("LoggingInterceptor request-headers", request.headers.toString())
        Log.d("LoggingInterceptor request-body", request.body.toString())
        Log.d("LoggingInterceptor request-response-body", response.body.toString())
        Log.d("LoggingInterceptor request-response-code", response.code.toString())
        return response
    }
}