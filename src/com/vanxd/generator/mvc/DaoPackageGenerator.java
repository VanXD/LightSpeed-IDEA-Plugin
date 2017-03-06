package com.vanxd.generator.mvc;

import com.vanxd.generator.PackageGenerator;

/**
 * @author wyd on 2017/3/4.
 */
public class DaoPackageGenerator extends PackageGenerator {
    @Override
    public String getId() {
        return "DaoPackageGenerator";
    }

    @Override
    public String getSuffix() {
        return "DaoInterface";
    }
}
