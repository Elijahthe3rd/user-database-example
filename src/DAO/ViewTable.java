package DAO;

import Model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public interface ViewTable {
    void selectAll(String userTableName,String addressTableName) throws SQLException;
}
