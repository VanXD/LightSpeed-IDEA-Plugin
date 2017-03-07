package com.vanxd.generator;

import com.intellij.psi.PsiDirectory;
import org.apache.commons.lang.StringUtils;

/**
 * @author wyd on 2017/3/6.
 */
public abstract class ImplPackageGenerator extends PackageGenerator{
    /** 执行命令文件所在的包 */
    protected PsiDirectory actionContainerDirectory;
    /** 该生成器的父生成器(e.g:ServiceImplPackageGenerator 的父生成器是 ServicePackageGenerator) */
    protected PackageGenerator parentPackageGenerator;

    public ImplPackageGenerator(PsiDirectory actionContainerDirectory, PackageGenerator parentPackageGenerator) {
        this.actionContainerDirectory = actionContainerDirectory;
        this.parentPackageGenerator = parentPackageGenerator;
        if(StringUtils.isNotEmpty(parentPackageGenerator.getPackageName())) {
            setPackageName(parentPackageGenerator.getPackageName() + "." + actionContainerDirectory.getName() + ".impl");
        }
    }

    public PsiDirectory getActionContainerDirectory() {
        return actionContainerDirectory;
    }

    public PackageGenerator getParentPackageGenerator() {
        return parentPackageGenerator;
    }

    @Override
    public String getBusinessPackageName(PsiDirectory actionContainingDirectory) {
        return getPackageName();
    }
}
