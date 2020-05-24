package com.example.annotation_reflect.reflect.inject.Retrofit;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/6 21:39
 * pkn    : com.example.annotation_reflect.reflect.inject.Retrofit
 * desc   :
 *
 * Retrofit:通过注解,反射,动态代理实现网络接口的访问
 *
 * 1.创建动态网络接口的接口代理对象
 * 2.在动态代理对象的InvocationHandler()的invoke()中处理接口中的函数:// 通过注解和反射
 *      1.处理网络请求方法的注解信息(地址,请求方式...)
 *      2.处理网络请求的参数(分为get参数和post参数,get设置到URL上,post设置为body)
 *
 * 3.将获取到的信息设置到Okhttp上,最后调用OkHttpClient来调用对应方式的网络接口
 */
public class WeatherRetrofit {

    /**
     * 接口调用的缓冲
     */
    final Map<Method, ServiceMethod> serviceMethodCache = new ConcurrentHashMap<>();
    HttpUrl baseUrl;
    Call.Factory callFactory;

    WeatherRetrofit(Call.Factory callFactory, HttpUrl baseUrl) {
        this.callFactory = callFactory;
        this.baseUrl = baseUrl;
    }

    public <T> T create(Class<T> service) {
        // 通过动态代理实现将接口中的函数调用传递到InvocationHandler中的invoke()中来实现
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //解析这个method 上所有的注解信息
                ServiceMethod serviceMethod = loadServiceMethod(method);
                //args:
                return serviceMethod.invoke(args);
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        //先不上锁，避免synchronized的性能损失
        ServiceMethod result = serviceMethodCache.get(method);
        if (result != null) return result;
        //多线程下，避免重复解析,
        synchronized (serviceMethodCache) {
            result = serviceMethodCache.get(method);
            if (result == null) {
                result = new ServiceMethod.Builder(this, method).build();
                serviceMethodCache.put(method, result);
            }
        }
        return result;
    }

    /**
     * 构建者模式，将一个复杂对象的构建和它的表示分离，可以使使用者不必知道内部组成的细节。
     */
    public static final class Builder {

        // baseurl,okhttpclient

        private HttpUrl baseUrl;
        private okhttp3.Call.Factory callFactory;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = HttpUrl.get(baseUrl);
            return this;
        }

        public Builder callFactory(okhttp3.Call.Factory factory) {
            this.callFactory = factory;
            return this;
        }

        public WeatherRetrofit build() {
            if (baseUrl == null) {
                throw new IllegalStateException("Base URL required.");
            }
            okhttp3.Call.Factory callFactory = this.callFactory;
            if (callFactory == null) {
                callFactory = new OkHttpClient();
            }
            return new WeatherRetrofit(callFactory, baseUrl);
        }
    }
}
