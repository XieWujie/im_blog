package com.example.im_blog.repository

import android.util.Log
import com.example.im_blog.http.entities.TokenRequestEntity
import com.example.im_blog.http.service.TokenService
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception


class LoginRepository(local:LoginLocalDataSource,remote:LoginRemoteDataSource):
    BothRepository<LoginLocalDataSource,LoginRemoteDataSource>(local,remote){

    fun login(code:String):Flowable<TokenRequestEntity>{
        return remote.loginService.tokenService.getToken(code = code).subscribeOn(Schedulers.io())
    }

}


class LoginLocalDataSource(private val repository: LUserRepository):LocalDataSource{

    fun fetchAutoLoginEvent():Flowable<Boolean>{
        try {
            val token = repository.token
            val isAutoLogin = repository.autoLogin
            val uid = repository.uid

            UserManage.token = token?:""
            UserManage.uid = uid?:""
            Log.d("token", "token:")
            Log.d("uid", "uid:")
            return Flowable.just(!token.isNullOrBlank() && isAutoLogin && !uid.isNullOrBlank())
        }catch (e:Exception){
            e.printStackTrace()
        }
        return Flowable.just(false)
    }

    fun save(entity: TokenRequestEntity){
        try {
            Log.d("imblog_save", entity.toString())
            repository.token = entity.access_token
            repository.uid = entity.uid
            UserManage.token = entity.access_token
            UserManage.uid = entity.uid
            Log.d("token_save","token_save:"+repository.token)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}

class LoginRemoteDataSource(val loginService: LoginService):RemoteDataSource

data class LoginService(val tokenService: TokenService)

data class AutoLoginEvent(val token:String,val isAutoLogin:Boolean)


