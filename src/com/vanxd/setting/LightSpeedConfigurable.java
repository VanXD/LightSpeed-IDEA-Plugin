package com.vanxd.setting;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

import static com.vanxd.generator.GeneratorHolder.*;

/**
 * @author wyd on 2017/3/4.
 */
public class LightSpeedConfigurable implements SearchableConfigurable {
    private static LightSpeedConfigForm configForm;
    @NotNull
    @Override
    public String getId() {
        return "lightspeed";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (null == configForm) {
            configForm = new LightSpeedConfigForm();
        }
        // 虽然LightSpeedSetting实现了持久化接口，也在XML中注册了，但就是要有这一行。
        ServiceManager.getService(LightSpeedSetting.class);
        return configForm.mainPanel;
    }

    @Override
    public boolean isModified() {
        return  !CONTROLLER_PACKAGE_GENERATOR.getPackageName().equals(configForm.controllerPackageName.getText())
            ||  !SERVICE_PACKAGE_GENERATOR.getPackageName().equals(configForm.servicePackageName.getText())
            ||  !DAO_PACKAGE_GENERATOR.getPackageName().equals(configForm.daoPackageName.getText())
            ||  !PROJECT_PACKAGE_GENERATOR.getPackageName().equals(configForm.projectDirectoryName.getText());
    }

    @Override
    public void apply() throws ConfigurationException {
        PROJECT_PACKAGE_GENERATOR.setPackageName(configForm.projectDirectoryName.getText());
        CONTROLLER_PACKAGE_GENERATOR.setPackageName(configForm.controllerPackageName.getText());
        SERVICE_PACKAGE_GENERATOR.setPackageName(configForm.servicePackageName.getText());
        DAO_PACKAGE_GENERATOR.setPackageName(configForm.daoPackageName.getText());
    }

    @Override
    public void reset() {
        configForm.projectDirectoryName.setText(PROJECT_PACKAGE_GENERATOR.getPackageName());
        configForm.controllerPackageName.setText(CONTROLLER_PACKAGE_GENERATOR.getPackageName());
        configForm.servicePackageName.setText(SERVICE_PACKAGE_GENERATOR.getPackageName());
        configForm.daoPackageName.setText(DAO_PACKAGE_GENERATOR.getPackageName());
    }

    @Override
    public void disposeUIResources() {

    }

    @Nls
    @Override
    public String getDisplayName() {
        return "LightSpeed";
    }
    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }
}
