using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Desguace_Net.Security;
using Desguace_Net.Inquiry;

namespace Desguace_Net
{
    class Uddi
    {
        public static string DarUrlWsdl(String nameService) 
        {
            UDDI_Inquiry_PortTypeClient l1 = new UDDI_Inquiry_PortTypeClient();
            Inquiry.find_business find = new Inquiry.find_business();
            find.name = new Inquiry.name[1];
            find.name[0] = new Inquiry.name();
            find.name[0].Value = "Gestor";

          
                Inquiry.businessList list = l1.find_business(find);
                String serviceKey = "";
                string url = "";
                if (list != null)
                {
                    for (int i = 0; i < list.businessInfos[0].serviceInfos.Length; i++)
                    {

                        if (list.businessInfos[0].serviceInfos[i].name[0].Value == nameService)
                        {
                            serviceKey = list.businessInfos[0].serviceInfos[i].serviceKey;
                            break;
                        }
                    }
                    Inquiry.get_serviceDetail sd = new Inquiry.get_serviceDetail();
                    sd.serviceKey = new String[1];
                    sd.serviceKey[0] = serviceKey;
                    Inquiry.serviceDetail n = l1.get_serviceDetail(sd);
                    url = n.businessService[0].bindingTemplates[0].accessPoint.Value;
                }
                

                return url;
            
            
                
           
            
           
        }
    }
}
