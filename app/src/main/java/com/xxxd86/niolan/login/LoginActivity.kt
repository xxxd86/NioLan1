package com.xxxd86.niolan.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xxxd86.niobase.base.baseActivity.BaseVMActivity
import com.xxxd86.niolan.R
import com.xxxd86.niolan.databinding.ActivityLoginBinding
//普通登录界面
class LoginActivity : BaseVMActivity<LoginViewModel,ActivityLoginBinding>(ActivityLoginBinding::inflate) {


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