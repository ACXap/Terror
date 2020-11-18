package Interfaces;

import Data.Terrorist;

import java.sql.SQLException;
import java.util.List;

public interface ISaveDataRepository {

    void AddTerror(List<Terrorist> persons) throws SQLException;
}