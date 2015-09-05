package com.tomato.web.utils;

import com.tomato.web.model.WechatObject;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.AbstractNamedValueMethodArgumentResolver;
import org.springframework.web.method.support.UriComponentsContributor;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * A new argument Resolver to binding the WechatObject in method argument.
 * When user try to get the wechatobject to process wechat related operation, they have two things to do:
 *
 * 1. Add the path to wechat interceptor
 * 2. Add an new method argument which is in type WechatObject
 *
 * Then they can get and use the wechatObject inside the method.
 *
 *
 * Created by wangronghua on 15/9/4.
 */
public class WechatObjectParamMethodArgumentResolver extends AbstractNamedValueMethodArgumentResolver
                                        implements UriComponentsContributor {


    @Override
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        return new NamedValueInfo(parameter.getParameterName(), false, null);
    }

    @Override
    protected Object resolveName(String name, MethodParameter parameter, NativeWebRequest request) throws Exception {
        HttpServletRequest servletRequest = request.getNativeRequest(HttpServletRequest.class);

        Object arg = servletRequest.getAttribute(Constants.WECHAT_OBJECT_ATTR_NAME);

        return arg;
    }

    @Override
    protected void handleMissingValue(String name, MethodParameter parameter) throws ServletException {
        throw new MissingServletRequestParameterException(name, parameter.getParameterType().getSimpleName());
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        if (WechatObject.class.isAssignableFrom(paramType)) {
            String paramName = parameter.getParameterName();

            return StringUtils.hasText(paramName);
        }
        else {
            return false;
        }
    }

    @Override
    public void contributeMethodArgument(MethodParameter parameter, Object value, UriComponentsBuilder builder, Map<String, Object> uriVariables, ConversionService conversionService) {

        String name = parameter.getParameterName();
        value = builder.query(name);

        uriVariables.put(name, value);
    }
}
