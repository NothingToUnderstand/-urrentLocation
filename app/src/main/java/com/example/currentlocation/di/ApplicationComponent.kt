package com.example.currentlocation.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class,SubcomponentsModule::class])
interface ApplicationComponent{
    fun locationComponent(): LocationComponent.Factory
}
