package Data;

import Db.QueryGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectProperty {

    public DbConnectProperty(String dbServer, int dbPort, String dbName, String dbUser, String dbPassword, String dbSchema) throws Exception {
        if (dbServer == null || dbServer.isEmpty()
                || dbPort == 0
                || dbName == null || dbName.isEmpty()) throw new Exception("DbProperty is empty");

        _dbServer = dbServer;
        _dbPort = dbPort;
        _dbName = dbName;
        _dbUser = dbUser;
        _dbPassword = dbPassword;
        _dbSchema = dbSchema;

        _url = String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", _dbServer, _dbPort, _dbName, _dbUser, _dbPassword);
    }

    //region PrivateField
    private final String _dbServer;
    private final int _dbPort;
    private final String _dbName;
    private final String _dbUser;
    private final String _dbPassword;
    private final String _dbSchema;
    private String _url;
    //endregion PrivateField

    //region PublicMethod

    public Connection GetConnection() throws SQLException {
        return DriverManager.getConnection(_url);
    }

    public QueryGenerator GetQueryGenerator() throws Exception {
        return new QueryGenerator(_dbSchema);
    }

    //endregion PublicMethod
}