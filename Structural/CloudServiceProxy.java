import java.util.ArrayList;
import java.util.List;

// Creating an Interface for servers
interface Server {
    void handleRequest(String request);
}

//  implementation of server A
class ServerA implements Server {
    @Override
    public void handleRequest(String request) {
        System.out.println("ServerA is processing: " + request);
    }
}

// implementation of server B
class ServerB implements Server {
    @Override
    public void handleRequest(String request) {
        System.out.println("ServerB is processing: " + request);
    }
}

// Proxy to balance requests between servers
class LoadBalancerProxy implements Server {
    private List<Server> serverList;
    private int index;

    public LoadBalancerProxy() {
        serverList = new ArrayList<>();
        serverList.add(new ServerA());
        serverList.add(new ServerB());
        index = 0;
    }

    @Override
    public void handleRequest(String request) {
        // Pick a server based on index
        Server server = serverList.get(index);
        index = (index + 1) % serverList.size();
        
        // Print which server is processing the request
        System.out.println("LoadBalancerProxy: Sending request \"" + request + "\" to a server");
        server.handleRequest(request);
    }
}


public class CloudServiceProxy {
    public static void main(String[] args) {
        Server loadBalancer = new LoadBalancerProxy();
        for (int i = 1; i <= 5; i++) {
            loadBalancer.handleRequest("Request " + i);
        }
    }
}
