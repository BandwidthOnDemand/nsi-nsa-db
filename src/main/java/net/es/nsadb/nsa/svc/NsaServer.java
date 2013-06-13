package net.es.nsadb.nsa.svc;


import net.es.nsadb.auth.svc.AuthServiceImpl;
import net.es.nsadb.config.http.HttpConfig;
import net.es.nsadb.nsa.NsaServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;


public class NsaServer {
    private org.apache.cxf.endpoint.Server server;

    private static NsaServer instance;

    public static NsaServer getInstance(){
        return instance;
    }
    public static NsaServer makeServer(HttpConfig conf) throws Exception {
        if (instance == null) {
            instance = new NsaServer(conf.url, conf.bus);
        }
        return instance;
    }

    private NsaServer(String url, String configFile) throws Exception {
        SpringBusFactory factory = new SpringBusFactory();
        Bus bus = factory.createBus(configFile);
        BusFactory.setDefaultBus(bus);

        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(NsaServiceImpl.class);
        sf.setResourceProvider(NsaServiceImpl.class,
                new SingletonResourceProvider(new NsaServiceImpl()));
        sf.setAddress(url);
        server = sf.create();
    }

    public void stop() {
        server.stop();
        server.destroy();

    }


}