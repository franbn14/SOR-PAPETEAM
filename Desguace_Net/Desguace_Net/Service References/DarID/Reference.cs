﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Este código fue generado por una herramienta.
//     Versión de runtime:4.0.30319.17929
//
//     Los cambios en este archivo podrían causar un comportamiento incorrecto y se perderán si
//     se vuelve a generar el código.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Desguace_Net.DarID {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(Namespace="http://Servicios/", ConfigurationName="DarID.DarIdDesguacebyCif")]
    public interface DarIdDesguacebyCif {
        
        // CODEGEN: Se está generando un contrato de mensaje, ya que el nombre de elemento nif del espacio de nombres  no está marcado para aceptar valores nil.
        [System.ServiceModel.OperationContractAttribute(Action="http://Servicios/DarIdDesguacebyCif/getIdDesRequest", ReplyAction="http://Servicios/DarIdDesguacebyCif/getIdDesResponse")]
        Desguace_Net.DarID.getIdDesResponse getIdDes(Desguace_Net.DarID.getIdDesRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://Servicios/DarIdDesguacebyCif/getIdDesRequest", ReplyAction="http://Servicios/DarIdDesguacebyCif/getIdDesResponse")]
        System.Threading.Tasks.Task<Desguace_Net.DarID.getIdDesResponse> getIdDesAsync(Desguace_Net.DarID.getIdDesRequest request);
        
        // CODEGEN: Se está generando un contrato de mensaje, ya que el nombre de elemento name del espacio de nombres  no está marcado para aceptar valores nil.
        [System.ServiceModel.OperationContractAttribute(Action="http://Servicios/DarIdDesguacebyCif/helloRequest", ReplyAction="http://Servicios/DarIdDesguacebyCif/helloResponse")]
        Desguace_Net.DarID.helloResponse hello(Desguace_Net.DarID.helloRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://Servicios/DarIdDesguacebyCif/helloRequest", ReplyAction="http://Servicios/DarIdDesguacebyCif/helloResponse")]
        System.Threading.Tasks.Task<Desguace_Net.DarID.helloResponse> helloAsync(Desguace_Net.DarID.helloRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class getIdDesRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="getIdDes", Namespace="http://Servicios/", Order=0)]
        public Desguace_Net.DarID.getIdDesRequestBody Body;
        
        public getIdDesRequest() {
        }
        
        public getIdDesRequest(Desguace_Net.DarID.getIdDesRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="")]
    public partial class getIdDesRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(Order=0)]
        public int id;
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=1)]
        public string nif;
        
        public getIdDesRequestBody() {
        }
        
        public getIdDesRequestBody(int id, string nif) {
            this.id = id;
            this.nif = nif;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class getIdDesResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="getIdDesResponse", Namespace="http://Servicios/", Order=0)]
        public Desguace_Net.DarID.getIdDesResponseBody Body;
        
        public getIdDesResponse() {
        }
        
        public getIdDesResponse(Desguace_Net.DarID.getIdDesResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="")]
    public partial class getIdDesResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(Order=0)]
        public int @return;
        
        public getIdDesResponseBody() {
        }
        
        public getIdDesResponseBody(int @return) {
            this.@return = @return;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class helloRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="hello", Namespace="http://Servicios/", Order=0)]
        public Desguace_Net.DarID.helloRequestBody Body;
        
        public helloRequest() {
        }
        
        public helloRequest(Desguace_Net.DarID.helloRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="")]
    public partial class helloRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string name;
        
        public helloRequestBody() {
        }
        
        public helloRequestBody(string name) {
            this.name = name;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class helloResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="helloResponse", Namespace="http://Servicios/", Order=0)]
        public Desguace_Net.DarID.helloResponseBody Body;
        
        public helloResponse() {
        }
        
        public helloResponse(Desguace_Net.DarID.helloResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="")]
    public partial class helloResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string @return;
        
        public helloResponseBody() {
        }
        
        public helloResponseBody(string @return) {
            this.@return = @return;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface DarIdDesguacebyCifChannel : Desguace_Net.DarID.DarIdDesguacebyCif, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class DarIdDesguacebyCifClient : System.ServiceModel.ClientBase<Desguace_Net.DarID.DarIdDesguacebyCif>, Desguace_Net.DarID.DarIdDesguacebyCif {
        
        public DarIdDesguacebyCifClient() {
        }
        
        public DarIdDesguacebyCifClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public DarIdDesguacebyCifClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public DarIdDesguacebyCifClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public DarIdDesguacebyCifClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        Desguace_Net.DarID.getIdDesResponse Desguace_Net.DarID.DarIdDesguacebyCif.getIdDes(Desguace_Net.DarID.getIdDesRequest request) {
            return base.Channel.getIdDes(request);
        }
        
        public int getIdDes(int id, string nif) {
            Desguace_Net.DarID.getIdDesRequest inValue = new Desguace_Net.DarID.getIdDesRequest();
            inValue.Body = new Desguace_Net.DarID.getIdDesRequestBody();
            inValue.Body.id = id;
            inValue.Body.nif = nif;
            Desguace_Net.DarID.getIdDesResponse retVal = ((Desguace_Net.DarID.DarIdDesguacebyCif)(this)).getIdDes(inValue);
            return retVal.Body.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<Desguace_Net.DarID.getIdDesResponse> Desguace_Net.DarID.DarIdDesguacebyCif.getIdDesAsync(Desguace_Net.DarID.getIdDesRequest request) {
            return base.Channel.getIdDesAsync(request);
        }
        
        public System.Threading.Tasks.Task<Desguace_Net.DarID.getIdDesResponse> getIdDesAsync(int id, string nif) {
            Desguace_Net.DarID.getIdDesRequest inValue = new Desguace_Net.DarID.getIdDesRequest();
            inValue.Body = new Desguace_Net.DarID.getIdDesRequestBody();
            inValue.Body.id = id;
            inValue.Body.nif = nif;
            return ((Desguace_Net.DarID.DarIdDesguacebyCif)(this)).getIdDesAsync(inValue);
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        Desguace_Net.DarID.helloResponse Desguace_Net.DarID.DarIdDesguacebyCif.hello(Desguace_Net.DarID.helloRequest request) {
            return base.Channel.hello(request);
        }
        
        public string hello(string name) {
            Desguace_Net.DarID.helloRequest inValue = new Desguace_Net.DarID.helloRequest();
            inValue.Body = new Desguace_Net.DarID.helloRequestBody();
            inValue.Body.name = name;
            Desguace_Net.DarID.helloResponse retVal = ((Desguace_Net.DarID.DarIdDesguacebyCif)(this)).hello(inValue);
            return retVal.Body.@return;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<Desguace_Net.DarID.helloResponse> Desguace_Net.DarID.DarIdDesguacebyCif.helloAsync(Desguace_Net.DarID.helloRequest request) {
            return base.Channel.helloAsync(request);
        }
        
        public System.Threading.Tasks.Task<Desguace_Net.DarID.helloResponse> helloAsync(string name) {
            Desguace_Net.DarID.helloRequest inValue = new Desguace_Net.DarID.helloRequest();
            inValue.Body = new Desguace_Net.DarID.helloRequestBody();
            inValue.Body.name = name;
            return ((Desguace_Net.DarID.DarIdDesguacebyCif)(this)).helloAsync(inValue);
        }
    }
}
