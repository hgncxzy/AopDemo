package com.example.aopdemo.aop.log2

import android.util.Log
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Around
import kotlin.Throws
import org.aspectj.lang.ProceedingJoinPoint
import com.example.aopdemo.aop.log2.LogMethodKt
import com.example.aopdemo.aop.log2.LogPrintUtilsKt
import org.aspectj.lang.annotation.AfterThrowing
import org.json.JSONObject
import org.json.JSONArray
import java.lang.UnsupportedOperationException
/**
 * Log 统一管理类 -kt版本
 */
class LogPrintUtilsKt private constructor() {
    companion object {
        // todo xzy 上线前切换开关为 false
        var isDebug = true // 是否需要打印bug，可以在application的onCreate函数里面初始化
        private const val TAG = "xzy"

        // 下面四个是默认tag的函数
        fun i(msg: String?) {
            if (isDebug) Log.i(TAG, msg!!)
        }

        fun d(msg: String?) {
            if (isDebug) Log.d(TAG, msg!!)
        }

        fun e(msg: String?) {
            if (isDebug) Log.e(TAG, msg!!)
        }

        fun v(msg: String?) {
            if (isDebug) Log.v(TAG, msg!!)
        }

        // 下面是传入自定义tag的函数
        fun i(tag: String?, msg: String?) {
            if (isDebug) Log.i(tag, msg!!)
        }

        fun d(tag: String?, msg: String?) {
            if (isDebug) Log.i(tag, msg!!)
        }

        fun e(tag: String?, msg: String?) {
            if (isDebug) Log.i(tag, msg!!)
        }

        fun v(tag: String?, msg: String?) {
            if (isDebug) Log.i(tag, msg!!)
        }
    }

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }
}