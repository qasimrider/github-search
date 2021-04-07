package com.dawn.common.mvi

interface IViewRenderer<STATE> {
    fun render(state: STATE)
}