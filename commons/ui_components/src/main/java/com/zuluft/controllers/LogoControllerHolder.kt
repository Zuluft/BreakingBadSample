package com.zuluft.controllers

interface LogoControllerHolder {
    fun attachController(logoController: LogoController, sceneFinishListener: () -> Unit)
    fun detachController()
}