package com.example.currentlocation.di

import com.example.currentlocation.BaseActivity
import com.example.currentlocation.BaseFragment
import dagger.Subcomponent
import javax.inject.Scope

@ActivityScope
@Subcomponent
interface LocationComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LocationComponent
    }

    fun inject(activity: BaseActivity)
    fun inject(fragment: BaseFragment)
}

@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope
