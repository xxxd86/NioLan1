package com.xxxd86.niolan.advertise

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.xxxd86.niobase.base.baseActivity.BaseLoadingActivity
import com.xxxd86.niolan.R
import com.xxxd86.niolan.databinding.ActivitySplashBinding
import com.xxxd86.niolan.page.AppActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseLoadingActivity<SplashViewModel, ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    private val AD_TIMEOUT = 2000
    private val MSG_AD_TIMEOUT = 1001

    // 收到广告展示超时消息时的回调处理
    private val timeoutHandler = Handler(object: Handler.Callback{
        override fun handleMessage(p0: Message): Boolean {
            if (hasWindowFocus()) {
                jump();
            }
            return false;
        }
    })
    /**
     * 暂停标志位。
     * 在开屏广告页面展示时：
     * 按返回键退出应用时需设置为true，以确保应用主界面不被拉起；
     * 切换至其他界面时需设置为false，以确保从其他页面回到开屏广告页面时仍然可以正常跳转至应用主界面；
     */
    private var  hasPaused = false;

    override fun initView() {
//        val intent = Intent(this, BaseRNService::class.java) //处理resource文件以及debugText文件的创立
//        startService(intent)
        //初始化OpenCV
//        OpenCV.initOpenCV()
//        //初始化WeChatQRCodeDetector
//        WeChatQRCodeDetector.init(applicationContext)
//            lifecycleScope.launch {
//                  withContext(Dispatchers.IO){
//                      PKJChannel.getRFromPython_KOTLIN("Hello","text", callback = {python->
//                          binding.text.text = python.toString()
//                      },this@SplashActivity)
//                  }
//            }


        loadAd()
    }
    private fun jump(){
        if (!hasPaused) {
            hasPaused = true;
            val intent = Intent(this,AppActivity::class.java)
            startActivityForResult(intent,101)
            finish();
        }

    }
    private fun loadAd(){
        val orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT//获取当前的状态

        // 发送延时消息，保证广告显示超时后，APP首页可以正常显示
        timeoutHandler.removeMessages(MSG_AD_TIMEOUT);
        timeoutHandler.sendEmptyMessageDelayed(MSG_AD_TIMEOUT, AD_TIMEOUT.toLong());
    }

    override fun onStop() {
        // 移除消息队列中等待的超时消息
        timeoutHandler.removeMessages(MSG_AD_TIMEOUT);
        hasPaused = true;
        super.onStop();
    }

    override fun onRestart() {
        super.onRestart();
        hasPaused = false;
        jump();
    }
//    fun getResourceFromIntern(){
//        DeviceUtils.checkLocationPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION,
//            lackPermission = {
//                /**
//                 * 获取网络
//                 */
//            },
//            havePermission = {
//                /**
//                 * 进行操作
//                 */
//            }
//
//            )
//    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onNightChange(onSuccess: () -> Unit): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLanguageChange(onSuccess: () -> Unit): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScreanChange(onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }
}