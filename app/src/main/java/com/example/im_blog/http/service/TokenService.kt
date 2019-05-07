package com.example.im_blog.http.service

import com.example.im_blog.http.entities.TokenRequestEntity
import com.example.im_blog.utilies.CLIENT_ID
import com.example.im_blog.utilies.CLIENT_SECRET
import com.example.im_blog.utilies.REDIRECT_URL
import io.reactivex.Flowable
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query
import java.net.URLDecoder
import java.net.URLEncoder

interface TokenService {

    @POST("/oauth2/access_token")
    fun getToken(@Query("client_id")client_id:Int = CLIENT_ID,
                 @Query("client_secret")client_secret:String = CLIENT_SECRET,
                 @Query("grant_type")client_type:String = "authorization_code",
                 @Query("redirect_uri")redirect_url:String = REDIRECT_URL,
                 @Query("code")code:String):Flowable<TokenRequestEntity>
}
