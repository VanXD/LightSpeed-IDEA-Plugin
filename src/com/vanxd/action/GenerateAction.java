package com.vanxd.action;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiFile;
import com.vanxd.generator.AllianceGenerator;
import com.vanxd.generator.GeneratorHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

/**
 * @author wyd on 2017/3/6.
 */
public abstract class GenerateAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        PsiFile actionPsiFile = e.getData(LangDataKeys.PSI_FILE);
        String entityName = actionPsiFile.getName().substring(0, actionPsiFile.getName().indexOf("."));
        Properties properties = getProperties(entityName);

        AllianceGenerator allianceGenerator = new AllianceGenerator(actionPsiFile);
        allianceGenerator.createFromTemplate(getTemplates(), properties);
    }

    /**
     * 获得需要创建的模板名
     * @return
     */
    protected abstract String[] getTemplates();

    @NotNull
    private Properties getProperties(String entityName) {
        Properties properties = new Properties();
        properties.setProperty(FileTemplate.ATTRIBUTE_NAME, entityName);
        properties.setProperty("CAMEL_NAME", Character.toLowerCase(entityName.charAt(0)) + entityName.substring(1));
        if (GeneratorHolder.SHIRO_SUPPORT) {
            properties.setProperty("SHIRO_REQUIRES_PERMISSIONS", "@RequiresPermissions(\"\")");
        }
        setCustomerProperty(properties);
        return properties;
    }


    /**
     * 设置自定义的模板属性
     * @param properties
     */
    protected void setCustomerProperty(Properties properties) {}
}
