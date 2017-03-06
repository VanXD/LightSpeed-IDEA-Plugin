package com.vanxd.adapter;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.util.DirectoryUtil;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.sun.istack.internal.NotNull;
import com.vanxd.generator.PackageGenerator;
import com.vanxd.generator.mvc.DaoImplPackageGenerator;
import com.vanxd.generator.mvc.ServiceImplPackageGenerator;
import com.vanxd.setting.LightSpeedSetting;
import com.vanxd.util.FileUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wyd on 2017/3/6.
 */
public class AllianceGenerator {
    private static final LightSpeedSetting lightSpeedSetting = ServiceManager.getService(LightSpeedSetting.class);
    private final List<PackageGenerator> packageGenerators = new ArrayList<>();
    PsiFile actionPsiFile;
    PsiManager actionPsiManager;

    public AllianceGenerator(@NotNull PsiFile actionPsiFile) {
        this.actionPsiFile = actionPsiFile;
        actionPsiManager = actionPsiFile.getContainingDirectory().getManager();
        PsiDirectory containingDirectory = actionPsiFile.getContainingDirectory();
        packageGenerators.add(PackageGenerator.CONTROLLER_PACKAGE_GENERATOR);
        packageGenerators.add(PackageGenerator.SERVICE_PACKAGE_GENERATOR);
        packageGenerators.add(new ServiceImplPackageGenerator(containingDirectory, PackageGenerator.SERVICE_PACKAGE_GENERATOR));
        packageGenerators.add(PackageGenerator.DAO_PACKAGE_GENERATOR);
        packageGenerators.add(new DaoImplPackageGenerator(containingDirectory, PackageGenerator.DAO_PACKAGE_GENERATOR));
        createGeneratorPackageDirectory();
    }

    public void createFromTemplate(@NotNull String[] templateNames, @NotNull Properties properties) {
        Project project;
        PsiDirectory packageDirectory;
        FileTemplate fileTemplate;
        PsiElement fromTemplate = null;
        for (String templateName : templateNames) {
            project = this.actionPsiFile.getProject();
            fileTemplate = FileTemplateManager.getInstance(project).getJ2eeTemplate(templateName);
            fromTemplate = null;
            for (PackageGenerator packageGenerator : packageGenerators) {
                if (StringUtils.isNotEmpty(packageGenerator.getPackageName()) && templateName.lastIndexOf(packageGenerator.getSuffix()) > -1) {
                    packageDirectory = packageGenerator.getPackageDirectory(project);
                    fromTemplate = FileUtil.createFromTemplate(fileTemplate, properties, packageDirectory);
                    break;
                }
            }
            if (null == fromTemplate) {
                fromTemplate = FileUtil.createFromTemplate(fileTemplate, properties, actionPsiFile.getContainingDirectory());
            }
        }

    }

    /**
     * 创建包目录
     */
    private void createGeneratorPackageDirectory() {
        String basePath = actionPsiFile.getProject().getBasePath();
        for (PackageGenerator packageGenerator : packageGenerators) {
            DirectoryUtil.mkdirs(this.actionPsiManager, basePath + "/src/" + packageGenerator.getPackageName().replace(".", "/"));
        }
    }
}
