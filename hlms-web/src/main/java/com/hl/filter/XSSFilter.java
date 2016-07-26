package com.hl.filter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class XSSFilter implements Filter {
 
    // XSS处理Map
    private static Map<String,String> xssMap = new LinkedHashMap<String,String>();
     
    public void init(FilterConfig filterConfig) throws ServletException {
        // 含有脚本： script
        xssMap.put("[s|S][c|C][r|R][i|C][p|P][t|T]", "");
        // 含有脚本 javascript
        xssMap.put("[\\\"\\\'][\\s]*[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']", "\"\"");
        // 含有函数： eval
        xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
        // 含有符号 <
        xssMap.put("<", "&lt;");
        // 含有符号 >
        xssMap.put(">", "&gt;");
        // 含有符号 (
        xssMap.put("\\(", "(");
        // 含有符号 )
        xssMap.put("\\)", ")");
        // 含有符号 '
        xssMap.put("'", "'");
        // 含有符号 "
        xssMap.put("\"", "");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        // 强制类型转换 HttpServletRequest
        HttpServletRequest httpReq = (HttpServletRequest)request;
        // 构造HttpRequestWrapper对象处理XSS
        HttpRequestWrapper httpReqWarp = new HttpRequestWrapper(httpReq,xssMap);
        // 
        chain.doFilter(httpReqWarp, response);
 
    }
 
    public void destroy() {
         
    }
}
