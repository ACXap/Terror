package Data;

public class InternetConnectProperty {
    public final String ProxyServer;
    public final int ProxyPort;
    public final boolean IsSet;


    public InternetConnectProperty(String proxyServer, int proxyPort) throws Exception {
        if (proxyServer != null && !proxyServer.isEmpty() && proxyPort == 0) throw new Exception("Port is empty");
        if ((proxyServer == null || proxyServer.isEmpty()) && proxyPort != 0) throw new Exception("ProxyServer is empty");

        ProxyServer = proxyServer;
        ProxyPort = proxyPort;
        IsSet = proxyServer != null && !proxyServer.isEmpty();
    }
}