package com.vanxd.util;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wyd on 2016/12/16.
 */
public class FileUtil {

    /**
     * 创建指定{templateName}的文件
     * @param templateName      模板名
     * @param project           项目对象
     * @param properties        属性对象，为模板填值用
     * @param dir               目录
     * @return
     */
    public static PsiElement createFromTemplate(@NotNull String templateName, @NotNull Project project, @NotNull Properties properties, @NotNull PsiDirectory dir) {
        try {
            FileTemplate template = FileTemplateManager.getInstance(project).getJ2eeTemplate(templateName);
            return FileTemplateUtil.createFromTemplate(template, "", properties, dir);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 根据{templateNames}批量创建文件
     * 需要注意，这里多个模板用的值都是{properties}这一个对象里的。
     * @param templateNames     模板名
     * @param project           项目对象
     * @param properties        属性对象，为模板填值用
     * @param dir               目录
     * @return
     */
    public static List<PsiElement> createFromTemplate(@NotNull String[] templateNames, @NotNull Project project, @NotNull Properties properties, @NotNull PsiDirectory dir) {
        List<PsiElement> psiElementList = new ArrayList<>();
        try {
            for ( String templateName : templateNames ) {
                FileTemplate template = FileTemplateManager.getInstance(project).getJ2eeTemplate(templateName);
                PsiElement psiElement = FileTemplateUtil.createFromTemplate(template, "", properties, dir);
                psiElementList.add(psiElement);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return psiElementList;
    }
}
