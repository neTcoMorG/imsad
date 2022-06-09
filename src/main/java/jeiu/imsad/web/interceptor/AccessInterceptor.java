package jeiu.imsad.web.interceptor;

import jeiu.imsad.domain.ADMIN_CONST;
import jeiu.imsad.domain.partner.Partner;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Partner accessPartner = (Partner) request.getSession(false).getAttribute("LOGIN");
        if (!accessPartner.getLoginId().equals(ADMIN_CONST.ADMIN_ID)) {
            response.sendError(403);
            return false;
        }
        return true;
    }
}
