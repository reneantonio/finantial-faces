/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.rmpestano.finantial.util;

import br.rmpestano.finantial.model.User;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * filter which controls session
 * restrict access to pages only to logged users
 * redirect to login  page if session time out
 * @author rmpestano
 */
public class SessionFilter  implements Filter{
    private String timeoutPage = "login.faces";
    Pattern p = Pattern.compile("resource*");

    @Override
       public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
        if ((request instanceof HttpServletRequest) && (response instanceof HttpServletResponse)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            // is session expire control required for this request?
            if (isSessionControlRequiredForThisResource(httpServletRequest) == -1) {
                // is session invalid?

                if (isSessionInvalid(httpServletRequest,request,response)) {
                    String timeoutUrl = httpServletRequest.getContextPath() + "/" + getTimeoutPage();
                    httpServletResponse.sendRedirect(timeoutUrl);
                    return;

                }
            }

        }
         chain.doFilter(request, response);
    }

    @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        public void destroy() {

        }

        /**
  *
  * session shouldn't be checked for some pages. For example: for timeout page..
  * Since we're redirecting to timeout page from this filter,
  * if we don't disable session control for it, filter will again redirect to it
  * and this will be result with an infinite loop...
  */
 private int isSessionControlRequiredForThisResource(HttpServletRequest httpServletRequest) {
  String requestPath = httpServletRequest.getRequestURI();
  
  if(p.matcher(requestPath).find() ){
      return 666;
  }
  if(httpServletRequest.getContextPath().concat("/").equals(requestPath)){
      return 1;
  }
  int controlRequired =  requestPath.indexOf(getTimeoutPage());

  return controlRequired;
 }

    private boolean isSessionInvalid(HttpServletRequest httpServletRequest,ServletRequest req, ServletResponse res) {
        boolean sessionInValid = (httpServletRequest.getRequestedSessionId() != null) && !httpServletRequest.isRequestedSessionIdValid();
        if(! sessionInValid){//se a sessão for válida testa se usuário tá logado ou é alguem tentando acessar as páginas sem tá logado
            sessionInValid = !isUserLoogedIn(req,res);
        }
        return sessionInValid;
    }


    private boolean isUserLoogedIn(ServletRequest req, ServletResponse res) {
        FacesContextBuilder fcb = new FacesContextBuilder();
        User u = (User) fcb.getFacesContext(req, res).getExternalContext().getSessionMap().get("user");
        return u != null;

    }



    public String getTimeoutPage() {
        return timeoutPage;
    }

    public void setTimeoutPage(String timeoutPage) {
        this.timeoutPage = timeoutPage;
    }

}


