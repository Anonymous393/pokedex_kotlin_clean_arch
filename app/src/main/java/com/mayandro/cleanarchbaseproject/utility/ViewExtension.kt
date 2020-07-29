package com.mayandro.cleanarchbaseproject.utility

import android.animation.ObjectAnimator
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView


fun ImageView.rotate(){
    val rotateAnimation = RotateAnimation(
        0f, 360f,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f

    )
    rotateAnimation.duration = 3000
    rotateAnimation.repeatCount = Animation.INFINITE
    this.startAnimation(rotateAnimation)
}

fun ImageView.rotateClockwise() {
    val rotate = ObjectAnimator.ofFloat(this, "rotation", 180f, 0f)
    rotate.repeatCount = Animation.INFINITE
    rotate.duration = 3000
    rotate.start()
}