package Db;

import Data.DbConnectProperty;
import Data.Terrorist;
import Interfaces.ISaveDataRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DataSaveRepositoryDb implements ISaveDataRepository {

    public DataSaveRepositoryDb(DbConnectProperty dbConnectProperty) throws Exception {
        _dbConnectProperty = dbConnectProperty;
        _queryGenerator = _dbConnectProperty.GetQueryGenerator();
    }

    //region PrivateField
    protected final QueryGenerator _queryGenerator;
    protected final DbConnectProperty _dbConnectProperty;
    //endregion PrivateField

    @Override
    public void AddTerror(List<Terrorist> terror) throws SQLException {
        Connection con = _dbConnectProperty.GetConnection();

        try {
            con.setAutoCommit(false);

            AddTerror(terror, con);

            con.commit();
        } catch (SQLException sex) {
            con.rollback();
            con.close();
            throw sex;
        } finally {
            con.close();
        }
    }

    @Override
    public void DeleteData() throws SQLException {
        Connection con = _dbConnectProperty.GetConnection();

        try {
            con.setAutoCommit(false);

            String query = _queryGenerator.GetQueryDeleteData();

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.execute();
            }

            con.commit();
        } catch (SQLException sex) {
            con.rollback();
            con.close();
            throw sex;
        } finally {
            con.close();
        }
    }

    private void AddTerror(List<Terrorist> terror, Connection con) throws SQLException {
        String query = _queryGenerator.GetQueryInsertTerror();

        try (PreparedStatement ps = con.prepareStatement(query)) {
            for (Terrorist p : terror) {

                int parameterIndex = 1;
                ps.setString(parameterIndex++, p.Name);
                ps.setInt(parameterIndex++, p.IdNew);
                ps.setInt(parameterIndex++, p.PersonType);
                ps.setInt(parameterIndex++, p.IsTerrorist);
                ps.setString(parameterIndex++, p.Inn);
                ps.setString(parameterIndex++, p.BirthDate);
                ps.setString(parameterIndex++, p.Description);
                ps.setString(parameterIndex++, p.Address);
                ps.setString(parameterIndex++, p.TerroristResolution);
                ps.setString(parameterIndex++, p.BirthPlace);
                ps.setString(parameterIndex, p.Passport);

                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private Object GetSqlDate(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        }

        return null;
    }
}