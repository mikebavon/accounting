package com.bavon.event;

import com.bavon.app.model.*;
import com.bavon.database.MysqlDatabase;
import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class AppInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("*************** Initializing database *************");

        try {
            Connection connection = MysqlDatabase.getInstance().getConnection();

            List<Class<?>> entities = new ArrayList<>();
            entities.add(User.class);
            entities.add(Journal.class);
            entities.add(Account.class);
            entities.add(Customer.class);
            entities.add(Invoice.class);
            entities.add(Vendor.class);

            for (Class<?> clazz : entities) {
                if (!clazz.isAnnotationPresent(DbTable.class))
                    continue;

                DbTable dbTable = clazz.getAnnotation(DbTable.class);

                StringBuilder sqlBuilder = new StringBuilder();

                sqlBuilder.append("create table if not exists ").append(dbTable.name()).append("(");
                for (Field field : clazz.getDeclaredFields()) {
                    if (!field.isAnnotationPresent(DbTableColumn.class))
                        continue;

                    DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                    sqlBuilder.append(dbTableColumn.name()).append(" ").append(dbTableColumn.definition()).append(",");
                }

                sqlBuilder.append(")");

                connection.prepareStatement(sqlBuilder.toString().replace(",)", ")")).executeUpdate();

            }



            //auto create databse -- if does not exist
            //auto create create tables; if does not exist

            //create custom annotation.. use java reflection to create table....


        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        try {
            MysqlDatabase database = MysqlDatabase.getInstance();

            if (database.getConnection() != null){
                database.getConnection().close();
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

}
