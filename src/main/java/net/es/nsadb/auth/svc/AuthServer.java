package net.es.nsadb.auth.svc;


import net.es.nsadb.config.http.HttpConfig;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import java.lang.Exception;
import java.lang.String;


public class AuthServer {
    private org.apache.cxf.endpoint.Server server;

    private static AuthServer instance;

    public static AuthServer getInstance(String url, String configFile) throws Exception {
        if (instance == null) {
            instance = new AuthServer(url, configFile);
        }
        return instance;
    }
    public static AuthServer getInstance(HttpConfig conf) throws Exception {
        if (instance == null) {
            instance = new AuthServer(conf.url, conf.bus);
        }
        return instance;
    }

    private AuthServer(String url, String configFile) throws Exception {
        SpringBusFactory factory = new SpringBusFactory();
        Bus bus = factory.createBus(configFile);
        BusFactory.setDefaultBus(bus);

        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(AuthServiceImpl.class);
        sf.setResourceProvider(AuthServiceImpl.class,
                new SingletonResourceProvider(new AuthServiceImpl()));
        sf.setAddress(url);
        server = sf.create();
    }

    public void stop() {
        server.stop();
        server.destroy();

    }


}