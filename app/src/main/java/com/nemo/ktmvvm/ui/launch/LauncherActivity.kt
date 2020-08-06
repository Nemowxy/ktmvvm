package com.nemo.ktmvvm.ui.launch

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nemo.ktmvvm.R
import com.nemo.ktmvvm.config.SpKey
import com.nemo.ktmvvm.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_launcher.*
import me.goldze.mvvmhabit.utils.SPUtils


class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        val banner:BGABanner = banner_guide_foreground
        banner.setAdapter(BGABanner.Adapter<ImageView,String> { banner, itemView, model, position ->
            Glide.with(this@LauncherActivity)
                    .load(model)
                    .apply(RequestOptions().placeholder(R.drawable.holder))
                    .apply(RequestOptions().error(R.drawable.holder))
                    .apply(RequestOptions().centerCrop())
                    .apply(RequestOptions().dontAnimate())
                    .into(itemView)
        })

        banner.setData(listOf("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1478722931,2283544120&fm=26&gp=0.jpg",
                "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1773955996,868298793&fm=26&gp=0.jpg",
                "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3517189196,1347970046&fm=26&gp=0.jpg"),
                listOf("老虎", "天鹅", "狗"))

        banner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip) {
            SPUtils.getInstance().put(SpKey.IS_FIRST,false)
            startActivity(Intent(this@LauncherActivity, MainActivity::class.java))
            finish()
        }
    }
}