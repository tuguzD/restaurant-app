package io.github.tuguzd.restaurantapp.presentation.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OrderRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OrderItemRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MenuRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MenuItemRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServiceRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServiceItemRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServiceItemPointRetrofit
