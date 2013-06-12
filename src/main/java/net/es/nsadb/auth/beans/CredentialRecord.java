package net.es.nsadb.auth.beans;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class CredentialRecord {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private CredentialType type;
    private String credential;

/*    @ManyToOne(targetEntity = AuthRecord.class)
    private AuthRecord authRecord;

    public AuthRecord getAuthRecord() {
        return authRecord;
    }

    public void setAuthRecord(AuthRecord authRecord) {
        this.authRecord = authRecord;
    }
    */
    @XmlElement(name = "id")
    public Long getId() {
        return id;
    }


    @XmlElement(name = "type")
    public CredentialType getType() {
        return type;
    }

    @XmlElement(name = "credential")
    public String getCredential() {
        return credential;
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setType(CredentialType type) {
        this.type = type;
    }
    public void setCredential(String credential) {
        this.credential = credential;
    }
}
