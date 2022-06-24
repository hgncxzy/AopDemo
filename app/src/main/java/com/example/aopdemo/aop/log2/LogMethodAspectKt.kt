package com.example.aopdemo.aop.log2

import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Around
import kotlin.Throws
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.json.JSONObject
import org.json.JSONArray
import java.lang.Exception

/**
 * description: 定义日志打印切面 -kt版本
 */
@Aspect
class LogMethodAspectKt {
    @Pointcut("execution(@com.example.aopdemo.aop.log.LogMethod * *(..))")
    fun onLogMethod() {
    }

    @Around("onLogMethod()&& @annotation(logMethod)")
    @Throws(Throwable::class)
    fun logMethod(joinPoint: ProceedingJoinPoint, logMethod: LogMethodKt?): Any {
        var printStr = ""
        if (logMethod != null) {
            val methodSignature = joinPoint.signature as MethodSignature
            val objects = joinPoint.args
            if (logMethod.before) {
                printStr = if (objects == null) {
                    "REQ: Method = " + methodSignature.name + ", Desc = " + logMethod.desc + ", Parameters = null"
                } else {
                    "REQ: Method = " + methodSignature.name + ", Desc = " + logMethod.desc + ", Parameters = " + objects.toString()
                }
                LogPrintUtilsKt.d(printStr)
            }
            val funName = methodSignature.name
            val obj = joinPoint.proceed()
            if (logMethod.after) {
                val type = methodSignature.returnType.toString()
                printStr = if ("void".equals(type, ignoreCase = true)) {
                    "RSP: Method = " + funName + ", Desc = " + logMethod.desc + ", response = Void"
                } else {
                    "RSP: Method = " + funName + ", Desc = " + logMethod.desc + ", response = " + obj
                }
                LogPrintUtilsKt.d(printStr)
            }
            return obj
        }
        return joinPoint.proceed()
    }

    @AfterThrowing(value = "onLogMethod()", throwing = "ex")
    fun afterThrowing(ex: Throwable) {
        LogPrintUtilsKt.e("Throw Exception:$ex")
        LogPrintUtilsKt.e("" + ex.stackTrace[0])
    }

    /**
     * 格式化输出 json 内容
     *
     * @param jsonStr json 字符串
     * @return 格式化后的 json 字符串
     */
    private fun formatDataFromJson(jsonStr: String): String {
        try {
            if (jsonStr.startsWith("{")) {
                val jsonObject = JSONObject(jsonStr)
                return jsonObject.toString(4)
            } else if (jsonStr.startsWith("[")) {
                val jsonArray = JSONArray(jsonStr)
                return jsonArray.toString(4)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return jsonStr
    }
}