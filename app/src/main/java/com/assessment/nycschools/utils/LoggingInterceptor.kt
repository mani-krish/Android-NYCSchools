package com.assessment.nycschools.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/*Log the request and response using interceptor*/
class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        return chain.proceed(request)
    }
}