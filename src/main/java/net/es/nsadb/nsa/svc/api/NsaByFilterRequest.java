package net.es.nsadb.nsa.svc.api;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nsaByFilterRequest")
public class NsaByFilterRequest {
    private String networkId;
    private String nsaId;

    @XmlElement(name = "networkId")
    public String getNetworkId() {
        return networkId;
    }

    @XmlElement(name = "nsaId")
    public String getNsaId() {
        return nsaId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public void setNsaId(String nsaId) {
        this.nsaId = nsaId;
    }
}
