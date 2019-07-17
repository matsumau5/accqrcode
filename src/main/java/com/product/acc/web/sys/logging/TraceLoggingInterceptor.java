package com.product.acc.web.sys.logging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TraceLoggingInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(
            TraceLoggingInterceptor.class);

    private static final String OWN_CLASSNAME = TraceLoggingInterceptor.class.getName();

    private static final long DEFAULT_WARN_NANOS = TimeUnit.SECONDS.toNanos(3);

    @AllArgsConstructor
    @Getter
    private enum Attribute {
        START(OWN_CLASSNAME + ".startTime"),
        HANDLING(OWN_CLASSNAME + ".handlingTime");
        private String value;
    }

    private long warnHandlingNanos = DEFAULT_WARN_NANOS;

    /**
     * Logic to output start log
     *
     * @see HandlerInterceptorAdapter#preHandle(HttpServletRequest,
     * HttpServletResponse, Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        if (handler instanceof HandlerMethod && logger.isTraceEnabled()) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            logger.trace("[START CONTROLLER] {}.{}({})",
                    method.getDeclaringClass().getSimpleName(),
                    method.getName(),
                    StringUtils.collectionToCommaDelimitedString(
                            Arrays.asList(handlerMethod.getMethodParameters())));
        }
        if (handler instanceof HandlerMethod) {
            request.setAttribute(Attribute.START.value, System.nanoTime());
        }
        return true;
    }

    /**
     * Logic to output end log.
     *
     *  @see HandlerInterceptorAdapter#postHandle(HttpServletRequest,
     * HttpServletResponse, Object, ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        long handlingTime = System.nanoTime() -
                (long) ObjectUtils.defaultIfNull(request.getAttribute(Attribute.START.value), 0);
        request.removeAttribute(Attribute.START.value);
        request.setAttribute(Attribute.HANDLING.value, handlingTime);
        String formattedHandlingTime = String.format("%1$,3d", handlingTime);

        boolean isWarnHandling = handlingTime > warnHandlingNanos;

        if (!isEnabledLogLevel(isWarnHandling)) {
            return;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method m = handlerMethod.getMethod();
        Object view = null;
        Map<String, Object> model = null;
        if (ObjectUtils.isNotEmpty(modelAndView)) {
            view = ObjectUtils.defaultIfNull(
                    modelAndView.getView(), modelAndView.getViewName());
            model = modelAndView.getModel();
        }
        String concatMethodParams = StringUtils.collectionToCommaDelimitedString(
                Arrays.asList(handlerMethod.getMethodParameters()));
        logger.trace("[END CONTROLLER  ] {}.{}({})-> view={}, model={}",
                m.getDeclaringClass().getSimpleName(),
                m.getName(),
                concatMethodParams,
                view,
                model);

        String traceHandlingMessageFormat = "[HANDLING TIME   ] {}.{}({})-> {} ns";
        String warnHandlingMessageFormat = traceHandlingMessageFormat + " > {}";
        if (isWarnHandling) {
            logger.warn(warnHandlingMessageFormat,
                    m.getDeclaringClass().getSimpleName(),
                    m.getName(),
                    concatMethodParams,
                    formattedHandlingTime,
                    warnHandlingNanos);
        } else {
            logger.trace(traceHandlingMessageFormat,
                    m.getDeclaringClass().getSimpleName(),
                    m.getName(),
                    concatMethodParams,
                    formattedHandlingTime);
        }
    }

    /**
     * check whether warn is enabled if isWarnHandling, or trace is enabled
     *
     * @param isWarnHandling the warning handling time limit is over
     * @return false if isWarnHandling and logger.warn is disabled, or logger.trace is disabled
     */
    private boolean isEnabledLogLevel(boolean isWarnHandling) {
        return isWarnHandling ? logger.isWarnEnabled() : logger.isTraceEnabled();
    }
}