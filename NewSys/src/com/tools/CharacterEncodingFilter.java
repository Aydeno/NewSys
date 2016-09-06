package com.tools;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 字符集FILTER，适用于所有SERVLET容器
 * @author
 * @version 1.0
 */
public class CharacterEncodingFilter implements Filter
{
  /**
   * 字符集名称，缺省为GBK
   */
  protected String encoding = "GBK";

  /**
   * 过滤器配置对象
   */
  protected FilterConfig filterConfig = null;

  /**
   * <p>从web.xml得到过滤器参数值</p>
   */
  public void init(javax.servlet.FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
    this.encoding = filterConfig.getInitParameter("encoding");
  }

  /**
   * <p>把请求编码为web.xml所配置的字符集</p>
   */
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	//对请求进行编码
    request.setCharacterEncoding(encoding);
    //跨域取session
    HttpServletResponse _response = (HttpServletResponse)response;
    _response.addHeader("P3P", "CP=CAO PSA OUR");
   // _response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'"); 
    //_response.setHeader("P3P","CP=\"CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR\""); 
    // 把控制传递给下一个过滤器
    chain.doFilter(request, response);

  }

  /**
   * 清除对象
   */
  public void destroy() {
    this.encoding = null;
    this.filterConfig = null;
  }

}