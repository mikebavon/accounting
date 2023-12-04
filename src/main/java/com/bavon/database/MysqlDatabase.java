package com.bavon.database;

import com.bavon.app.model.*;
import com.bavon.database.helper.DbTable;
import com.bavon.database.helper.DbTableColumn;
import com.bavon.database.helper.DbTableId;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Singleton
@Startup()
@DependsOn({"CheckOsBean","CheckCountryBean","CheckLicenseBean"})
public class MysqlDatabase implements Serializable {

    @Resource(lookup = "java:jboss/datasources/accounting")
    private DataSource dataSource;

    private Connection connection;


    @PostConstruct
    private void init() throws SQLException, NamingException {
        connection = dataSource.getConnection();

        System.out.println("Executed. on start up!!");

        this.updateSchema();
    }


    private void updateSchema(){
        System.out.println("*************** updating schema database *************");

        try {

            List<Class<?>> entities = new ArrayList<>();
            entities.add(User.class);
            entities.add(Journal.class);
            entities.add(Account.class);
            entities.add(Customer.class);
            entities.add(Invoice.class);
            entities.add(Vendor.class);
            entities.add(Item.class);
            entities.add(Payment.class);
            entities.add(AuditLog.class);

            for (Class<?> clazz : entities) {
                if (!clazz.isAnnotationPresent(DbTable.class))
                    continue;

                DbTable dbTable = clazz.getAnnotation(DbTable.class);

                StringBuilder sqlBuilder = new StringBuilder();

                sqlBuilder.append("create table if not exists ").append(dbTable.name()).append("(");

                List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

                for (Field field : fields) {
                    if (!field.isAnnotationPresent(DbTableColumn.class))
                        continue;

                    DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                    sqlBuilder.append(dbTableColumn.name()).append(" ")
                        .append(dbTableColumn.definition())
                        .append(field.isAnnotationPresent(DbTableId.class)?" NOT NULL AUTO_INCREMENT PRIMARY KEY" : "")
                        .append(",");
                }

                sqlBuilder.append(")");

                String tableCreationSql = sqlBuilder.toString().replace(",)", ")");
                System.out.println("Creating table: " + tableCreationSql);
                connection.prepareStatement(tableCreationSql).executeUpdate();

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    public void saveOrUpdate(Object entity) {

        try {

            Class<?> clazz = entity.getClass();
            if (!clazz.isAnnotationPresent(DbTable.class))
                return;

            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder columnBuilder = new StringBuilder();
            StringBuilder paramPlaceHolderBuilder = new StringBuilder();
            List<Object> parameters = new ArrayList<>();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                    continue;

                field.setAccessible(true);
                if (field.get(entity) == null)
                    continue;

                DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                columnBuilder.append(dbTableColumn.name()).append(",");
                paramPlaceHolderBuilder.append("?").append(",");
                parameters.add(field.get(entity));

            }

            String queryBuilder = "insert into " +
                dbTable.name() +
                "(" +
                columnBuilder +
                ")" +
                " values(" +
                paramPlaceHolderBuilder +
                ")";

            String query = queryBuilder.replace(",)", ")");
            System.out.println("Query: " + query);

            PreparedStatement sqlStmt = connection.prepareStatement(query);

            int paramIdx = 1;
            for (Object param : parameters) {
                if (param.getClass().isAssignableFrom(BigDecimal.class))
                    sqlStmt.setBigDecimal(paramIdx++, (BigDecimal) param);
                else if (param.getClass().isAssignableFrom(Long.class))
                    sqlStmt.setLong(paramIdx++, (long) param);
                else if (param.getClass().isAssignableFrom(Date.class))
                    sqlStmt.setDate(paramIdx++, new java.sql.Date(((Date) param).getTime()));
                else
                    sqlStmt.setString(paramIdx++, (String) param);
            }

            sqlStmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> fetch(T entity) {

        List<T> resultList = new ArrayList<>();

        try {
            Class<?> clazz = entity.getClass();

            if (!clazz.isAnnotationPresent(DbTable.class))
                return resultList;

            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            String tableAlias = dbTable.name().charAt(0) + "_e_";
            System.out.println("table alias " + tableAlias);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder columnBuilder = new StringBuilder();
            StringBuilder paramPlaceHolderBuilder = new StringBuilder();
            List<Object> whereParams = new ArrayList<>();

            DateConverter converter = new DateConverter( null );
            converter.setPattern("yyyy-mm-dd");
            ConvertUtils.register(converter, Date.class);

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                    continue;

                DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                columnBuilder.append(tableAlias).append(".").append(dbTableColumn.name()).append(",");

                field.setAccessible(true);
                if (field.get(entity) != null) {
                    paramPlaceHolderBuilder
                        .append(whereParams.isEmpty()?"":" and ")
                        .append(tableAlias).append(".").append(dbTableColumn.name()).append("=?");
                    whereParams.add(field.get(entity));
                }

            }

            String queryBuilder =
                "select " +
                columnBuilder +
                " from " +
                dbTable.name() + " " + tableAlias +
                (whereParams.isEmpty() && StringUtils.isBlank(paramPlaceHolderBuilder) ? "" : " where " + paramPlaceHolderBuilder);

            String query = queryBuilder.replace(", from", " from");
            System.out.println("Query: " + query);

            PreparedStatement sqlStmt = connection.prepareStatement(query);

            int paramIdx = 1;
            for (Object whereParam : whereParams) {
                if (whereParam.getClass().isAssignableFrom(BigDecimal.class))
                    sqlStmt.setBigDecimal(paramIdx++, (BigDecimal) whereParam);
                else if (whereParam.getClass().isAssignableFrom(Long.class))
                    sqlStmt.setLong(paramIdx++, (long) whereParam);
                else
                    sqlStmt.setString(paramIdx++, (String) whereParam);
            }

            ResultSet resultSet = sqlStmt.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int resultSetMetaDataCnt = resultSetMetaData.getColumnCount();

            while (resultSet.next()){
                T bean = (T) entity.getClass().getDeclaredConstructor().newInstance();

                for (int idx = 1; idx <= resultSetMetaDataCnt; idx++ ) {
                    String colName = resultSetMetaData.getColumnName(idx);

                    for (Field field : fields) {
                        if (!field.isAnnotationPresent(DbTableColumn.class) || field.isAnnotationPresent(DbTableId.class))
                            continue;

                        DbTableColumn dbTableColumn = field.getAnnotation(DbTableColumn.class);

                        field.setAccessible(true);
                        if (dbTableColumn.name().equals(colName)) {
                            BeanUtils.setProperty(bean, field.getName(), resultSet.getObject(idx));
                            break;
                        }
                    }

                }

                resultList.add(bean);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return resultList;

    }

    @PreDestroy
    public void closeConnection(){
        try {
            if (connection != null)
                connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
