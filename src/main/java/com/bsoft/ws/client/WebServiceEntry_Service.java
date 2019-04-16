
package com.bsoft.ws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

import org.apache.commons.lang3.StringUtils;

import com.bsoft.common.util.PropertyUtil;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "webServiceEntry", targetNamespace = "http://ws.bsoft.com/", wsdlLocation = "http://localhost:8080/DQM/services/webServiceEntry?wsdl")
public class WebServiceEntry_Service
    extends Service
{

    private final static URL WEBSERVICEENTRY_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICEENTRY_EXCEPTION;
    private final static QName WEBSERVICEENTRY_QNAME = new QName("http://ws.bsoft.com/", "webServiceEntry");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
        	String webServiceEntryUrl = PropertyUtil.getPropertyValue("sys", "webServiceEntryUrl");
        	if(StringUtils.isEmpty(webServiceEntryUrl)){
        		throw new WebServiceException("未配置webServiceEntryUrl！");
        	}
        	url = new URL(webServiceEntryUrl);
            //url = new URL("http://localhost:8080/DQM/services/webServiceEntry?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
            e.printStackTrace();
        }
        WEBSERVICEENTRY_WSDL_LOCATION = url;
        WEBSERVICEENTRY_EXCEPTION = e;
    }

    public WebServiceEntry_Service() {
        super(__getWsdlLocation(), WEBSERVICEENTRY_QNAME);
    }

    public WebServiceEntry_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), WEBSERVICEENTRY_QNAME, features);
    }

    public WebServiceEntry_Service(URL wsdlLocation) {
        super(wsdlLocation, WEBSERVICEENTRY_QNAME);
    }

    public WebServiceEntry_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WEBSERVICEENTRY_QNAME, features);
    }

    public WebServiceEntry_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceEntry_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebServiceEntry
     */
    @WebEndpoint(name = "webServiceEntryPort")
    public WebServiceEntry getWebServiceEntryPort() {
        return super.getPort(new QName("http://ws.bsoft.com/", "webServiceEntryPort"), WebServiceEntry.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceEntry
     */
    @WebEndpoint(name = "webServiceEntryPort")
    public WebServiceEntry getWebServiceEntryPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.bsoft.com/", "webServiceEntryPort"), WebServiceEntry.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICEENTRY_EXCEPTION!= null) {
            throw WEBSERVICEENTRY_EXCEPTION;
        }
        return WEBSERVICEENTRY_WSDL_LOCATION;
    }

}
