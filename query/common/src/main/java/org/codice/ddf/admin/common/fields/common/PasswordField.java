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
package org.codice.ddf.admin.common.fields.common;

import org.codice.ddf.admin.common.fields.base.scalar.StringField;

public class PasswordField extends StringField {

  public static final String DEFAULT_FIELD_NAME = "password";

  public static final String FIELD_TYPE_NAME = "Password";

  public static final String DESCRIPTION = "Password used for authentication.";

  public static final String HIDDEN_FLAG = "*****";

  public PasswordField() {
    super(DEFAULT_FIELD_NAME, FIELD_TYPE_NAME, DESCRIPTION);
  }

  public PasswordField(String fieldName) {
    super(fieldName, FIELD_TYPE_NAME, DESCRIPTION);
  }

  @Override
  public String getSanitizedValue() {
    return HIDDEN_FLAG;
  }
}
