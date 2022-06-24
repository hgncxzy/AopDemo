package com.example.aopdemo.aop.log2

/**
 * description: 定义日志注解 -kt版本
 */
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class LogMethodKt(
    val before: Boolean = false,
    val after: Boolean = false,
    val desc: String = ""
)