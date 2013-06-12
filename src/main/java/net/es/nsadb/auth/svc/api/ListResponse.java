package net.es.nsadb.auth.svc.api;


import net.es.nsadb.auth.beans.AuthRecord;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "listResponse")
public class ListResponse {

    private List<AuthRecord> authRecords;

    @XmlElement(name = "authRecords")
    public List<AuthRecord> getAuthRecords() {
        if (authRecords == null) {
            authRecords = new ArrayList<AuthRecord>();
        }
        return authRecords;
    }

    public void setAuthRecords(List<AuthRecord> authRecords) {
        this.authRecords = authRecords;
    }
}
