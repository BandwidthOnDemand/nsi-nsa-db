package net.es.nsadb.nsa.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "nsaRecord")
public class NsaRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nsaId;
    private String networkId;
    private String providerUrl;

    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "networkId")
    public String getNetworkId() {
        return networkId;
    }


    @XmlElement(name = "providerUrl")
    public String getProviderUrl() {
        return providerUrl;
    }

    @XmlElement(name = "nsaId")
    public String getNsaId() {
        return nsaId;
    }

    public void setNsaId(String nsaId) {
        this.nsaId = nsaId;
    }

    public void setProviderUrl(String providerUrl) {
        this.providerUrl = providerUrl;
    }
    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
