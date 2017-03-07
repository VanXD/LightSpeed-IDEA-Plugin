package com.vanxd.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiDirectory;

/**
 * @author wyd on 2017/3/4.
 */
public abstract class PackageGenerator {
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

    public PsiDirectory getBusinessPackageDirectory(Project project, PsiDirectory actionContainingDirectory) {
        String businessPackageName = getBusinessPackageName(actionContainingDirectory);
        return JavaPsiFacade.getInstance(project).findPackage(businessPackageName).getDirectories()[0];
    }

    public String getBusinessPackageName(PsiDirectory actionContainingDirectory) {
        return packageName + "." + actionContainingDirectory.getName();
    }
}
