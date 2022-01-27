package com.zuluft.breakingbadapp.presentation

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zuluft.controllers.LogoController
import com.zuluft.controllers.LogoControllerHolder
import com.zuluft.controllers.LogoControllerImpl
import com.zuluft.breakingbadapp.R
import com.zuluft.controllers.BackgroundController
import com.zuluft.ui_components.image_crossfade_holder.ImageCrossFadeHolder

class MainActivity :
    AppCompatActivity(),
    LogoControllerHolder,
    BackgroundController {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun attachController(
        logoController: LogoController,
        sceneFinishListener: () -> Unit
    ) {
        if (supportFragmentManager.findFragmentById(android.R.id.content) == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    android.R.id.content,
                    (logoController as LogoControllerImpl)
                        .apply {
                            setAnimSceneListener(sceneFinishListener)
                        },
                    LOGO_CONTROLLER_TAG
                )
                .commitAllowingStateLoss()
        }
    }

    override fun detachController() {
        val controller = supportFragmentManager.findFragmentByTag(LOGO_CONTROLLER_TAG)
        if (controller != null) {
            supportFragmentManager
                .beginTransaction()
                .detach(controller)
                .remove(controller)
                .commitAllowingStateLoss()
        }
    }

    override fun loadBackground(drawable: Drawable) {
        findViewById<ImageCrossFadeHolder>(R.id.crossFadeHolder).loadDrawable(drawable)
    }

    companion object {
        private const val LOGO_CONTROLLER_TAG = "LOGO_CONTROLLER_TAG"
    }
}