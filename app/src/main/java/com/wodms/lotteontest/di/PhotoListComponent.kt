package com.wodms.lotteontest.api

import com.wodms.lotteontest.MainActivity
import dagger.Component

@Component(modules = [PhotoListModule::class])
interface PhotoListComponent {
    fun inject(Activity: MainActivity)
}
