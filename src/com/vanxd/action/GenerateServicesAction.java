package com.vanxd.action;

import com.intellij.ide.IdeView;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.vanxd.util.FileUtil;

import java.util.Properties;

/**
 * 直接生成Service接口和ServiceImpl
 * 使用模板：fileTemplates.j2ee.service
 *
 * @author wyd on 2016/12/16.
 */
public class GenerateServicesAction extends AnAction {
    private final static String[] templateNames = {"Vanxd ServiceInterface", "Vanxd ServiceImpl"};


    @Override
    public void actionPerformed(AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        IdeView view = LangDataKeys.IDE_VIEW.getData(dataContext);
        PsiDirectory[] dirs = view.getDirectories();
        String entityPath = dataContext.toString();
        String entityName = entityPath.substring(entityPath.lastIndexOf("/") + 1, entityPath.lastIndexOf("."));

        Properties properties = new Properties();
        properties.setProperty(FileTemplate.ATTRIBUTE_NAME, entityName);

        FileUtil.createFromTemplate(templateNames, e.getProject(), properties, dirs[0]);
    }
}
