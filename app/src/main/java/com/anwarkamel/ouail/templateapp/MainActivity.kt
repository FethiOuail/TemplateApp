package com.anwarkamel.ouail.templateapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import com.anwarkamel.ouail.templateapp.activities.LoginActivity
import com.anwarkamel.ouail.templateapp.data.SharedPrefManager
import com.anwarkamel.ouail.templateapp.utils.startActivity
import com.anwarkamel.ouail.templateapp.utils.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    var currentlang: String? = null


    override fun onStart() {
        super.onStart()
        currentlang = SharedPrefManager.getInstance(this).lang


        setAppLocale(this, currentlang!!)


        Log.i("clang", currentlang)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currentlang = SharedPrefManager.getInstance(this).lang

        if (currentlang == "ar") {
            setAppLocale(this, "ar")

            SharedPrefManager.getInstance(applicationContext).saveLang("ar")


        }

        toast(currentlang.toString())

       btn_one.setOnClickListener {
           CoroutineScope(Dispatchers.Main).launch {

               toast("Hi this is toast")

             //  btn_tow.startAnimation(AnimationUtils.loadAnimation(application, R.anim.splash_in))

               btn_tow.startAnimation(AnimationUtils.loadAnimation(application, R.anim.slide_down))

             //  btn_tow.startAnimation(AnimationUtils.loadAnimation(application, R.anim.slide_up))


           }
       }
        btn_tow.setOnClickListener {
            toast(currentlang.toString())
        }


        btn_tow.startAnimation(AnimationUtils.loadAnimation(application, R.anim.splash_in))

        btn_login.setOnClickListener { startActivity<LoginActivity>() }


        btn_three.setOnClickListener {
            setAppLocale(this, "ar")

            SharedPrefManager.getInstance(applicationContext).saveLang("ar")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        btn_four.setOnClickListener {

            setAppLocale(this, "en")

            SharedPrefManager.getInstance(applicationContext).saveLang("en")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


    fun setAppLocale(context: Context, language: String) {

        val locale = Locale(language)

        Locale.setDefault(locale)

        val config = context.resources.configuration

        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)

    }
}
