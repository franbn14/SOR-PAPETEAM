/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package desguacejava;

import java.util.List;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessList;
import org.uddi.api_v3.FindBusiness;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.GetServiceDetail;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.ServiceDetail;
import org.uddi.api_v3.ServiceInfo;

/**
 *
 * @author Gustavo
 */
public class ServiceHandler {
    
    public static String getURL(String service) {

        GetAuthToken getAuthToken = new GetAuthToken();
        getAuthToken.setUserID("client_java");
        getAuthToken.setCred("client_java");
        AuthToken AuthToken = getAuthToken(getAuthToken);

        FindBusiness fb = new FindBusiness();
        fb.setAuthInfo(AuthToken.getAuthInfo());
        Name businessName = new Name();
        businessName.setValue("Gestor");
        fb.getName().add(businessName);

        BusinessInfo bInf = findBusiness(fb).getBusinessInfos().getBusinessInfo().get(0);
        List<ServiceInfo> sInf = bInf.getServiceInfos().getServiceInfo();
        
        for (ServiceInfo si : sInf) {
            if (service.equals(si.getName().get(0).getValue())) {
                GetServiceDetail gsd = new GetServiceDetail();
                gsd.setAuthInfo(AuthToken.getAuthInfo());
                gsd.getServiceKey().add(si.getServiceKey());
                ServiceDetail sd = getServiceDetail(gsd);
                return sd.getBusinessService().get(0).getBindingTemplates().getBindingTemplate().get(0).getAccessPoint().getValue();
            }
        }
        return "";
    }

    private static BusinessList findBusiness(org.uddi.api_v3.FindBusiness body) {
        org.uddi.v3_service.UDDIInquiryService service = new org.uddi.v3_service.UDDIInquiryService();
        org.uddi.v3_service.UDDIInquiryPortType port = service.getUDDIInquiryImplPort();
        return port.findBusiness(body);
    }

    private static ServiceDetail getServiceDetail(org.uddi.api_v3.GetServiceDetail body) {
        org.uddi.v3_service.UDDIInquiryService service = new org.uddi.v3_service.UDDIInquiryService();
        org.uddi.v3_service.UDDIInquiryPortType port = service.getUDDIInquiryImplPort();
        return port.getServiceDetail(body);
    }

    private static AuthToken getAuthToken(org.uddi.api_v3.GetAuthToken body) {
        org.uddi.v3_service.UDDISecurityService service = new org.uddi.v3_service.UDDISecurityService();
        org.uddi.v3_service.UDDISecurityPortType port = service.getUDDISecurityImplPort();
        return port.getAuthToken(body);
    }
    
    
}
