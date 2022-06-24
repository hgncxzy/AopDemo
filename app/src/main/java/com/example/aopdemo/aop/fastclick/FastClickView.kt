package com.example.aopdemo.aop.fastclick

/**
 * description: 点击次数限制注解
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class FastClickView(val interval: Long = 3000L)