package com.assessment.nycschools

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        keepSplashScreenFor5Seconds()
        setContentView(R.layout.activity_main)

        // Because we're creating the NavHostFragment using FragmentContainerView, we must
        // retrieve the NavController directly from the NavHostFragment instead

        // Get the navigation host fragment from this Activity
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        // Instantiate the navController using the NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)
        findViewById<Toolbar>(R.id.toolbar)
            .setupWithNavController(navController, appBarConfiguration)
    }

    /* Keep splash screen on-screen for longer period. This is useful if you need to load data when splash screen is appearing */
    private fun keepSplashScreenFor5Seconds() {
        val content = findViewById<View>(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                Thread.sleep(1000)
                content.viewTreeObserver.removeOnPreDrawListener(this)
                return true
            }
        })
    }
}