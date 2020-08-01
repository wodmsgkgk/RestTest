package com.wodms.lotteontest.api

import com.wodms.lotteontest.MainActivity
import dagger.Component

@Component(modules = [UserListModule::class])
interface UserListComponent {
    fun inject(Activity: MainActivity)
}
