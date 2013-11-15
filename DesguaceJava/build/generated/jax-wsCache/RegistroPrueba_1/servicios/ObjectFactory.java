
package servicios;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the servicios package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HelloResponse_QNAME = new QName("http://Servicios/", "helloResponse");
    private final static QName _RegistroUser_QNAME = new QName("http://Servicios/", "RegistroUser");
    private final static QName _RegistroUserResponse_QNAME = new QName("http://Servicios/", "RegistroUserResponse");
    private final static QName _Hello_QNAME = new QName("http://Servicios/", "hello");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: servicios
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link RegistroUserResponse }
     * 
     */
    public RegistroUserResponse createRegistroUserResponse() {
        return new RegistroUserResponse();
    }

    /**
     * Create an instance of {@link RegistroUser }
     * 
     */
    public RegistroUser createRegistroUser() {
        return new RegistroUser();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicios/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicios/", name = "RegistroUser")
    public JAXBElement<RegistroUser> createRegistroUser(RegistroUser value) {
        return new JAXBElement<RegistroUser>(_RegistroUser_QNAME, RegistroUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistroUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicios/", name = "RegistroUserResponse")
    public JAXBElement<RegistroUserResponse> createRegistroUserResponse(RegistroUserResponse value) {
        return new JAXBElement<RegistroUserResponse>(_RegistroUserResponse_QNAME, RegistroUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://Servicios/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

}
