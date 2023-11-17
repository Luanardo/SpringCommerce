package com.springcommerce.midterm.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        // Trích xuất thông tin người dùng từ đối tượng Authentication
//        CustomerDetails customerDetails = (CustomerDetails) authentication.getPrincipal();
//
//        // Lưu thông tin người dùng vào session
//        HttpSession session = request.getSession();
//        session.setAttribute("email", customerDetails.getUsername());

        // Chuyển hướng người dùng về trang trước đó nếu có
        String targetUrl = determineTargetUrl(request, response, authentication);
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        // Lấy SavedRequest từ session
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);

        // Nếu có SavedRequest và không phải là trang đăng nhập, chuyển hướng về trang trước đó
        if (savedRequest != null && !("/login").equals(savedRequest.getRedirectUrl())) {
            return savedRequest.getRedirectUrl();
        } else {
            // Nếu không có SavedRequest hoặc là trang đăng nhập, chuyển hướng về URL mặc định
            return "/";
        }
    }
}
