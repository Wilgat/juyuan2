package com.ruoyi.framework.config.properties;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class PermitAllUrlProperties implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final List<String> urls = new ArrayList<>();

    @Override
    public void afterPropertiesSet() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((info, handlerMethod) -> {
            // Check method-level @Anonymous
            Anonymous methodAnonymous = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Anonymous.class);
            if (methodAnonymous != null) {
                addUrl(info);
                return;
            }

            // Check class-level @Anonymous
            Anonymous classAnonymous = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Anonymous.class);
            if (classAnonymous != null) {
                addUrl(info);
            }
        });
    }

    private void addUrl(RequestMappingInfo info) {
        Set<String> patterns = info.getPatternsCondition() != null 
                ? info.getPatternsCondition().getPatterns() 
                : Set.of();

        for (String pattern : patterns) {
            if (StringUtils.isNotBlank(pattern)) {
                urls.add(pattern);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public List<String> getUrls() {
        return urls;
    }

    // Optional: allow manual configuration via application.yml if needed
    public void setUrls(List<String> urls) {
        if (urls != null) {
            this.urls.addAll(urls);
        }
    }
}