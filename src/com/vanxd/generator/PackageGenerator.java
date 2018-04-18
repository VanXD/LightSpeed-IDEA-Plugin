package com.vanxd.generator;

import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.PsiManagerImpl;
import com.intellij.psi.impl.file.PsiJavaDirectoryFactory;

import java.io.IOException;

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

    public PsiDirectory getBusinessPackageDirectory(PsiManager actionPsiManager, PsiDirectory actionContainingDirectory) {
        String path = actionContainingDirectory.getVirtualFile().getPath();
        String basePath = path.substring(0, path.indexOf("/com"));
        String targetPath = basePath + "/" + getBusinessPackageName(actionContainingDirectory).replace(".", "/");
        VirtualFile directories = null;
        try {
            directories = VfsUtil.createDirectories(targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new PsiJavaDirectoryFactory((PsiManagerImpl) actionPsiManager).createDirectory(directories);
    }

    protected String getBusinessPackageName(PsiDirectory actionContainingDirectory) {
        return packageName + "." + actionContainingDirectory.getName();
    }
}
