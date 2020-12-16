import Data.Terrorist;
import Db.DataSaveRepositoryDb;
import RepositoryTerror.Data.TerrorResponse;
import RepositoryTerror.RepositoryFile;
import Service.PropertyService;

import java.util.List;

public class Program {
    public static void main(String[] args) throws Exception {
        PropertyService.Initialization();

        RepositoryFile rf = new RepositoryFile(PropertyService.PathTempFile);
        TerrorResponse terrorResponse = rf.GetResponse("temp/" + "terror.xml");


        List<Terrorist> terror = Terrorist.ConvertEntityToTerrorist(terrorResponse.Terror);

        DataSaveRepositoryDb db = new DataSaveRepositoryDb(PropertyService.DbConnectProperty);
        db.DeleteData();
        db.AddTerror(terror);
    }
}