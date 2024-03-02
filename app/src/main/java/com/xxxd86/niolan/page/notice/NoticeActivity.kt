package com.xxxd86.niolan.page.notice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxxd86.niobase.base.baseActivity.BaseActivityLate
import com.xxxd86.niolan.R
import com.xxxd86.niolan.databinding.ActivityNoticeBinding

class NoticeActivity : BaseActivityLate<ActivityNoticeBinding>(ActivityNoticeBinding::inflate) {
    override fun initView() {
        super.initView()
        binding.check.setOnClickListener {
            val intents = Intent()
        //广播标志. 在静态注册中即在在AndroidManifest中需拦截这个action。
        intents.setAction("com.broadcast.Notification");
        //数据.
        intents.putExtra("Notification","牛逼");

        intents.setPackage(packageName)
        //发送广播
        sendBroadcast(intents)
        }
    }
}