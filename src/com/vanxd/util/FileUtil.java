package com.vanxd.util;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.sun.istack.internal.NotNull;
import com.vanxd.setting.LightSpeedSetting;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.vanxd.generator.ConfigurationHolder.CONTROLLER_PACKAGE_GENERATOR;
import static com.vanxd.generator.ConfigurationHolder.DAO_PACKAGE_GENERATOR;
import static com.vanxd.generator.ConfigurationHolder.SERVICE_PACKAGE_GENERATOR;

/**
 * @author wyd on 2016/12/16.
 */
public class FileUtil {
    private static final LightSpeedSetting lightSpeedSetting = ServiceManager.getService(LightSpeedSetting.class);
    /**
     * 根据{templateNames}批量创建文件
     * 需要注意，这里多个模板用的值都是{properties}这一个对象里的。
     * @param templateNames     模板名
     * @param project           项目对象
     * @param properties        属性对象，为模板填值用
     * @param actionPsiFile     执行操作的文件
     * @return
     */
    public static List<PsiElement> createFromTemplate(@NotNull String[] templateNames, @NotNull Project project, @NotNull Properties properties, @NotNull PsiFile actionPsiFile) {
        FileTemplate template = null;
        PsiElement psiElement = null;
        PsiDirectory layerDirectory = null;
        List<PsiElement> psiElementList = new ArrayList<>();
        try {
            for ( String templateName : templateNames ) {
                layerDirectory = getTemplateDirectory(templateName, project, actionPsiFile);
                template = FileTemplateManager.getInstance(project).getJ2eeTemplate(templateName);
                psiElement = FileTemplateUtil.createFromTemplate(template, "", properties, layerDirectory);
                psiElementList.add(psiElement);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return psiElementList;
    }

    public static PsiElement createFromTemplate(@NotNull FileTemplate template, @NotNull Properties properties, @NotNull PsiDirectory directory) {
        try {
            return FileTemplateUtil.createFromTemplate(template, "", properties, directory);
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获得template的文件夹，
     * 匹配MVC各层模板的包名，将文件直接放入对应的包
     * @param templateName      模板名称
     * @param project           项目
     * @param actionPsiFile     如果没有在默认关键字中获得相应的路径，则将文件生成位于和执行命令的文件同文件夹下
     * @return
     */
    private static PsiDirectory getTemplateDirectory(String templateName, Project project, PsiFile actionPsiFile) {
        JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);
        if (templateName.lastIndexOf("Controller") > -1) {
            String packageName = CONTROLLER_PACKAGE_GENERATOR.getPackageName();
            if(StringUtils.isNotEmpty(packageName)) {
                return javaPsiFacade.findPackage(packageName).getDirectories()[0];
            }
        }
        if (templateName.lastIndexOf("ServiceImpl") > -1) {
            return javaPsiFacade.findPackage(SERVICE_PACKAGE_GENERATOR.getPackageName() + ".impl").getDirectories()[0];
        }
        if (templateName.lastIndexOf("ServiceInterface") > -1) {
            return javaPsiFacade.findPackage(SERVICE_PACKAGE_GENERATOR.getPackageName()).getDirectories()[0];
        }
        if (templateName.lastIndexOf("DaoImpl") > -1) {
            return javaPsiFacade.findPackage(DAO_PACKAGE_GENERATOR.getPackageName() + ".impl").getDirectories()[0];
        }
        return actionPsiFile.getContainingDirectory();
    }
}
