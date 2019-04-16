
package com.bsoft.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Action;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WebServiceEntry", targetNamespace = "http://ws.bsoft.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WebServiceEntry {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://ws.bsoft.com/WebServiceEntry/invokeRequest", output = "http://ws.bsoft.com/WebServiceEntry/invokeResponse")
    public String invoke(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0);

}
