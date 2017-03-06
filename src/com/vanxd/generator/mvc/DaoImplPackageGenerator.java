package com.vanxd.generator.mvc;

import com.intellij.psi.PsiDirectory;
import com.vanxd.generator.ImplPackageGenerator;
import com.vanxd.generator.PackageGenerator;

/**
 * @author wyd on 2017/3/4.
 */
public class DaoImplPackageGenerator extends ImplPackageGenerator {

    public DaoImplPackageGenerator(PsiDirectory actionContainerDirectory, PackageGenerator parentPackageGenerator) {
        super(actionContainerDirectory, parentPackageGenerator);
    }

    @Override
    public String getId() {
        return "DaoImplPackageGenerator";
    }

    @Override
    public String getSuffix() {
        return "DaoImpl";
    }
}
