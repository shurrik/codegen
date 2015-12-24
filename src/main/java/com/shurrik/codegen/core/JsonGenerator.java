package com.shurrik.codegen.core;

import com.shurrik.codegen.model.ClassObject;
import com.shurrik.codegen.util.helper.ClassObjecHelper;
import com.shurrik.codegen.util.helper.JsonClassObjectHelper;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.List;

/**
 * Created by lip on 2015-12-24.
 */
public class JsonGenerator extends AbstractGenerator{

    @Override
    public List<ClassObject> getClassObjectList() throws Exception {
        ClassObjecHelper helper = new JsonClassObjectHelper();
        return  helper.getClassObjectList();
    }
}
