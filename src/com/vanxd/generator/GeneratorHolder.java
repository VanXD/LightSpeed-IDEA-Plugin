package com.vanxd.generator;

import com.vanxd.generator.mvc.ControllerPackageGenerator;
import com.vanxd.generator.mvc.DaoPackageGenerator;
import com.vanxd.generator.mvc.ServicePackageGenerator;

/**
 * @author wyd on 2017/3/6.
 */
public class GeneratorHolder {
    public static final PackageGenerator CONTROLLER_PACKAGE_GENERATOR = new ControllerPackageGenerator();
    public static final PackageGenerator SERVICE_PACKAGE_GENERATOR = new ServicePackageGenerator();
    public static final PackageGenerator DAO_PACKAGE_GENERATOR = new DaoPackageGenerator();
}
