package com.nemo.ktmvvm.ui.login

import android.app.Application
import android.text.TextUtils
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.nemo.ktmvvm.net.source.user.UserApiClientImpl
import com.nemo.ktmvvm.ui.main.MainActivity
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand
import me.goldze.mvvmhabit.binding.command.BindingConsumer
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent
import me.goldze.mvvmhabit.utils.RxUtils
import me.goldze.mvvmhabit.utils.ToastUtils

class LoginViewModel(application: Application, model: UserApiClientImpl?) : BaseViewModel<UserApiClientImpl>(application, model) {

    //用户名的绑定
    var userName = ObservableField("")

    //密码的绑定
    var password = ObservableField("")

    //用户名清除按钮的显示隐藏绑定
    var clearBtnVisibility = ObservableInt()

    //封装一个界面发生改变的观察者
    var uc = UIChangeObservable()

    class UIChangeObservable {
        //密码开关观察者
        var pSwitchEvent = SingleLiveEvent<Boolean>()
    }

    //清除用户名的点击事件, 逻辑从View层转换到ViewModel层
    var clearUserNameOnClickCommand: BindingCommand<*> = BindingCommand<Any?>(BindingAction { userName.set("") })

    //密码显示开关  (你可以尝试着狂按这个按钮,会发现它有防多次点击的功能)
    var passwordShowSwitchOnClickCommand: BindingCommand<*> = BindingCommand<Any?>(BindingAction { //让观察者的数据改变,逻辑从ViewModel层转到View层，在View层的监听则会被调用
        uc.pSwitchEvent.setValue(uc.pSwitchEvent.value == null || !uc.pSwitchEvent.value!!)
    })

    //用户名输入框焦点改变的回调事件
    var onFocusChangeCommand = BindingCommand(BindingConsumer<Boolean> { hasFocus ->
        if (hasFocus) {
            clearBtnVisibility.set(View.VISIBLE)
        } else {
            clearBtnVisibility.set(View.INVISIBLE)
        }
    })

    //登录按钮的点击事件
    var loginOnClickCommand: BindingCommand<*> = BindingCommand<Any?>(BindingAction { login() })

    /**
     * 网络模拟一个登陆操作
     */
    private fun login() {
        if (TextUtils.isEmpty(userName.get())) {
            ToastUtils.showShort("请输入账号！")
            return
        }
        if (TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入密码！")
            return
        }
        //RaJava模拟登录
        addSubscribe(model.login()!!
                .compose(RxUtils.schedulersTransformer()) //线程调度
                .doOnSubscribe { showDialog() }
                .subscribe {
                    dismissDialog()
                    //保存账号密码
                    //进入DemoActivity页面
                    startActivity(MainActivity::class.java)
                    //关闭页面
                    finish()
                })
    }

}