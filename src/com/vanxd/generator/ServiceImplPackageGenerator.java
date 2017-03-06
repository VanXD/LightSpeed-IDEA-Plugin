package com.vanxd.generator;

import org.apache.commons.lang.StringUtils;

/**
 * @author wyd on 2017/3/4.
 */
public class ServiceImplPackageGenerator extends PackageGenerator {
    @Override
    public String getId() {
        return "ServicePackageGenerator";
    }

    @Override
    public String getPackageName() {
        return StringUtils.isEmpty(SERVICE_PACKAGE_GENERATOR.getPackageName()) ? "" : SERVICE_PACKAGE_GENERATOR.getPackageName() + ".impl";
    }
}
