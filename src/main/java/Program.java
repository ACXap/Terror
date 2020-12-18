import Data.Terrorist;
import Db.DataSaveRepositoryDb;
import Interfaces.IRepositoryTerror;
import RepositoryTerror.Data.TerrorResponse;
import RepositoryTerror.FactoryRepository;
import Service.PropertyService;

import java.util.List;

public class Program {
    public static void main(String[] args) throws Exception {
        PropertyService.Initialization();

        IRepositoryTerror rf = new FactoryRepository().GetRepository(args);
        TerrorResponse terrorResponse = rf.GetResponse();

        List<Terrorist> terror = Terrorist.ConvertEntityToTerrorist(terrorResponse.Terror);

        DataSaveRepositoryDb db = new DataSaveRepositoryDb(PropertyService.DbConnectProperty);
        db.DeleteData();
        db.AddTerror(terror);
    }
}