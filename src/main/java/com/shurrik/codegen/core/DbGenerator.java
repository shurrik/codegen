package com.shurrik.codegen.core;

import com.cgs.db.meta.core.MetaLoader;
import com.cgs.db.meta.schema.Schema;
import com.cgs.db.meta.schema.Table;
import com.cgs.db.util.PrintUtils;
import com.shurrik.codegen.model.ClassObject;
import com.shurrik.codegen.util.helper.ClassObjecHelper;
import com.shurrik.codegen.util.helper.DbClassObjectHelper;
import com.shurrik.codegen.util.helper.JsonClassObjectHelper;
import freemarker.template.TemplateException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lip on 2015-12-24.
 */
public class DbGenerator extends AbstractGenerator{

    @Override
    public List<ClassObject> getClassObjectList() throws IOException, TemplateException, SQLException {
        ClassObjecHelper helper = new DbClassObjectHelper();
        return  helper.getClassObjectList();
    }
}
