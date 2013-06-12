package net.es.nsadb;


import net.es.nsadb.auth.svc.AuthServer;
import net.es.nsadb.config.SpringContext;
import net.es.nsadb.config.http.HttpConfig;
import net.es.nsadb.config.http.HttpConfigProvider;
import org.springframework.context.ApplicationContext;

public class Invoker {
    public static void main(String args[]) throws Exception {
        SpringContext sc = SpringContext.getInstance();

        ApplicationContext context = sc.initContext("config/beans.xml");

        HttpConfigProvider htProv = (HttpConfigProvider) context.getBean("httpConfigProvider");


        System.out.print("Loading HTTP config... ");
        HttpConfig aaConf = htProv.getConfig("auth");

        System.out.print("done. Starting HTTP servers...\n");
        AuthServer.getInstance(aaConf);
        System.out.println("HTTP servers started.");

        while (true) {
            Thread.sleep(1);
        }

    }
}
