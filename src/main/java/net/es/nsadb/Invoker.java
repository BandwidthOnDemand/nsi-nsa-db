package net.es.nsadb;


import net.es.nsadb.auth.svc.AuthServer;
import net.es.nsadb.config.SpringContext;
import net.es.nsadb.config.http.HttpConfig;
import net.es.nsadb.config.http.HttpConfigProvider;
import org.springframework.context.ApplicationContext;

public class Invoker {
    private static boolean keepRunning = true;

    public static boolean isKeepRunning() {
        return keepRunning;
    }

    public static void setKeepRunning(boolean keepRunning) {
        Invoker.keepRunning = keepRunning;
    }

    public static void main(String args[]) throws Exception {
        System.out.print("Initializing Spring... ");
        SpringContext sc = SpringContext.getInstance();
        ApplicationContext context = sc.initContext("config/beans.xml");



        System.out.print(" Spring initialized. \nLoading HTTP config... ");
        HttpConfigProvider htProv = (HttpConfigProvider) context.getBean("httpConfigProvider");
        HttpConfig aaConf = htProv.getConfig("auth");

        System.out.print("done. \nStarting HTTP servers...\n");
        AuthServer.makeServer(aaConf);
        System.out.println("HTTP servers started.");

        Runtime.getRuntime().addShutdownHook(
                new Thread() {
                    public void run() {
                        System.out.println("Shutting down..");
                        PersistenceHolder.getInstance().getEntityManager().close();
                        AuthServer.getInstance().stop();
                        System.out.println("Shutdown complete.");
                        Invoker.setKeepRunning(false);
                    }
                }
        );

        while (keepRunning) {
            Thread.sleep(1);
        }

    }
}
