package com.casestudycheckerbackend.service.email;

import javax.servlet.http.HttpServletRequest;

public class Utiliy {
    public static String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(),"");
    }
}
