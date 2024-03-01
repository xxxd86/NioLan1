package com.xxxd86.niolan.page

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.xxxd86.niobase.base.baseActivity.BaseActivityLate
import com.xxxd86.niobase.base.baseActivity.BaseVMActivity
import com.xxxd86.niobase.base.baseService.BaseRNService
import com.xxxd86.niobase.base.broadcast.BaseBroadCast
import com.xxxd86.niolan.R
import com.xxxd86.niolan.databinding.ActivityAppBinding
import com.xxxd86.niolan.page.home.HomeFragment
import com.xxxd86.niolan.page.my.ProfitFragment
import com.xxxd86.niolan.page.search.SearchFragment

/**
 * 包含app中类似主页什么的内容
 */
class AppActivity : BaseVMActivity<AppViewModel,ActivityAppBinding>(ActivityAppBinding::inflate) {
    val fragmentManager = supportFragmentManager;  //使用fragmentmanager和transaction来实现切换效果
    val transaction = fragmentManager.beginTransaction();
    private var homeFragment: HomeFragment? = null
    private var searchFragment:SearchFragment? = null
    private var profitFragment:ProfitFragment? = null
    private var contentId = -1
    private var lastShowFragment: Fragment? = null
    private val REQUEST_CODE = 1024
    @SuppressLint("CommitTransaction")
    override fun initView() {
        contentId = binding.container.id
        initFirstFragment()
        binding.navBottom.setOnItemSelectedListener {item->
            if(item.itemId!=binding.navBottom.selectedItemId){
                when(item.itemId){
                    R.id.tab_home ->{
                        showFragment(homeFragment!!)
                    }
                    R.id.tab_search ->{
                        if (searchFragment == null){
                            searchFragment = SearchFragment()
                            supportFragmentManager.beginTransaction()
                                .add(contentId,searchFragment!!,"TableFragment")
                                .commit()
                        }
                        showFragment(searchFragment!!)

                    }
                    R.id.tab_profile ->{
                        if (profitFragment == null){
                            profitFragment = ProfitFragment()
                            supportFragmentManager.beginTransaction()
                                .add(contentId,profitFragment!!,"TableFragment")
                                .commit()

                        }
                        showFragment(profitFragment!!)
                    }
                }
                return@setOnItemSelectedListener true
            }
            false

        }
       BaseBroadCast.RegistryUI_ModeChangeBroadcastReciver(this) {
           val restartIntent = baseContext.packageManager.getLaunchIntentForPackage(baseContext.packageName)
           val pendingIntent = PendingIntent.getActivity(baseContext,0,restartIntent,
               PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
           //获取AlarmManager
           val alarmManager  = baseContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

           val restartTime = System.currentTimeMillis() + 1000;
           alarmManager.set(AlarmManager.RTC,restartTime,pendingIntent)
       }
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

    private fun showFragment(fragmentToShow: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.show(fragmentToShow)
        lastShowFragment?.let {
            transaction.hide(it)
        }
        lastShowFragment = fragmentToShow
        transaction.commit()
    }
    private fun initFirstFragment() {
        homeFragment = HomeFragment()
        transaction
            .show(homeFragment!!)
            .add(contentId, homeFragment!!, "HomeFragment")
            .commit()
        lastShowFragment = homeFragment
    }
}