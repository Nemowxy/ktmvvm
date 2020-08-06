package com.nemo.ktmvvm.app;

import com.nemo.ktmvvm.net.RetrofitClient;
import com.nemo.ktmvvm.net.source.main.MainApiClientImpl;
import com.nemo.ktmvvm.net.source.main.MainApiService;
import com.nemo.ktmvvm.net.source.user.UserApiClientImpl;
import com.nemo.ktmvvm.net.source.user.UserApiService;


/**
 * 注入全局的数据仓库，可以考虑使用Dagger2。（根据项目实际情况搭建，千万不要为了架构而架构）
 * Created by goldze on 2019/3/26.
 */
public class Injection {
    public static UserApiClientImpl provideUserRepository() {
        //网络API服务
        UserApiService apiService = RetrofitClient.Companion.getInstance().create(UserApiService.class);
        //网络数据源
        return UserApiClientImpl.Companion.getInstance(apiService);
    }

    public static MainApiClientImpl provideMainRepository() {
        //网络API服务
        MainApiService apiService = RetrofitClient.Companion.getInstance().create(MainApiService.class);
        //网络数据源
        return MainApiClientImpl.Companion.getInstance(apiService);
    }
}
