/**
 * Copyright (c) Codice Foundation
 *
 * <p>This is free software: you can redistribute it and/or modify it under the terms of the GNU
 * Lesser General Public License as published by the Free Software Foundation, either version 3 of
 * the License, or any later version.
 *
 * <p>This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details. A copy of the GNU Lesser General Public
 * License is distributed along with this program and can be found at
 * <http://www.gnu.org/licenses/lgpl.html>.
 */
package org.codice.ddf.admin.sources.services;

import static org.codice.ddf.admin.common.services.ServiceCommons.FLAG_PASSWORD;
import static org.codice.ddf.admin.common.services.ServiceCommons.SERVICE_PID_KEY;
import static org.codice.ddf.admin.common.services.ServiceCommons.mapValue;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.codice.ddf.admin.common.services.ServiceCommons;
import org.codice.ddf.admin.sources.fields.type.OpenSearchSourceConfigurationField;

public class OpenSearchServiceProperties {

  // --- OpenSearch Service Properties
  public static final String ENDPOINT_URL = "endpointUrl";

  public static final String USERNAME = "username";

  @SuppressWarnings("squid:S2068" /* Used as a key, not a hard-coded password */)
  public static final String PASSWORD = "password";

  public static final String SHORTNAME = "shortname";
  // ---

  public static final String OPENSEARCH_FACTORY_PID = "OpenSearchSource";

  public static final String OPENSEARCH_FEATURE = "catalog-opensearch-source";

  public static final List<String> OPENSEARCH_FACTORY_PIDS =
      ImmutableList.of(OPENSEARCH_FACTORY_PID);

  public static final Function<Map<String, Object>, OpenSearchSourceConfigurationField>
      SERVICE_PROPS_TO_OPENSEARCH_CONFIG =
          OpenSearchServiceProperties::servicePropsToOpenSearchConfig;

  private OpenSearchServiceProperties() {}

  public static OpenSearchSourceConfigurationField servicePropsToOpenSearchConfig(
      Map<String, Object> props) {
    OpenSearchSourceConfigurationField config = new OpenSearchSourceConfigurationField();
    config.pid(mapValue(props, SERVICE_PID_KEY));
    config.sourceName(mapValue(props, SHORTNAME));
    config.endpointUrl(mapValue(props, ENDPOINT_URL));
    config.credentials().username(mapValue(props, USERNAME));
    config.credentials().password(FLAG_PASSWORD);
    return config;
  }

  public static Map<String, Object> openSearchConfigToServiceProps(
      OpenSearchSourceConfigurationField config) {
    ServiceCommons.ServicePropertyBuilder builder =
        new ServiceCommons.ServicePropertyBuilder()
            .putPropertyIfNotNull(SHORTNAME, config.sourceNameField())
            .putPropertyIfNotNull(ENDPOINT_URL, config.endpointUrlField())
            .putPropertyIfNotNull(USERNAME, config.credentials().usernameField());

    String password = config.credentials().password();
    if (password != null && !password.equals(FLAG_PASSWORD)) {
      builder.put(PASSWORD, config.credentials().password());
    }
    return builder.build();
  }
}
