package net.es.nsadb.auth.svc.api;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authByFilterRequest")
public class AuthByFilterRequest {
    private String networkId;

    @XmlElement(name = "networkId")
    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }
}
