package com.example.aopdemo.aop.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * description: 定义日志打印切面
 */
@SuppressWarnings("ALL")
@Aspect
public class LogMethodAspect {

    @Pointcut("execution(@com.example.aopdemo.aop.log.LogMethod * *(..))")
    public void onLogMethod() {
    }

    @Around("onLogMethod()&& @annotation(logMethod)")
    public Object logMethod(ProceedingJoinPoint joinPoint, LogMethod logMethod) throws Throwable {
        String printStr = "";
        if (logMethod != null) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Object[] objects = joinPoint.getArgs();
            if (logMethod.before()) {
                if (objects == null) {
                    printStr = "REQ: Method = " + methodSignature.getName() + ", Desc = " + logMethod.desc() + ", Parameters = null";
                } else {
                    printStr = "REQ: Method = " + methodSignature.getName() + ", Desc = " + logMethod.desc() + ", Parameters = " + objects.toString();
                }
                LogPrintUtils.d(printStr);
            }
            String funName = methodSignature.getName();
            Object obj = joinPoint.proceed();
            if (logMethod.after()) {
                String type = methodSignature.getReturnType().toString();
                if ("void".equalsIgnoreCase(type)) {
                    printStr = "RSP: Method = " + funName + ", Desc = " + logMethod.desc() + ", response = Void";
                } else {
                    printStr = "RSP: Method = " + funName + ", Desc = " + logMethod.desc() + ", response = " + obj;
                }
                LogPrintUtils.d(printStr);
            }
            return obj;
        }
        return joinPoint.proceed();
    }

    @AfterThrowing(value = "onLogMethod()", throwing = "ex")
    public void afterThrowing(Throwable ex) {
        LogPrintUtils.e("Throw Exception:" + ex);
        LogPrintUtils.e("" + ex.getStackTrace()[0]);
    }

    /**
     * 格式化输出 json 内容
     *
     * @param jsonStr json 字符串
     * @return 格式化后的 json 字符串
     */
    private String formatDataFromJson(String jsonStr) {
        try {
            if (jsonStr.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(jsonStr);
                return jsonObject.toString(4);
            } else if (jsonStr.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(jsonStr);
                return jsonArray.toString(4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}