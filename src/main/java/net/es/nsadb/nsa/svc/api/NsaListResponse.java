package net.es.nsadb.nsa.svc.api;


import net.es.nsadb.nsa.beans.NsaRecord;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "nsaListResponse")
public class NsaListResponse {

    private List<NsaRecord> nsaRecords;

    @XmlElement(name = "nsaRecords")
    public List<NsaRecord> getNsaRecords() {
        if (nsaRecords == null) {
            nsaRecords = new ArrayList<NsaRecord>();
        }
        return nsaRecords;
    }

    public void setNsaRecords(List<NsaRecord> nsaRecords) {
        this.nsaRecords = nsaRecords;
    }
}
