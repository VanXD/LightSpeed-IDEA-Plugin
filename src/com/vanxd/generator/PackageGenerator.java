package com.vanxd.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiDirectory;
import com.vanxd.generator.mvc.ControllerPackageGenerator;
import com.vanxd.generator.mvc.DaoPackageGenerator;
import com.vanxd.generator.mvc.ServicePackageGenerator;

/**
 * @author wyd on 2017/3/4.
 */
public abstract class PackageGenerator {
    public static final PackageGenerator CONTROLLER_PACKAGE_GENERATOR = new ControllerPackageGenerator();
    public static final PackageGenerator SERVICE_PACKAGE_GENERATOR = new ServicePackageGenerator();
    public static final PackageGenerator DAO_PACKAGE_GENERATOR = new DaoPackageGenerator();

    /** 包名 */
    private String packageName = "";

    /** 用来作为类似MAP中的KEY使用 */
    public abstract String getId();

    /** 匹配模板的后缀使用 */
    public abstract String getSuffix();

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public PsiDirectory getPackageDirectory(Project project) {
        return JavaPsiFacade.getInstance(project).findPackage(packageName).getDirectories()[0];
    }
}
