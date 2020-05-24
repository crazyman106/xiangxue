package com.example.annotation_reflect.reflect.inject.Retrofit;

/**
 * author : fenzili
 * e-mail : 291924028@qq.com
 * date   : 2020/5/6 21:59
 * pkn    : com.example.annotation_reflect.reflect.inject.Retrofit.retrofit.annotation
 * desc   :
 */
public abstract class ParameterHandler {
    abstract void apply(ServiceMethod serviceMethod, String value);

    static class QueryParameterHandler extends ParameterHandler {
        String key;

        public QueryParameterHandler(String key) {
            this.key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            //serviceMethod: 回调
            serviceMethod.addQueryParameter(key, value);
        }
    }

    static class FiledParameterHandler extends ParameterHandler {
        String key;

        public FiledParameterHandler(String key) {
            this.key = key;
        }

        @Override
        void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addFiledParameter(key, value);
        }
    }
}
