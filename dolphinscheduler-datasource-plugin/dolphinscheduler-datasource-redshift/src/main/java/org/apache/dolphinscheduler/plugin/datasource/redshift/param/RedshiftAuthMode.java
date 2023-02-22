/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dolphinscheduler.plugin.datasource.redshift.param;

import static java.util.stream.Collectors.toMap;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.base.Functions;

public enum RedshiftAuthMode {

    PASSWORD(0, "password"),
    IAM_ACCESS_KEY(1, "IAM-accessKey"),
    ;

    private static final Map<Integer, RedshiftAuthMode> AUTH_TYPE_MAP =
            Arrays.stream(RedshiftAuthMode.values()).collect(toMap(RedshiftAuthMode::getCode, Functions.identity()));
    private final int code;
    @JsonValue
    private final String descp;

    RedshiftAuthMode(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    public static RedshiftAuthMode of(int type) {
        if (AUTH_TYPE_MAP.containsKey(type)) {
            return AUTH_TYPE_MAP.get(type);
        }
        return null;
    }

    public static RedshiftAuthMode ofName(String name) {
        return Arrays.stream(RedshiftAuthMode.values()).filter(e -> e.name().equals(name)).findFirst()
                .orElseThrow(() -> new NoSuchElementException("no such auth type"));
    }

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }
}
