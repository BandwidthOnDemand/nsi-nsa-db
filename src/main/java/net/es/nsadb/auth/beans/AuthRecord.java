package net.es.nsadb.auth.beans;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.Long;import java.lang.String;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement(name = "authRecord")
public class AuthRecord  {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String networkId;
    @OneToMany(cascade ={CascadeType.ALL})
    private Set<CredentialRecord> credentialRecordSet;
    private AuthMethod method;



    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }

    @XmlElement(name = "networkId")
    public String getNetworkId() {
        return networkId;
    }


    @XmlElement(name = "credentials")
    public Set<CredentialRecord> getCredentialRecordSet() {
        if (credentialRecordSet == null) {
            credentialRecordSet = new HashSet<CredentialRecord>();

        }
        return credentialRecordSet;
    }

    @XmlElement(name = "method")
    public AuthMethod getMethod() {
        return method;
    }



    public void setMethod(AuthMethod method) {
        this.method = method;
    }


    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }
    public void setCredentialRecordSet(Set<CredentialRecord> credentialRecordSet) {
        this.credentialRecordSet = credentialRecordSet;
    }


    public void setId(Long id) {
        this.id = id;
    }

}
