package com.vanxd.generator;

import org.apache.commons.lang.StringUtils;

/**
 * @author wyd on 2017/3/4.
 */
public class DaoImplPackageGenerator extends PackageGenerator {
    @Override
    public String getId() {
        return "DaoPackageGenerator";
    }

    @Override
    public String getPackageName() {
        return StringUtils.isEmpty(DAO_PACKAGE_GENERATOR.getPackageName()) ? "" : DAO_PACKAGE_GENERATOR.getPackageName() + ".impl";
    }
}
