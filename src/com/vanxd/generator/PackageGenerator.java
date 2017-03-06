package com.vanxd.generator;

/**
 * @author wyd on 2017/3/4.
 */
public abstract class PackageGenerator {
    public static final PackageGenerator CONTROLLER_PACKAGE_GENERATOR = new ControllerPackageGenerator();
    public static final PackageGenerator SERVICE_PACKAGE_GENERATOR = new ServicePackageGenerator();
    public static final PackageGenerator DAO_PACKAGE_GENERATOR = new DaoPackageGenerator();


    private String packageName = "";
    private String id;

    public abstract String getId();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
