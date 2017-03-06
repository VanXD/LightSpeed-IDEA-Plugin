package com.vanxd.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.vanxd.generator.PackageGenerator;
import org.jdom.Element;
import org.jetbrains.annotations.Nullable;

import static com.vanxd.generator.PackageGenerator.*;

/**
 * @author wyd on 2017/3/4.
 */
@State(name = "LightSpeedSettings", storages = @Storage(id = "other", file = "$APP_CONFIG$/LightSpeed.xml"))
public class LightSpeedSetting implements PersistentStateComponent<Element> {

    @Nullable
    @Override
    public Element getState() {
        Element element = new Element("LightSpeedSettings");
        element.setAttribute(CONTROLLER_PACKAGE_GENERATOR.getId(), CONTROLLER_PACKAGE_GENERATOR.getPackageName());
        element.setAttribute(SERVICE_PACKAGE_GENERATOR.getId(), SERVICE_PACKAGE_GENERATOR.getPackageName());
        element.setAttribute(DAO_PACKAGE_GENERATOR.getId(), DAO_PACKAGE_GENERATOR.getPackageName());
        return element;
    }

    @Override
    public void loadState(Element element) {
        loadState(element, CONTROLLER_PACKAGE_GENERATOR);
        loadState(element, SERVICE_PACKAGE_GENERATOR);
        loadState(element, DAO_PACKAGE_GENERATOR);
    }

    private void loadState(Element element, PackageGenerator generator) {
        String attribute = element.getAttributeValue(generator.getId());
        if (null != attribute) {
            generator.setPackageName(attribute);
        }
    }
}
