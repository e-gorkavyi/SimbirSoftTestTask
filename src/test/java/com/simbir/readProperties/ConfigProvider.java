package com.simbir.readProperties;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.load("application.conf");
    }

    String URL = config.getString("baseUrl");
    String NODE_URL = config.getString("hubUrl");
    String USER_NAME = config.getString("userName");
}
