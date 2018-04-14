package com.vanxd.generator;

import com.vanxd.generator.mvc.*;

/**
 * @author wyd on 2017/3/6.
 */
public class ConfigurationHolder {
    public static final PackageGenerator PROJECT_PACKAGE_GENERATOR = new ProjectPackageGenerator();
    public static final PackageGenerator CONTROLLER_PACKAGE_GENERATOR = new ControllerPackageGenerator();
    public static final PackageGenerator SERVICE_PACKAGE_GENERATOR = new ServicePackageGenerator();
    public static final PackageGenerator DAO_PACKAGE_GENERATOR = new DaoPackageGenerator();
    public static Boolean SHIRO_SUPPORT = false;
    public static String PSCP_CMD_FIELD = "";
}
