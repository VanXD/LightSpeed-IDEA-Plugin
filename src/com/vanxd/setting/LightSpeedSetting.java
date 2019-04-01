package com.vanxd.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.vanxd.generator.PackageGenerator;
import org.jdom.Element;
import org.jetbrains.annotations.Nullable;

import static com.vanxd.generator.ConfigurationHolder.*;

/**
 * @author wyd on 2017/3/4.
 */
@State(name = "LightSpeedSettings", storages = @Storage(value = "other", file = "$APP_CONFIG$/LightSpeed.xml"))
public class LightSpeedSetting implements PersistentStateComponent<Element> {

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("LightSpeedSettings");
        element.setAttribute(PROJECT_PACKAGE_GENERATOR.getId(), PROJECT_PACKAGE_GENERATOR.getPackageName());
        element.setAttribute(CONTROLLER_PACKAGE_GENERATOR.getId(), CONTROLLER_PACKAGE_GENERATOR.getPackageName());
        element.setAttribute(SERVICE_PACKAGE_GENERATOR.getId(), SERVICE_PACKAGE_GENERATOR.getPackageName());
        element.setAttribute(DAO_PACKAGE_GENERATOR.getId(), DAO_PACKAGE_GENERATOR.getPackageName());
        element.setAttribute("SHIRO_SUPPORT", SHIRO_SUPPORT.toString());
        element.setAttribute("PSCP_CMD_FIELD", PSCP_CMD_FIELD);
        return element;
    }

    @Override
    public void loadState(Element element) {
        loadState(element, PROJECT_PACKAGE_GENERATOR);
        loadState(element, CONTROLLER_PACKAGE_GENERATOR);
        loadState(element, SERVICE_PACKAGE_GENERATOR);
        loadState(element, DAO_PACKAGE_GENERATOR);
        SHIRO_SUPPORT = Boolean.valueOf(element.getAttributeValue("SHIRO_SUPPORT"));
        PSCP_CMD_FIELD = null == element.getAttributeValue("PSCP_CMD_FIELD") ? "" : element.getAttributeValue("PSCP_CMD_FIELD");
    }

    private void loadState(Element element, PackageGenerator generator) {
        String attribute = element.getAttributeValue(generator.getId());
        if (null != attribute) {
            generator.setPackageName(attribute);
        }
    }
}
