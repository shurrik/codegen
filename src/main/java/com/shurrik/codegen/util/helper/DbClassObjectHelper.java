package com.shurrik.codegen.util.helper;

import com.cgs.db.exception.NonTransientDataAccessException;
import com.cgs.db.meta.core.MetaLoader;
import com.cgs.db.meta.schema.Schema;
import com.cgs.db.meta.schema.Table;
import com.cgs.db.util.PrintUtils;
import com.shurrik.codegen.model.ClassObject;
import com.shurrik.codegen.model.ClassProperty;
import com.shurrik.codegen.model.Column;
import com.shurrik.codegen.model.ComparatorClassObject;
import com.shurrik.codegen.util.CharacterCaseUtils;
import com.shurrik.codegen.util.ProjectConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by lip on 2015-12-24.
 */
public class DbClassObjectHelper extends ClassObjecHelper{
    @Override
    public List<ClassObject> getClassObjectList() throws SQLException {

        List<ClassObject> classes = new ArrayList();

        ApplicationContext context =
                new ClassPathXmlApplicationContext("config.xml");
        MetaLoader metaLoader=(MetaLoader) context.getBean("metaLoader");

        Connection conn = metaLoader.getDataSource().getConnection();

        Set<String> tableNames=metaLoader.getTableNames();

        Schema schema=metaLoader.getSchema();
        Map<String,Table> tables=schema.getTables();


        String dbType = getProductName(conn);
        Set<String> keys=tables.keySet();
        for (String string : keys) {
            Table t=tables.get(string);
            classes.add(table2ClassObject(t,dbType));
        }

        //排序
        ComparatorClassObject comparator=new ComparatorClassObject();
        Collections.sort(classes, comparator);
        return classes;
    }

    private String getProductName(Connection conn)
    {
        String product = null;
        try {
            DatabaseMetaData dbm = conn.getMetaData();
            product = dbm.getDatabaseProductName();
            return product;

        } catch (SQLException e) {
            throw new NonTransientDataAccessException("can not get database product information!",e);
        }
    }

    private ClassObject table2ClassObject(Table table,String dbType)
    {
        ClassObject co = ClassObject.instance();
        String className = processTableName(table.getName());
        className = className.substring(0,1).toUpperCase() + className.substring(1);
        String classRemark = StringUtils.isNotBlank(table.getComment())?table.getComment():className;
        co.setClassName(className);
        co.setClassRemark(classRemark);


        //setProperties
        //setColumns
        List<ClassProperty> properties = new ArrayList();
        List<Column> cols = new ArrayList();

        Map<String, com.cgs.db.meta.schema.Column> columns=table.getColumns();

        Set<String> keys=columns.keySet();
        for (String string : keys) {
            com.cgs.db.meta.schema.Column column=columns.get(string);

            ClassProperty cp = new ClassProperty();
            Column col = new Column();

            String pName = CharacterCaseUtils.toCamelCase(column.getName().toLowerCase());
            String pType = column.getTypeName().toLowerCase();
            String comment = StringUtils.isNotBlank(column.getComment())?column.getComment():pName;

            String cName = CharacterCaseUtils.toUnderlineCase(pName);
            String cLength = String.valueOf(column.getLength());
            Boolean isPk = false;
            Boolean notNull = column.isNullable()?false:true;

            pType = getPropertyType(pType,dbType);
            cp.setName(pName);
            cp.setType(pType);
            cp.setComment(comment);
            cp.setSize(cLength);
            cp.setNotNull(notNull);

            col.setName(cName);

            col.setType(getColumnType(pType));
            col.setLength(cLength);
            col.setIsPk(isPk);
            col.setNotNull(notNull);
            col.setComment(comment);
            col.setProName(pName);

            properties.add(cp);
            cols.add(col);

        }
        co.setProperties(properties);
        co.setColumns(cols);
        //setTableName
        ProjectConfig conf = ProjectConfig.getInstance();
        co.setTableName(StringUtils.isNotBlank(conf.getTablePrefix())?"t_"+conf.getTablePrefix()+"_"+className.toLowerCase():"t_"+className.toLowerCase());

        return co;
    }


    public String processTableName(String tableName) {
        tableName = tableName.toLowerCase();
        ProjectConfig conf = ProjectConfig.getInstance();
        String dbPrefix = conf.getDbPrefix();
        if(StringUtils.isNotBlank(dbPrefix))
        {
            String[] arr = dbPrefix.split(",");
            for(String a:arr)
            {
                tableName = clearDbPrefix(tableName,a);
            }
        }

//        if(tableName.startsWith("t_"))//去掉t_
//        {
//            tableName = tableName.substring(2);
//        }
        tableName = CharacterCaseUtils.toCamelCase(tableName);
        return tableName;
    }


    private String clearDbPrefix(String tableName,String dbPrefix)
    {
        if(tableName.startsWith(dbPrefix))//如去掉t_
        {
            tableName = tableName.replace(dbPrefix,"");
        }
        return tableName;
    }


    public static void main(String[] args) throws SQLException {
        DbClassObjectHelper helper = new DbClassObjectHelper();
        List<ClassObject> classObjectList = helper.getClassObjectList();
        System.out.println("Ok");
    }

}
