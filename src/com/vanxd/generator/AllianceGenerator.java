package com.vanxd.generator;

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
import com.vanxd.generator.mvc.DaoImplPackageGenerator;
import com.vanxd.generator.mvc.ServiceImplPackageGenerator;
import com.vanxd.setting.LightSpeedSetting;
import com.vanxd.util.FileUtil;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.vanxd.generator.GeneratorHolder.*;

/**
 * @author wyd on 2017/3/6.
 */
public class AllianceGenerator {
    private static final String mavenDir = "src/main/java/";
    private static final LightSpeedSetting lightSpeedSetting = ServiceManager.getService(LightSpeedSetting.class);
    private final List<PackageGenerator> packageGenerators = new ArrayList<>();
    private PsiFile actionPsiFile;
    private PsiManager actionPsiManager;
    private PsiDirectory actionContainingDirectory;
    private Project actionProject;

    public AllianceGenerator(@NotNull PsiFile actionPsiFile) {
        this.actionPsiFile = actionPsiFile;
        this.actionPsiManager = actionPsiFile.getContainingDirectory().getManager();
        this.actionContainingDirectory = actionPsiFile.getContainingDirectory();
        this.actionProject = actionPsiFile.getProject();
        packageGenerators.add(CONTROLLER_PACKAGE_GENERATOR);
        packageGenerators.add(SERVICE_PACKAGE_GENERATOR);
        packageGenerators.add(new ServiceImplPackageGenerator(actionContainingDirectory, SERVICE_PACKAGE_GENERATOR));
        packageGenerators.add(DAO_PACKAGE_GENERATOR);
        packageGenerators.add(new DaoImplPackageGenerator(actionContainingDirectory, DAO_PACKAGE_GENERATOR));
        createGeneratorPackageDirectory();
    }

    public void createFromTemplate(@NotNull String[] templateNames, @NotNull Properties properties) {
        Project project;
        PsiDirectory packageDirectory;
        FileTemplate fileTemplate;
        PsiElement fromTemplate = null;
        for (String templateName : templateNames) {
            project = this.actionProject;
            fileTemplate = FileTemplateManager.getInstance(project).getJ2eeTemplate(templateName);
            fromTemplate = null;
            doCreateFile(properties, project, fileTemplate, fromTemplate, templateName);
        }

    }

    private void doCreateFile(@NotNull Properties properties, Project project, FileTemplate fileTemplate, PsiElement fromTemplate, String templateName) {
        PsiDirectory packageDirectory;
        String templateNameSuffix = templateName.substring(templateName.indexOf(" ") + 1);
        for (PackageGenerator packageGenerator : packageGenerators) {
            if (StringUtils.isNotEmpty(packageGenerator.getPackageName()) && templateNameSuffix.equals(packageGenerator.getSuffix())) {
                packageDirectory = packageGenerator.getBusinessPackageDirectory(project, actionContainingDirectory);
                fromTemplate = FileUtil.createFromTemplate(fileTemplate, properties, packageDirectory);
                return;
            }
        }
        if (null == fromTemplate) {
            fromTemplate = FileUtil.createFromTemplate(fileTemplate, properties, actionPsiFile.getContainingDirectory());
        }
    }

    /**
     * 创建包目录
     */
    private void createGeneratorPackageDirectory() {
        String basePath = this.actionProject.getBasePath();
        String path = "";
        for (PackageGenerator packageGenerator : packageGenerators) {
            if(StringUtils.isEmpty(packageGenerator.getPackageName())) {
                continue;
            }
            // 项目路径 + 指定子项目的文件夹名 + maven工程结构的固定文件夹 + 指定的包路径
            path = basePath + "/"
                    + PROJECT_PACKAGE_GENERATOR.getBusinessPackageName(actionContainingDirectory)
                    + mavenDir
                    + packageGenerator.getBusinessPackageName(actionContainingDirectory).replace(".", "/");
            DirectoryUtil.mkdirs(this.actionPsiManager, path);
        }
    }
}
