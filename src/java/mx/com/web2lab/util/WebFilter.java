package mx.com.web2lab.util;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class WebFilter implements Filter, Serializable
{
    /**
     * The <code>Log</code> instance for this class
     */
    private Log iObjLog = LogFactory.getLog(WebFilter.class);
    
    public void init(FilterConfig filterConfig)
        throws ServletException
    {

    }

    /**
     * Destroys the filter.
     */
    public void destroy()
    {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
        throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        iObjLog.info("---------------------MidiendoProcesoXXX-----------: ");
        chain.doFilter(req, resp);
        long stopTime = System.currentTimeMillis();
        iObjLog.info("TiempoDeEjecucionDelProcesoXXX = " + 
        		(stopTime - startTime) + 
        		" milliseconds");
    }
}
