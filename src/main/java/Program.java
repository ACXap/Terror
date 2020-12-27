import Data.Terrorist;
import Db.DataSaveRepositoryDb;
import Interfaces.IRepositoryTerror;
import RepositoryTerror.Data.TerrorResponse;
import RepositoryTerror.FactoryRepository;
import Service.PropertyService;

import java.text.SimpleDateFormat;
import java.util.List;

public class Program {
    public static void main(String[] args) throws Exception {
        PropertyService.Initialization();
        Terrorist.SEPARATOR = PropertyService.DbSeparator;

        IRepositoryTerror rf = new FactoryRepository().GetRepository(args);
        TerrorResponse terrorResponse = rf.GetResponse();

        List<Terrorist> terror = Terrorist.ConvertEntityToTerrorist(terrorResponse.Terror);

        for(Terrorist t: terror){
            t.IdList = terrorResponse.Id;
            t.NumList = terrorResponse.Num;
            t.DateList = new SimpleDateFormat("dd.MM.yyyy").parse(terrorResponse.Date);
        }

        DataSaveRepositoryDb db = new DataSaveRepositoryDb(PropertyService.DbConnectProperty);
        db.DeleteData();
        db.AddTerror(terror);
    }
}