package Data;

import Db.QueryGenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectProperty {

    public DbConnectProperty(String dbType, String dbServer, int dbPort, String dbName, String dbUser, String dbPassword, String dbSchema) throws Exception {
        if (dbType == null || dbType.isEmpty()
                || dbServer == null || dbServer.isEmpty()
                || dbPort == 0
                || dbName == null || dbName.isEmpty()) throw new Exception("DbProperty is empty");

        _dbType = dbType.toLowerCase();
        _dbServer = dbServer;
        _dbPort = dbPort;
        _dbName = dbName;
        _dbUser = dbUser;
        _dbPassword = dbPassword;
        _dbSchema = dbSchema;
    }

    //region PrivateField
    private final String _dbType;
    private final String _dbServer;
    private final int _dbPort;
    private final String _dbName;
    private final String _dbUser;
    private final String _dbPassword;
    private final String _dbSchema;
    private String _url = "";
    //endregion PrivateField

    //region PublicMethod

    public Connection GetConnection() throws SQLException {
        CreateUrlConnect();

        return DriverManager.getConnection(_url);
    }

    public QueryGenerator GetQueryGenerator() throws Exception {
        return new QueryGenerator(_dbSchema);
    }

    //endregion PublicMethod

    //region PrivateMethod
    private void CreateUrlConnect() throws SQLException {
        if (_url.isEmpty()) {
            if (_dbType.equals("sql")) {
                if (_dbUser.isEmpty())
                    _url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;integratedSecurity=true", _dbServer, _dbPort, _dbName);
                else
                    _url = String.format("jdbc:sqlserver://%s:%s;databaseName=%s;user=%s;password=%s", _dbServer, _dbPort, _dbName, _dbUser, _dbPassword);

            } else if (_dbType.equals("postgresql")) {
                _url = String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s", _dbServer, _dbPort, _dbName, _dbUser, _dbPassword);
            } else throw new SQLException("DbProperty is bad");
        }
    }
    //endregion PrivateMethod
}