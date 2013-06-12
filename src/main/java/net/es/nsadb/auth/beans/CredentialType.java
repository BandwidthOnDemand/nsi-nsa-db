package net.es.nsadb.auth.beans;

import javax.persistence.Embeddable;

@Embeddable
public enum CredentialType {
    USERNAME,
    PASSWORD,
    TOKEN
}
