package Interfaces;

import RepositoryTerror.Data.TerrorResponse;

public interface IRepositoryTerror {
    TerrorResponse GetResponse() throws Exception;
}