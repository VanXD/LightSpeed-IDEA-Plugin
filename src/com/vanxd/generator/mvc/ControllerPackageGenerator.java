package com.vanxd.generator.mvc;

import com.vanxd.generator.PackageGenerator;

/**
 * @author wyd on 2017/3/4.
 */
public class ControllerPackageGenerator extends PackageGenerator {
    @Override
    public String getId() {
        return "ControllerPackageGenerator";
    }

    @Override
    public String getSuffix() {
        return "Controller";
    }
}
