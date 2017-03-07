package com.vanxd.generator.mvc;

import com.intellij.psi.PsiDirectory;
import com.vanxd.generator.PackageGenerator;

/**
 * @author wyd on 2017/3/4.
 */
public class ProjectPackageGenerator extends PackageGenerator {
    @Override
    public String getId() {
        return "ProjectPackageGenerator";
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public String getBusinessPackageName(PsiDirectory actionContainingDirectory) {
        return getPackageName();
    }
}
