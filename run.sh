#!/bin/bash
java -Xmx256m  -Djava.net.preferIPv4Stack=true -jar target/nsi-nsa-db-1.0-SNAPSHOT.one-jar.jar   $*
