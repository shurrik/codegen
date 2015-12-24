package com.shurrik.codegen;

import com.shurrik.codegen.core.DbGenerator;
import com.shurrik.codegen.core.JsonGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by lip on 2015-12-24.
 */
public class DbCommandLine {

    public static void main(String args[]) throws Exception
    {
        DbGenerator generator = new DbGenerator();
        generator.run();
    }
}
