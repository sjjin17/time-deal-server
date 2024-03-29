package com.timedeal_server.timedeal.global.auth;

import com.timedeal_server.timedeal.domain.user.domain.Role;
import com.timedeal_server.timedeal.domain.user.domain.User;
import com.timedeal_server.timedeal.global.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasLoginAnnotaion = parameter.hasParameterAnnotation(Auth.class);
        boolean isUserType = User.class.isAssignableFrom(parameter.getParameterType());
        return hasLoginAnnotaion && isUserType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        Auth auth = parameter.getParameterAnnotation(Auth.class);

        if (session == null) {
            return null;
        }
        User user = (User) session.getAttribute("loginUser");
        String role = auth.role().toString();

        if ("ADMIN".equals(role)) {
            if (role.equals(user.getRole().toString())) {
                return user;
            } else {
                throw new CustomException("권한이 없습니다.");
            }
        }
        return user;

    }
}
